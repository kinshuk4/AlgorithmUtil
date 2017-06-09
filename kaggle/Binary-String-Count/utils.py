import os
import numpy as np
from random import shuffle
from six.moves import cPickle as pickle

class BinaryCount:
	pass

class Dataset():
	'''Generate or load dataset'''
	def __init__(self, config, dataset_type):
		self.config = config
		self.num_unrollings = config.num_unrollings
		self.dataset_type = dataset_type
		self.dataset_dir = config.dataset_dir
		self.train_size = config.train_size

		self.load_dataset()

	def load_dataset(self):
		file_name = os.path.join(self.dataset_dir, self.dataset_type + '_' + str(self.num_unrollings) + '_' + str(self.train_size) + '.pkl')

		if os.path.isfile(file_name):
			# Load the dataset
			dataset = self.load(file_name)
			self.data = dataset.data
			self.target = dataset.target
		else:
			# Generate the dataset
			dataset = self.generate()
			train_dataset, test_dataset = self.split(dataset)
			self.save(train_dataset, 'train_dataset' + '_' + str(self.num_unrollings) + '_' + str(self.train_size) + '.pkl')
			self.save(test_dataset, 'test_dataset' + '_' + str(self.num_unrollings) + '_' + str(self.train_size) + '.pkl')

			if self.dataset_type == 'train_dataset':
				self.data = train_dataset.data
				self.target = train_dataset.target
			else:
				self.data = test_dataset.data
				self.target = test_dataset.target

	def generate(self):
		X = ['{0:b}'.format(i).zfill(self.num_unrollings) for i in xrange(2**self.num_unrollings)]
		shuffle(X)
		X = np.array([map(int, i) for i in X])
		X = np.reshape(X, [len(X), self.num_unrollings, 1])

		y = []

		for i in X:
			count = np.sum(i[:, 0])
			buffer = np.zeros(self.num_unrollings + 1)
			buffer[count] = 1
			y.append(buffer)

		dataset = BinaryCount()
		dataset.X = X
		dataset.y = np.array(y)
		
		return dataset

	def save(self, dataset, file_name):
		with open(os.path.join(self.dataset_dir, file_name), 'wb') as f:
			pickle.dump(dataset, f)

	def load(self, file_name):
		with open(file_name, 'rb') as f:
			dataset = pickle.load(f)

		return dataset

	def split(self, dataset):
		X = dataset.X
		y = dataset.y

		train_dataset = BinaryCount()
		train_dataset.data = X[:self.train_size]
		train_dataset.target = y[:self.train_size]

		test_dataset = BinaryCount()
		test_dataset.data = X[self.train_size:]
		test_dataset.target = y[self.train_size:]

		return train_dataset, test_dataset

class BatchGenerator():
	'''Generate train batches'''
	def __init__(self, config):
		self.config = config
		self.batch_size = config.batch_size
		self.num_unrollings = config.num_unrollings
		self.target_size = config.num_unrollings + 1
		self.batch_dataset_type = config.batch_dataset_type
		self.cursor = 0

		self.load_dataset()

	def load_dataset(self):
		dataset = Dataset(self.config, self.batch_dataset_type)
		self.train_data = dataset.data
		self.train_target = dataset.target

	def next(self):
		data = self.train_data[self.cursor:self.cursor + self.batch_size]
		target = self.train_target[self.cursor:self.cursor + self.batch_size]
		self.cursor = (self.cursor + self.batch_size) % len(self.train_data)

		return data, target
