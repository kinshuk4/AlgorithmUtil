import tensorflow as tf

def placeholder_input(num_unrollings, output_size):
	data = tf.placeholder(tf.float32, [None, num_unrollings, 1])
	target = tf.placeholder(tf.float32, [None, output_size])

	return data, target

def weight_variable(shape):
	initial = tf.truncated_normal(shape=shape)
	var = tf.Variable(initial)

	return var

def bias_variable(shape):
	initial = tf.constant(0.1, shape=shape)
	var = tf.Variable(initial)

	return var
