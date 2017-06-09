from __future__ import print_function

import math
import tensorflow as tf
from sklearn.manifold import TSNE
from word2vec_input import *
from word2vec_plot import *

dataset_path = 'dataset/'
dataset = 'text8.zip'
vocabulary_size = 50000
batch_size = 128
embedding_size = 128
skip_window = 1
num_skips = 2
num_sampled = 64
num_steps = 100001
num_points = 400

def run(param):
	# Building my graph
	graph = tf.Graph()

	with graph.as_default():
		# Input data
		train_dataset = tf.placeholder(tf.int32, shape=[batch_size])
		train_labels = tf.placeholder(tf.int32, shape=[batch_size, 1])

		# Variables
		embeddings = tf.Variable(tf.random_uniform([vocabulary_size, embedding_size], -1.0, 1.0))
		softmax_weights = tf.Variable(tf.truncated_normal([vocabulary_size, embedding_size], stddev=1.0/math.sqrt(embedding_size)))
		softmax_biases = tf.Variable(tf.zeros([vocabulary_size]))

		# Model
		embed = tf.nn.embedding_lookup(embeddings, train_dataset)

		# Loss
		loss = tf.reduce_mean(tf.nn.sampled_softmax_loss(softmax_weights, softmax_biases, embed, train_labels, num_sampled, vocabulary_size))

		# Optimizer
		optimizer = tf.train.AdagradOptimizer(1.0).minimize(loss)

		# Normalizing the final embeddings
		norm = tf.sqrt(tf.reduce_sum(tf.square(embeddings), 1, keep_dims=True))
		normalized_embeddings = embeddings / norm

		# Creating saver to write embeddings
		saver = tf.train.Saver()

	# Getting dataset
	words = read_data(dataset_path, dataset)
	data, count, dictionary, reverse_dictionary = build_dataset(words, vocabulary_size)

	if param == 'training':
		# Training word embeddings
		with tf.Session(graph=graph) as sess:
			# Initializing all variables
			init = tf.initialize_all_variables()
			sess.run(init)
			print('Graph Initialized')

			average_loss = 0

			for step in xrange(num_steps):
				batch_data, batch_labels = generate_batch(data, batch_size, num_skips, skip_window)

				feed_dict = {train_dataset: batch_data, train_labels: batch_labels}

				_, l = sess.run([optimizer, loss], feed_dict=feed_dict)

				average_loss += l

				if step % 2000 == 0:
					if step > 0:
						average_loss /= 2000

					print('Average loss at step %d: %f' % (step, average_loss))

			saver.save(sess, 'dataset/embeddings')
	else:
		# Visualizing word embeddings
		with tf.Session(graph=graph) as sess:
			saver.restore(sess, 'dataset/embeddings')
			print('Embeddings restored')

			final_embeddings = sess.run(normalized_embeddings)

			tsne = TSNE(perplexity=30, n_components=2, init='pca', n_iter=5000)
			two_d_embeddings = tsne.fit_transform(final_embeddings[1:num_points+1, :])

			words = [reverse_dictionary[i] for i in xrange(1, num_points+1)]

			plot(two_d_embeddings, words)

			plt.show()

if __name__ == '__main__':
	run('training')
	run('visualization')
