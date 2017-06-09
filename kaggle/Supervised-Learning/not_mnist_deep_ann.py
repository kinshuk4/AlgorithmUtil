import numpy as np
import tensorflow as tf
from six.moves import cPickle as pickle

image_size = 28
num_labels = 10
batch_size = 128
hidden1 = 1024
learning_rate = 0.5
num_steps = 3001

def reformat(dataset, labels):
	dataset = np.reshape(dataset, (-1, image_size * image_size)).astype(np.float32)
	labels = (labels[:, None] == np.arange(num_labels)).astype(np.float32)

	return dataset, labels

def load_dataset():
	pickle_file = 'notMNIST.pickle'

	with open(pickle_file, 'rb') as f:
		save = pickle.load(f)
		train_dataset = save['train_dataset']
		train_labels = save['train_labels']
		valid_dataset = save['valid_dataset']
		valid_labels = save['valid_labels']
		test_dataset = save['test_dataset']
		test_labels = save['test_labels']

	train_dataset, train_labels = reformat(train_dataset, train_labels)
	valid_dataset, valid_labels = reformat(valid_dataset, valid_labels)
	test_dataset, test_labels = reformat(test_dataset, test_labels)

	dataset = {'train_dataset': train_dataset, 'train_labels': train_labels, 'valid_dataset': valid_dataset, 'valid_labels': valid_labels, 'test_dataset': test_dataset, 'test_labels': test_labels}

	return dataset

def placeholder_input():
	images_placeholder = tf.placeholder(tf.float32, shape=(None, image_size * image_size))
	labels_placeholder = tf.placeholder(tf.float32, shape=(None, num_labels))

	return images_placeholder, labels_placeholder

def inference(images, hidden1_units):
	# Hidden 1
	with tf.name_scope('hidden1'):
		weights = tf.Variable(tf.truncated_normal([image_size * image_size, hidden1_units]))
		biases = tf.Variable(tf.zeros([hidden1_units]))

		hidden1 = tf.nn.relu(tf.matmul(images, weights) + biases)

	# Linear
	with tf.name_scope('linear'):
		weights = tf.Variable(tf.truncated_normal([hidden1_units, num_labels]))
		biases = tf.Variable(tf.zeros([num_labels]))

		logits = tf.matmul(hidden1, weights) + biases

	return logits

def loss_op(logits, labels):
	loss = tf.reduce_mean(tf.nn.softmax_cross_entropy_with_logits(logits, labels))

	return loss

def training(loss, learning_rate):
	optimizer = tf.train.GradientDescentOptimizer(learning_rate)
	train_op = optimizer.minimize(loss)

	return train_op

def accuracy(predictions, labels):
	return (100.0 * np.sum(np.argmax(predictions, 1) == np.argmax(labels, 1)) / predictions.shape[0])

def run_training():
	dataset = load_dataset()
	train_dataset = dataset['train_dataset']
	train_labels = dataset['train_labels']

	with tf.Graph().as_default():
		# Creating placeholder for input and labels
		images_placeholder, labels_placeholder = placeholder_input()

		# Build a graph that computes inference
		logits = inference(images_placeholder, hidden1)

		# Adding loss Op to the graph
		loss = loss_op(logits, labels_placeholder)

		# Adding training Op to the graph
		train_op = training(loss, learning_rate)

		# Create a session to run Ops
		sess = tf.Session()

		# Initializing variables
		init = tf.initialize_all_variables()
		sess.run(init)

		valid_feed_dict = {images_placeholder: dataset['valid_dataset'], labels_placeholder: dataset['valid_labels']}
		test_feed_dict = {images_placeholder: dataset['test_dataset'], labels_placeholder: dataset['test_labels']}

		for step in xrange(num_steps):
			offset = (step * batch_size) % (train_labels.shape[0] - batch_size)

			batch_data = train_dataset[offset:(offset + batch_size), :]
			batch_labels = train_labels[offset:(offset + batch_size), :]

			feed_dict = {images_placeholder: batch_data, labels_placeholder: batch_labels}

			predictions, l, _ = sess.run([logits, loss, train_op], feed_dict=feed_dict)

			if step % 500 == 0:
				print 'Minibatch loss at step %d: %f' % (step, l)
				print '  Training accuracy: %.1f%%' % accuracy(predictions, batch_labels)
				print '  Validation accuracy: %.1f%%' % accuracy(sess.run(logits, feed_dict=valid_feed_dict), dataset['valid_labels'])
				print '  Test accuracy: %.1f%%' % accuracy(sess.run(logits, feed_dict=test_feed_dict), dataset['test_labels'])

run_training()