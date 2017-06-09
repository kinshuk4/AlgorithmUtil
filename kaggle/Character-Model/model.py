from __future__ import print_function

import tensorflow as tf

from ops import *
from utils import *

class Model:
	'''RNN LSTM neural network'''
	def __init__(self, config):
		self.config = config
		self.num_units = config.num_units
		self.num_hidden_layers = config.num_hidden_layers
		self.learning_rate = config.learning_rate
		self.train_input_keep_prob = config.input_keep_prob
		self.train_output_keep_prob = config.output_keep_prob
		self.epochs = config.epochs
		self.batch_size = config.batch_size
		self.num_unrollings = config.num_unrollings
		self.checkpoint_epoch = config.checkpoint_epoch
		self.checkpoint_dir = config.checkpoint_dir
		self.model_name = config.model_name
		self.input_size = len(string.ascii_lowercase) + 1
		self.output_size = self.input_size
		self.restore_model = config.restore_model
		self.accuracy_dataset_type = config.validation_dataset_type

		self.build_model()

	def inference(self):
		# Creating LSTM layer
		cell = tf.nn.rnn_cell.LSTMCell(self.num_units)
		cell = tf.nn.rnn_cell.DropoutWrapper(cell, self.input_keep_prob, self.output_keep_prob)
		cell = tf.nn.rnn_cell.MultiRNNCell([cell] * self.num_hidden_layers)

		# Creating Unrolled LSTM
		hidden, _ = tf.nn.dynamic_rnn(cell, self.data, dtype=tf.float32)

		# Creating output at each time step
		reshaped_hidden = tf.reshape(hidden, (-1, self.num_units))
		prediction = tf.nn.softmax(tf.matmul(reshaped_hidden, self.weight) + self.bias)

		self.prediction = prediction

	def loss_op(self):
		reshaped_prediction = tf.reshape(self.prediction, (self.batch_size, self.num_unrollings, self.output_size))
		cross_entropy = -tf.reduce_sum(self.target * tf.log(reshaped_prediction))

		self.loss = cross_entropy

	def train_op(self):
		optimizer = tf.train.AdamOptimizer(self.learning_rate)

		self.optimizer = optimizer.minimize(self.loss)

	def create_saver(self):
		saver = tf.train.Saver()

		self.saver = saver

	def accuracy(self):
		validation_batches = BatchGenerator(self.config)

		validation_log_prob = 0

		for _ in xrange(validation_batches.size):
			validation_data = validation_batches.next()
			
			validation_prediction = self.predict(validation_data)
			reshaped_validation_prediction = np.reshape(validation_prediction, (1, 1, validation_data.shape[2]))

			validation_log_prob += logprob(reshaped_validation_prediction, validation_data[:, 1:])

		print('Validation set perplexity: %.2f' % float(np.exp(validation_log_prob / validation_data.size)))

	def build_model(self):
		self.graph = tf.Graph()

		with self.graph.as_default():
			# Creating placeholder for data and target
			self.data, self.target = placeholder_input(self.num_unrollings, self.input_size, self.output_size)

			# Creating placeholder for LSTM dropout
			self.input_keep_prob = placeholder_dropout()
			self.output_keep_prob = placeholder_dropout()

			# Creating variables for output layer
			self.weight = weight_variable([self.num_units, self.output_size])
			self.bias = bias_variable([self.output_size])

			# Builds the graph that computes inference
			self.inference()

			# Adding loss op to the graph
			self.loss_op()

			# Adding train op to the graph
			self.train_op()

			# Creating saver
			self.create_saver()

	def train(self):
		with tf.Session(graph=self.graph) as self.sess:
			init = tf.initialize_all_variables()
			self.sess.run(init)
			print('Graph Initialized')

			train_batches = BatchGenerator(self.config)

			steps_in_one_epoch = ((train_batches.size / self.batch_size) / self.num_unrollings)
			checkpoint_step = self.checkpoint_epoch * steps_in_one_epoch

			for step in xrange(self.epochs * steps_in_one_epoch + 1):
				train_data = train_batches.next()

				feed_dict = {self.data: train_data[:, :-1], self.target: train_data[:, 1:], self.input_keep_prob: self.train_input_keep_prob, self.output_keep_prob: self.train_output_keep_prob}	

				_, l = self.sess.run([self.optimizer, self.loss], feed_dict=feed_dict)

				if not step % checkpoint_step:
					epoch = step / checkpoint_step
					self.save(epoch)
					print('Loss at Epoch %d: %f' % (epoch, l))

	def predict(self, data):
		with tf.Session(graph=self.graph) as self.sess:
			self.load()

			feed_dict = {self.data: data[:, :-1], self.input_keep_prob: 1.0, self.output_keep_prob: 1.0}

			prediction = self.sess.run(self.prediction, feed_dict=feed_dict)

			return prediction

	def save(self, global_step):
		self.saver.save(self.sess, os.path.join(self.checkpoint_dir, self.model_name), global_step=global_step)

	def load(self):
		try:
			self.saver.restore(self.sess, os.path.join(self.checkpoint_dir, self.model_name + '-' + str(self.restore_model)))
		except ValueError:
			print('Restore model does not exist!')
			exit()
