import numpy as np
import tensorflow as tf
from digit_recognizer_input import *
from digit_recognizer_model import *
from digit_plot import *

num_labels = 10
learning_rate = 1e-3
batch_size = 64
num_steps = 40001

def reformat(dataset, labels=None):
	# Reshaping dataset according to model
	dataset = np.reshape(dataset, (-1, 28, 28, 1)).astype(np.float32)

	if labels is not None:
		labels = (labels[:, None].astype(int) == np.arange(num_labels)).astype(np.float32)

	return dataset, labels

def run_training():
	# Building my model
	graph = tf.Graph()

	with graph.as_default():
		# Creating placeholder for images and labels
		images_placeholder, labels_placeholder = placeholder_input()

		# Creating placeholder for dropout
		keep_prob = tf.placeholder(tf.float32)

		# Builds a graph that computes inference		
		logits = inference(images_placeholder, keep_prob)

		# Adding loss op to the graph
		loss = loss_op(logits, labels_placeholder)

		# Adding train op to the graph
		train = train_op(loss, learning_rate)

		# Adding accuracy op to the graph
		score = accuracy(logits, labels_placeholder)

		# Creating saver to write training checkpoints
		saver = tf.train.Saver()

	# Training my model
	with tf.Session(graph=graph) as sess:
		# Initializing all variables
		init = tf.initialize_all_variables()
		sess.run(init)
		print 'Graph Initialized'

		# Loading dataset
		print 'Loading dataset'
		dataset = load_digits(validation_size=0.01)
		print 'Dataset loaded'
		train_dataset = dataset['train_dataset']
		validation_dataset = dataset['validation_dataset']

		# Reshaping images and labels for training and validation dataset
		train_images, train_labels = reformat(train_dataset.data, train_dataset.target)
		validation_data, validation_labels = reformat(validation_dataset.data, validation_dataset.target)

		validation_feed_dict = {images_placeholder: validation_data, labels_placeholder: validation_labels, keep_prob: 1.0}

		for step in xrange(num_steps):
			offset = (step * batch_size) % (train_images.shape[0] - batch_size)

			batch_data = train_images[offset:(offset + batch_size), :]
			batch_labels = train_labels[offset:(offset + batch_size), :]

			feed_dict = {images_placeholder: batch_data, labels_placeholder: batch_labels, keep_prob: 0.5}

			l, _ = sess.run([loss, train], feed_dict=feed_dict)

			if step % 500 == 0:
				# Saving the sess
				saver.save(sess, 'dataset/my-model', global_step=step)

				print 'Minibatch loss at step %d: %f' % (step, l)
				print '  Training Accuracy: %.3f' % sess.run(score, feed_dict={images_placeholder: batch_data, labels_placeholder: batch_labels, keep_prob: 1.0})
				print '  Validation Accuracy: %.3f' % sess.run(score, feed_dict=validation_feed_dict)

def make_predictions():
	# Building my model
	graph = tf.Graph()

	with graph.as_default():
		# Creating placeholder for images and labels
		images_placeholder, _ = placeholder_input()

		# Creating placeholder for dropout
		keep_prob = tf.placeholder(tf.float32)

		# Builds a graph that computes inference
		logits = inference(images_placeholder, keep_prob)

		# Creating saver to read training checkpoints
		saver = tf.train.Saver()

	# Making predictions using saved model
	with tf.Session(graph=graph) as sess:
		# Restore variables from disk
		saver.restore(sess, 'dataset/my-model-40000')
		print 'Model restored'

		# Loading dataset
		print 'Loading dataset'
		test = get_test_dataset()
		print 'Dataset loaded'

		predictions = []

		for i, image in enumerate(test.data):
			test_data, _ = reformat(image)

			# Predicting the label one by one
			prediction = sess.run(logits, feed_dict={images_placeholder: test_data, keep_prob: 1.0})
			predictions.append(np.argmax(prediction))

			if i % 1000 == 0:
				print 'Predicting %d labels complete' % i

		# Writing the predictions to a file for submission
		write_output(predictions, 'output.csv')

		# Plotting a random prediction to verify
		num1 = np.random.randint(len(predictions))
		draw_digit(test.data[num1].astype(np.float32), predictions[num1])

		plt.show()

if __name__ == '__main__':
	run_training()		
	make_predictions()
