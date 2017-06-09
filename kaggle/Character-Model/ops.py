import tensorflow as tf

def placeholder_input(num_unrollings, input_size, output_size):
	data = tf.placeholder(tf.float32, [None, num_unrollings, input_size])
	target = tf.placeholder(tf.float32, [None, num_unrollings, output_size])

	return data, target

def placeholder_dropout():
	dropout = tf.placeholder(tf.float32)

	return dropout

def weight_variable(shape):
	initial = tf.truncated_normal(shape=shape, stddev=0.1)
	var = tf.Variable(initial)

	return var

def bias_variable(shape):
	initial = tf.constant(0.1, shape=shape)
	var = tf.Variable(initial)

	return var
