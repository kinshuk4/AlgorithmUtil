import tensorflow as tf

def placeholder_input(batch_size, num_unrollings):
	data = tf.placeholder(tf.int32, (batch_size, num_unrollings))
	target = tf.placeholder(tf.int32, (batch_size, num_unrollings))

	return data, target

def embeddings(data, vocabulary_size, embedding_size):
	params = tf.Variable(tf.random_uniform((vocabulary_size, embedding_size), -1.0, 1.0))
	embedding = tf.nn.embedding_lookup(params, data)

	return embedding

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
