import os
import zipfile
import numpy as np
import tensorflow as tf
from collections import Counter

class Dataset():
	'''Load dataset'''
	def __init__(self, config, dataset_type):
		self.config = config
		self.file_name = os.path.join(config.dataset_dir, config.dataset + '.' + dataset_type + '.txt')

		self.load_dataset()

	def load_dataset(self):
		self.load()
		self.build_vocabulary()
		self.convert_words_to_wordids()

		self.data = self.wordids

	def load(self):
		'''Reading dataset as a list of words'''
		with open(self.file_name, 'rb') as f:
			words = tf.compat.as_str(f.read()).split()

		self.words = words

	def build_vocabulary(self):
		counter = Counter(self.words)
		count_pairs = sorted(counter.items(), key=lambda x: (-x[1], x[0]))
		words, _ = list(zip(*count_pairs))
		self.words2id = dict(zip(words, range(len(words))))
		self.id2words = dict(zip(range(len(words)), words))

	def convert_words_to_wordids(self):
		self.wordids = [self.words2id[word] for word in self.words]

	def convert_wordids_to_words(self, wordids):
		words = [self.id2words[id] for id in wordids]

		return words

class BatchGenerator():
	'''Generate Batches'''
	def __init__(self, config):
		self.config = config
		self.batch_size = batch_size = config.batch_size
		self.num_unrollings = config.num_unrollings
		self.batch_dataset_type = config.batch_dataset_type

		self.load_dataset()
		self.dataset_size = dataset_size = len(self.data)

		segment = dataset_size / batch_size
		self.cursor = [offset * segment for offset in xrange(batch_size)]

	def load_dataset(self):
		dataset = Dataset(self.config, self.batch_dataset_type)
		self.data = dataset.data

	def sequence(self, position):
		'''Generate a sequence from a cursor position'''
		sequence = []

		for _ in xrange(self.num_unrollings + 1):
			sequence.append(self.data[self.cursor[position]])
			self.cursor[position] = (self.cursor[position] + 1) % self.dataset_size

		return sequence

	def next(self):
		'''Generate next batch from the data'''
		batch = []

		for position in xrange(self.batch_size):
			batch.append(self.sequence(position))

		return np.array(batch)	
