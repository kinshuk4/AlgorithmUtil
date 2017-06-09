import numpy as np
import tensorflow as tf
from six.moves import cPickle as pickle

image_size = 28
num_labels = 10
num_channels = 1
batch_size = 16
patch_size = 5
depth = 16
num_hidden = 64
num_steps = 1001

def reformat(dataset, labels):
	dataset = np.reshape(dataset, (-1, image_size, image_size, num_channels)).astype(np.float32)
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
		del save
	
	train_dataset, train_labels = reformat(train_dataset, train_labels)
	valid_dataset, valid_labels = reformat(valid_dataset, valid_labels)
	test_dataset, test_labels = reformat(test_dataset, test_labels)

	class dataset(object):
		pass

	dataset.train_dataset = train_dataset
	dataset.train_labels = train_labels
	dataset.valid_dataset = valid_dataset
	dataset.valid_labels = valid_labels
	dataset.test_dataset = test_dataset
	dataset.test_labels = test_labels

	return dataset

def placeholder_input():
	images_placeholder = tf.placeholder(tf.float32, shape=(None, image_size, image_size, num_channels))
	labels_placeholder = tf.placeholder(tf.float32, shape=(None, num_labels))

	return images_placeholder, labels_placeholder

def weight_variable(shape):
	initial = tf.truncated_normal(shape, stddev=0.1)
	return tf.Variable(initial)

def bias_variable(shape):
	initial = tf.constant(0.1, shape=shape)
	return tf.Variable(initial)

def conv2d(x, W):
	return tf.nn.conv2d(x, W, strides=[1, 1, 1, 1], padding='SAME')

def max_pool_2x2(x):
  	return tf.nn.max_pool(x, ksize=[1, 2, 2, 1], strides=[1, 2, 2, 1], padding='SAME')

def inference(images):
	# Conv1 Layer
	with tf.name_scope('conv1'):
		weights = weight_variable([patch_size, patch_size, num_channels, depth])
		biases = bias_variable([depth])

		conv1 = tf.nn.relu(conv2d(images, weights) + biases)

	# Pool1
	pool1 = max_pool_2x2(conv1)

	# Conv2 Layer
	with tf.name_scope('conv2'):
		weights = weight_variable([patch_size, patch_size, depth, depth])
		biases = bias_variable([depth])

		conv2 = tf.nn.relu(conv2d(pool1, weights) + biases)

	# Pool2
	pool2 = max_pool_2x2(conv2)

	# Fully connected Layer
	with tf.name_scope('fc1'):
		weights = weight_variable([7 * 7 * depth, num_hidden])
		biases = bias_variable([num_hidden])

		pool2_flat = tf.reshape(pool2, [-1, 7 * 7 * depth])
		fc1 = tf.nn.relu(tf.matmul(pool2_flat, weights) + biases)

	# Linear Layer
	with tf.name_scope('linear'):
		weights = weight_variable([num_hidden, num_labels])
		biases = bias_variable([num_labels])

		logits = tf.matmul(fc1, weights) + biases

	return logits

def loss_op(logits, labels):
	loss = tf.reduce_mean(tf.nn.softmax_cross_entropy_with_logits(logits, labels))

	return loss

def train_op(loss, learning_rate):
	optimizer = tf.train.GradientDescentOptimizer(learning_rate)
	train = optimizer.minimize(loss)

	return train

def accuracy(predictions, labels):
	return (100.0 * np.sum(np.argmax(predictions, 1) == np.argmax(labels, 1)) / predictions.shape[0])

def run_training():

	with tf.Graph().as_default():
		# Creating placeholder for input and labels
		images_placeholder, labels_placeholder = placeholder_input()

		# Build a graph that computes inference
		logits = inference(images_placeholder)

		# Adding loss Op to the graph
		loss = loss_op(logits, labels_placeholder)

		# Adding training Op to the graph
		train = train_op(loss, 0.05)

		with tf.Session() as sess:
			# Initializing all variables
			init = tf.initialize_all_variables()
			sess.run(init)

			dataset = load_dataset()
			train_dataset = dataset.train_dataset
			train_labels = dataset.train_labels

			valid_feed_dict = {images_placeholder: dataset.valid_dataset, labels_placeholder: dataset.valid_labels}
			test_feed_dict = {images_placeholder: dataset.test_dataset, labels_placeholder: dataset.test_labels}

			for step in xrange(num_steps):
				offset = (step * batch_size) % (train_labels.shape[0] - batch_size)

				batch_data = train_dataset[offset:(offset + batch_size), :, :, :]
				batch_labels = train_labels[offset:(offset + batch_size), :]

				feed_dict = {images_placeholder : batch_data, labels_placeholder : batch_labels}

				predictions, l, _ = sess.run([logits, loss, train], feed_dict=feed_dict)

				if step % 50 == 0:
					print 'Minibatch loss at step %d: %f' % (step, l)
					print '  Minibatch accuracy: %.1f%%' % accuracy(predictions, batch_labels)
					print '  Validation accuracy: %.1f%%' % accuracy(sess.run(logits, feed_dict=valid_feed_dict), dataset.valid_labels)
					print '  Test accuracy: %.1f%%' % accuracy(sess.run(logits, feed_dict=test_feed_dict), dataset.test_labels)

run_training()