from __future__ import print_function
from __future__ import absolute_import

import tensorflow as tf

def encoder_decoder_input_placeholder(encoder_input_range, decoder_input_range):
	encoder_inputs = []
	decoder_inputs = []

	for i in xrange(encoder_input_range):
		encoder_inputs.append(tf.placeholder(tf.int32, shape=[None], name='encoder{0}'.format(i)))

	for i in xrange(decoder_input_range):
		decoder_inputs.append(tf.placeholder(tf.int32, shape=[None], name='decoder{0}'.format(i)))

	return encoder_inputs, decoder_inputs

def target_placeholder(decoder_inputs):
	return [decoder_inputs[i + 1] for i in xrange(len(decoder_inputs) - 1)]

def target_weight_placeholder(decoder_input_range):
	target_weights = []

	for i in xrange(decoder_input_range):
		target_weights.append(tf.placeholder(tf.float32, shape=[None], name='weight{0}'.format(i)))

	return target_weights

def handle_large_vocabulary(num_samples, size, target_vocab_size):
	output_projection = None
	softmax_loss_function = None

	if num_samples > 0 and num_samples < target_vocab_size:
		w = tf.get_variable('proj_w', [size, target_vocab_size])
		w_t = tf.transpose(w)
		b = tf.get_variable('proj_b', [target_vocab_size])

		output_projection = (w, b)

		def sampled_loss(inputs, labels):
			local_w_t = tf.cast(w_t, tf.float32)
			local_b = tf.cast(b, tf.float32)
			local_inputs = tf.cast(inputs, tf.float32)
			labels = tf.reshape(labels, [-1, 1])

			return tf.cast(tf.nn.sampled_softmax_loss(local_w_t, local_b, local_inputs, labels, num_samples, target_vocab_size), tf.float32)

		softmax_loss_function = sampled_loss

	return output_projection, softmax_loss_function

def get_more_hyperparameters(learning_rate, learning_rate_decay_factor):
	learning_rate_var = tf.Variable(float(learning_rate), trainable=False, dtype=tf.float32)
	learning_rate_decay_op = learning_rate_var.assign(learning_rate_var * learning_rate_decay_factor)
	global_step = tf.Variable(0, trainable=False)

	return learning_rate_var, learning_rate_decay_op, global_step

def embedding_seq2seq_with_attention(cell, source_vocab_size, target_vocab_size, num_units, output_projection):
	def seq2seq_f(encoder_inputs, decoder_inputs):
		return tf.nn.seq2seq.embedding_attention_seq2seq(encoder_inputs, decoder_inputs, cell, num_encoder_symbols=source_vocab_size, num_decoder_symbols=target_vocab_size, embedding_size=num_units, output_projection=output_projection, dtype=tf.float32)

	return seq2seq_f
