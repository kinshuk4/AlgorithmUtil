from __future__ import print_function

import os
import string
import zipfile
import numpy as np
import tensorflow as tf

def char2id(char):
	first_letter = ord(string.ascii_lowercase[0])

	if char in string.ascii_lowercase:
		return ord(char) - first_letter + 1
	elif char == ' ':
		return 0
	else:
		print('Unexpected character: %s' % char)
		return 0

def id2char(charid):
	first_letter = ord(string.ascii_lowercase[0])

	if charid > 0:
		return chr(charid + first_letter - 1)
	else:
		return ' '

def logprob(prediction, label):
	return np.sum(np.multiply(label, -np.log(prediction)))

class Dataset:
	'''Load dataset'''
	def __init__(self, config, dataset_type):
		self.config = config
		self.dataset_type = dataset_type
		self.file_name = os.path.join(config.dataset_dir, config.dataset)
		self.validation_size = config.validation_size

		self.load_dataset()

	def load_dataset(self):
		self.load()
		train_text, validation_text = self.split()

		if self.dataset_type == 'train_dataset':
			self.data = train_text
		else:
			self.data = validation_text

	def load(self):
		'''Reading dataset as a string'''
		with zipfile.ZipFile(self.file_name) as f:
			text = tf.compat.as_str(f.read(f.namelist()[0]))

			self.text = text

	def split(self):
		validation_text = self.text[:self.validation_size]
		train_text = self.text[self.validation_size:]

		return train_text, validation_text

class BatchGenerator():
	'''Generate batches'''
	def __init__(self, config):
		self.config = config
		self.batch_size = config.batch_size
		self.num_unrollings = config.num_unrollings
		self.input_size = len(string.ascii_lowercase) + 1
		self.batch_dataset_type = config.batch_dataset_type

		self.load_dataset()
		self.size = len(self.data)

		assert self.size % self.batch_size == 0, 'Train size should be divisible by batch size'
		segment = self.size / self.batch_size

		self.cursor = [offset * segment for offset in xrange(self.batch_size)]

	def load_dataset(self):
		dataset = Dataset(self.config, self.batch_dataset_type)
		self.data = dataset.data

	def sequence(self, position):
		'''Generate a sequence from a cursor position'''
		sequence = np.zeros(shape=(self.num_unrollings + 1, self.input_size), dtype=np.float)

		for i in xrange(self.num_unrollings + 1):
			sequence[i, char2id(self.data[self.cursor[position]])] = 1.0
			self.cursor[position] = (self.cursor[position] + 1) % self.size

		return sequence

	def next(self):
		'''Generate next batch from the data'''
		batch = []

		for position in xrange(self.batch_size):
			batch.append(self.sequence(position))

		return np.array(batch)
		