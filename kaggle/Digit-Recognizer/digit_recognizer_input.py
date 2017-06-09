import os
import sys
import csv
import numpy as np
from six.moves import cPickle as pickle
from sklearn import cross_validation

file_path = 'dataset/'
train_file = 'train.csv'
test_file = 'test.csv'

class digits:
	pass

def read_train_file(size):
	data = []
	target = []

	with open(file_path + train_file, 'rb') as f:
		csv_reader = csv.reader(f, delimiter=',')

		# Skipping first line
		csv_reader.next()

		for row in csv_reader:
			target.append(row[0])
			data.append(row[1:])

		desired_size = int(size * len(data))

		data = data[:desired_size]
		target = target[:desired_size]

		train = digits()
		train.data = np.array(data)
		train.target = np.array(target)

	return train

def read_test_file(size):
	data = []

	with open(file_path + test_file, 'rb') as f:
		csv_reader = csv.reader(f, delimiter=',')

		# Skipping first line
		csv_reader.next()

		for row in csv_reader:
			data.append(row)

		desired_size = int(size * len(data))

		data = data[:desired_size]

		test = digits()
		test.data = np.array(data)

	return test

def write_output(content, file_name):
	with open(file_path + file_name, 'wb') as f:
		csv_writer = csv.writer(f, delimiter=',')
		csv_writer.writerow(['ImageId', 'Label'])

		for i, label in enumerate(content):
			csv_writer.writerow([i+1, label])

def write_pickle(content, file_name):
	with open(file_path + file_name, 'wb') as f:
		pickle.dump(content, f)

def read_pickle(file_name):
	with open(file_path + file_name, 'rb') as f:
		content = pickle.load(f)

	return content

def get_train_dataset(size):

	train_pickle_file = 'train_' + str(size) + '.pickle'

	if os.path.exists(file_path + train_pickle_file):
		train = read_pickle(train_pickle_file)
	else:
		train = read_train_file(size)
		write_pickle(train, train_pickle_file)

	return train

def get_test_dataset(size=1):

	test_pickle_file = 'test_' + str(size) + '.pickle'
	
	if os.path.exists(file_path + test_pickle_file):
		test = read_pickle(test_pickle_file)
	else:
		test = read_test_file(size)
		write_pickle(test, test_pickle_file)

	return test

def split_train_dataset(train_dataset, test_size=0.1):

	X_train, X_test, y_train, y_test = cross_validation.train_test_split(train_dataset.data, train_dataset.target, test_size=test_size, random_state=42)

	train = digits()
	validation = digits()
	train.data = X_train
	train.target = y_train
	validation.data = X_test
	validation.target = y_test

	return train, validation

def load_digits(train_size=1, validation_size=0.1):
	train_dataset = get_train_dataset(train_size)
	train, validation = split_train_dataset(train_dataset, validation_size)

	digits_dataset = {'train_dataset': train, 'validation_dataset': validation}

	return digits_dataset

if __name__ == '__main__':
	dataset = load_digits(0.01, 0.1)
	train = dataset['train_dataset']
	validation = dataset['validation_dataset']
	test = get_test_dataset(0.01) 
	print train.data.shape
	print train.target.shape
	print validation.data.shape
	print validation.target.shape
	print test.data.shape

