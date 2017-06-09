import numpy as np
import tensorflow as tf

image_size = 28
num_labels = 10

def placeholder_input():
	images_placeholder = tf.placeholder(tf.float32, shape=(None, image_size, image_size, 1))
	labels_placeholder = tf.placeholder(tf.float32, shape=(None, num_labels))

	return images_placeholder, labels_placeholder

def weight_variable(shape, wd=None):
	initial = tf.truncated_normal(shape, stddev=0.1)
	var = tf.Variable(initial)

	if wd is not None:
	    weight_decay = tf.mul(tf.nn.l2_loss(var), wd)
	    tf.add_to_collection('losses', weight_decay)

	return var

def bias_variable(shape, bd=None):
	initial = tf.constant(0.1, shape=shape)
	var = tf.Variable(initial)

	if bd is not None:
		bias_decay = tf.mul(tf.nn.l2_loss(var), bd)
		tf.add_to_collection('losses', bias_decay)

	return var

def conv2d(x, W):
	return tf.nn.conv2d(x, W, strides=[1, 1, 1, 1], padding='SAME')

def max_pool_2x2(x):
	return tf.nn.max_pool(x, ksize=[1, 2, 2, 1], strides=[1, 2, 2, 1], padding='SAME')

def inference(images, keep_prob):
	# Convolutional layer 1
	with tf.name_scope('conv1'):
		weights = weight_variable([5, 5, 1, 32])
		biases = bias_variable([32])

		conv1 = tf.nn.relu(conv2d(images, weights) + biases)

	pool1 = max_pool_2x2(conv1)

	# Convolutional layer 2
	with tf.name_scope('conv2'):
		weights = weight_variable([5, 5, 32, 64])
		biases = bias_variable([64])

		conv2 = tf.nn.relu(conv2d(pool1, weights) + biases)

	pool2 = max_pool_2x2(conv2)

	# Fully connected layer
	with tf.name_scope('fc1'):
		weights = weight_variable([7 * 7 * 64, 1024], 5e-4)
		biases = bias_variable([1024], 5e-4)

		pool2_flat = tf.reshape(pool2, [-1, 7 * 7 * 64])
		fc1 = tf.nn.relu(tf.matmul(pool2_flat, weights) + biases)

	# Dropout layer
	fc1_drop = tf.nn.dropout(fc1, keep_prob)

	# Fully connected layer
	with tf.name_scope('fc2'):
		weights = weight_variable([1024, 512], 5e-4)
		biases = bias_variable([512], 5e-4)

		fc2 = tf.nn.relu(tf.matmul(fc1_drop, weights) + biases)

	# Dropout layer
	fc2_drop = tf.nn.dropout(fc2, keep_prob)

	# Linear layer
	with tf.name_scope('linear'):
		weights = weight_variable([512, 10], 5e-4)
		biases = bias_variable([10], 5e-4)

		logits = tf.matmul(fc2_drop, weights) + biases

	return logits

def loss_op(logits, labels):
	cross_entropy = tf.reduce_mean(tf.nn.softmax_cross_entropy_with_logits(logits, labels))

	tf.add_to_collection('losses', cross_entropy)
	loss = tf.add_n(tf.get_collection('losses'))

	return loss

def train_op(loss, learning_rate):
	optimizer = tf.train.AdamOptimizer(learning_rate)
	train = optimizer.minimize(loss)

	return train

def accuracy(predictions, labels):
	correct_prediction = tf.equal(tf.argmax(predictions, 1), tf.argmax(labels, 1))

	return tf.reduce_mean(tf.cast(correct_prediction, tf.float32))

if __name__ == '__main__':
	pass

