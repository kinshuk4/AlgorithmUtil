from __future__ import print_function

import os
import tensorflow as tf

from ops import *
from utils import *

class Model():
	'''RNN LSTM neural network'''
	def __init__(self, config):
		self.config = config
		self.batch_size = config.batch_size
		self.num_unrollings = config.num_unrollings
		self.vocabulary_size = config.vocabulary_size
		self.embedding_size = config.embedding_size
		self.num_units = config.num_units
		self.num_hidden_layers = config.num_hidden_layers
		self.learning_rate = config.learning_rate
		self.checkpoint_epoch = config.checkpoint_epoch
		self.epochs = config.epochs
		self.train_input_keep_prob = config.input_keep_prob
		self.train_output_keep_prob = config.output_keep_prob
		self.checkpoint_dir = config.checkpoint_dir
		self.model_name = config.model_name

		self.build_model()

	def inference(self):
		# Creating LSTM layer
		cell = tf.nn.rnn_cell.LSTMCell(self.num_units)
		cell = tf.nn.rnn_cell.DropoutWrapper(cell, self.input_keep_prob, self.output_keep_prob)
		cell = tf.nn.rnn_cell.MultiRNNCell([cell] * self.num_hidden_layers)

		# Creating Unrolled LSTM
		hidden, _ = tf.nn.dynamic_rnn(cell, self.embedding, dtype=tf.float32)

		# Creating output at each time step
		reshaped_hidden = tf.reshape(hidden, (-1, self.num_units))
		logit = tf.matmul(reshaped_hidden, self.weight) + self.bias

		self.logit = logit

	def loss_op(self):
		loss = tf.nn.seq2seq.sequence_loss_by_example([self.logit], [self.target], [tf.ones((self.batch_size * self.num_unrollings))])

		self.loss = tf.reduce_sum(loss) / self.batch_size

	def train_op(self):
		optimizer = tf.train.AdamOptimizer(self.learning_rate)

		self.optimizer = optimizer.minimize(self.loss)

	def create_saver(self):
		saver = tf.train.Saver()

		self.saver = saver

	def build_model(self):
		self.graph = tf.Graph()

		with self.graph.as_default():
			# Creating placeholder for data
			self.data, self.target = placeholder_input(self.batch_size, self.num_unrollings)

			# Converting data to embeddings
			self.embedding = embeddings(self.data, self.vocabulary_size, self.embedding_size)

			# Creating placeholder for LSTM dropout
			self.input_keep_prob = self.output_keep_prob = placeholder_dropout()

			# Creating variables for output layer
			self.weight = weight_variable([self.num_units, self.vocabulary_size])
			self.bias = bias_variable([self.vocabulary_size])

			# Builds the graph that computes inference
			self.inference()	

			# Adding loss op to the graph
			self.loss_op()

			# Adding train op to the grpah
			self.train_op()

			# Creating saver
			self.create_saver()

	def train(self):
		with tf.Session(graph=self.graph) as self.sess:
			init = tf.initialize_all_variables()
			self.sess.run(init)
			print('Graph Initialized')

			train_batches = BatchGenerator(self.config)

			steps_in_one_epoch = ((train_batches.dataset_size / self.batch_size) / self.num_unrollings)
			checkpoint_step = self.checkpoint_epoch * steps_in_one_epoch

			for step in xrange(self.epochs * steps_in_one_epoch + 1):
				train_data = train_batches.next()

				feed_dict = {self.data: train_data[:, :-1], self.target: train_data[:, 1:], self.input_keep_prob: self.train_input_keep_prob, self.output_keep_prob: self.train_output_keep_prob}

				_, l = self.sess.run([self.optimizer, self.loss], feed_dict=feed_dict)

				if not step % checkpoint_step:
					epoch = step / checkpoint_step
					self.save(epoch)
					print('Loss at Epoch %d: %f' % (epoch, l))

	def save(self, global_step):
		self.saver.save(self.sess, os.path.join(self.checkpoint_dir, self.model_name), global_step=global_step)
