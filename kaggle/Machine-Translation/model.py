from __future__ import print_function
from __future__ import absolute_import

import os
import numpy as np
import tensorflow as tf

import ops
import config
from utils.batchGenerator import BatchGenerator

class Model():
	'''Sequence to sequence translation model'''
	def __init__(self):
		self.batch_size = config.FLAGS.batch_size
		self.num_units = config.FLAGS.num_units
		self.num_hidden_layers = config.FLAGS.num_hidden_layers
		self.learning_rate = config.FLAGS.learning_rate
		self.learning_rate_decay_factor = config.FLAGS.learning_rate_decay_factor
		self.max_gradient_norm = config.FLAGS.max_gradient_norm
		self.num_samples = config.FLAGS.num_samples
		self.checkpoint_step = config.FLAGS.checkpoint_step
		self.checkpoint_dir = config.FLAGS.checkpoint_dir
		self.model_name = config.FLAGS.model_name
		self.buckets = config.BUCKETS

		# English to French translation
		if config.FLAGS.target_vocab == 'fr':
			self.source_vocab_size = config.FLAGS.en_vocabulary_size
			self.target_vocab_size = config.FLAGS.fr_vocabulary_size
		else:
			self.source_vocab_size = config.FLAGS.fr_vocabulary_size
			self.target_vocab_size = config.FLAGS.en_vocabulary_size

		self.build_model()

	def inference_and_loss(self):
		# Creating LSTM layer
		single_cell = tf.nn.rnn_cell.LSTMCell(self.num_units)
		cell = tf.nn.rnn_cell.MultiRNNCell([single_cell] * self.num_hidden_layers)

		# Creating embedding seq2seq function with attention
		seq2seq_f = ops.embedding_seq2seq_with_attention(cell, self.source_vocab_size, self.target_vocab_size, self.num_units, self.output_projection)

		# Creating outputs and losses using model with buckets
		self.outputs, self.losses = tf.nn.seq2seq.model_with_buckets(self.encoder_inputs, self.decoder_inputs, self.targets, self.target_weights, self.buckets, lambda x, y: seq2seq_f(x, y), self.softmax_loss_function)

	def train_op(self):
		params = tf.trainable_variables()
		self.gradient_norms = []
		self.updates = []
		
		opt = tf.train.GradientDescentOptimizer(self.learning_rate_var)

		for b in xrange(len(self.buckets)):
			gradients = tf.gradients(self.losses[b], params)
			clipped_gradients, norm = tf.clip_by_global_norm(gradients, self.max_gradient_norm)
			self.gradient_norms.append(norm)
			self.updates.append(opt.apply_gradients(zip(clipped_gradients, params), global_step=self.global_step))

	def create_saver(self):
		saver = tf.train.Saver()

		self.saver = saver

	def build_model(self):
		self.graph = tf.Graph()

		with self.graph.as_default():
			# Creating placeholder for encoder and decoder inputs
			self.encoder_inputs, self.decoder_inputs = ops.encoder_decoder_input_placeholder(self.buckets[-1][0], self.buckets[-1][1] + 1)

			# Creating placeholder for targets
			self.targets = ops.target_placeholder(self.decoder_inputs)

			# Creating placeholder for target weights
			self.target_weights = ops.target_weight_placeholder(self.buckets[-1][1] + 1)

			# Creating output projection and softmax loss function in order to handle large vocabulary
			self.output_projection, self.softmax_loss_function = ops.handle_large_vocabulary(self.num_samples, self.num_units, self.target_vocab_size)

			# Creating learning rate variable, learning rate decay op, and global step for train op
			self.learning_rate_var, self.learning_rate_decay_op, self.global_step = ops.get_more_hyperparameters(self.learning_rate, self.learning_rate_decay_factor)

			# Builds the graph that computes inference and loss
			self.inference_and_loss()

			# Adding train op to the graph
			self.train_op()

			# Creating saver
			self.create_saver()

	def train(self):
		with tf.Session(graph=self.graph) as self.sess:
			init = tf.initialize_all_variables()
			self.sess.run(init)
			print('Graph Initialised')

			loss = 0.0
			current_step = 0
			previous_losses = []
			train_batches = BatchGenerator()

			while True:
				encoder_inputs, decoder_inputs, target_weights, bucket_id = train_batches.next()

				feed_dict = {}
				encoder_size, decoder_size = self.buckets[bucket_id]

				for l in xrange(encoder_size):
					feed_dict[self.encoder_inputs[l].name] = encoder_inputs[l]

				for l in xrange(decoder_size):
					feed_dict[self.decoder_inputs[l].name] = decoder_inputs[l]
					feed_dict[self.target_weights[l].name] = target_weights[l]

				last_target = self.decoder_inputs[decoder_size].name
				feed_dict[last_target] = np.zeros([self.batch_size], dtype=np.int32)

				_, step_loss = self.sess.run([self.updates[bucket_id], self.losses[bucket_id]], feed_dict=feed_dict)

				loss += step_loss / self.checkpoint_step
				current_step += 1

				if not current_step % self.checkpoint_step:
					print('Current learning rate: %.4f' % self.sess.run(self.learning_rate_var))
					print(' Loss at step %d: %f' % (current_step, step_loss))

					if len(previous_losses) > 2 and loss > max(previous_losses[-3:]):
						self.sess.run(self.learning_rate_decay_op)

					previous_losses.append(loss)
					self.save()
					loss = 0.0

	def save(self):
		self.saver.save(self.sess, os.path.join(self.checkpoint_dir, self.model_name), global_step=self.sess.run(self.global_step))
