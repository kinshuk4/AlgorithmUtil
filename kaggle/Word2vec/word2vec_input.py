from __future__ import print_function

import os
import numpy as np
import zipfile
import random
from collections import Counter, deque
import tensorflow as tf

data_index = 0

def read_data(dataset_path, filename):
	'''Reading dataset as a list of words'''
	with zipfile.ZipFile(dataset_path + filename) as f:
			data = tf.compat.as_str(f.read(f.namelist()[0])).split()

	return data

def build_dataset(words, vocabulary_size):
	'''Creating vocabulary(index) of most common [vocabulary_size] words'''
	count = [['UNK', -1]]
	count.extend(Counter(words).most_common(vocabulary_size - 1))

	dictionary = dict()
	data = list()
	unk_count = 0

	for word, _ in count:
		dictionary[word] = len(dictionary)

	for word in words:
		if word in dictionary:
			index = dictionary[word]
		else:
			index = 0
			unk_count += 1
		data.append(index)

	count[0][1] = unk_count

	reverse_dictionary = dict(zip(dictionary.values(), dictionary.keys()))

	return data, count, dictionary, reverse_dictionary

def generate_batch(data, batch_size, num_skips, skip_window):
	'''Generating skip-gram training batch'''
	global data_index
	batch = np.ndarray(shape=(batch_size), dtype=np.int32)
	labels = np.ndarray(shape=(batch_size, 1), dtype=np.int32)
	span = 2 * skip_window + 1
	buffer = deque(maxlen=span)

	for _ in xrange(span):
		buffer.append(data[data_index])
		data_index = (data_index + 1) % len(data)

	for i in xrange(batch_size / num_skips):
		target = skip_window
		targets_to_avoid = [skip_window]

		for j in xrange(num_skips):
			while target in targets_to_avoid:
				target = random.randint(0, span - 1)

			targets_to_avoid.append(target)

			batch[i * num_skips + j] = buffer[skip_window]
			labels[i * num_skips + j, 0] = buffer[target]

		buffer.append(data[data_index])
		data_index = (data_index + 1) % len(data)

	return batch, labels

if __name__ == '__main__':
	dataset_path = 'dataset/'
	dataset = 'text8.zip'

	vocabulary_size = 50000
	batch_size = 8

	words = read_data(dataset_path, dataset)

	data, count, dictionary, reverse_dictionary = build_dataset(words, vocabulary_size)

	for num_skips, skip_window in [(2, 1), (4, 2)]:
		print('\ndata:', [reverse_dictionary[x] for x in data[data_index:data_index + batch_size]])

		batch, labels = generate_batch(data, batch_size, num_skips, skip_window)

		print('\nwith num_skips = %d and skip_window = %d' % (num_skips, skip_window))
		print('\tbatch:', [reverse_dictionary[x] for x in batch])
		print('\tlabels:', [reverse_dictionary[x] for x in labels.reshape(batch_size)])
