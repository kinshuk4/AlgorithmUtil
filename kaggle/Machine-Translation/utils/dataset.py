from __future__ import print_function
from __future__ import absolute_import

import os
import sys
import gzip
import tarfile

import tensorflow as tf
from tensorflow.python.platform import gfile

import config

class Dataset():
	'''Load dataset'''
	def __init__(self):
		self.max_train_data_size = config.FLAGS.max_train_data_size
		self.dataset_dir = config.FLAGS.dataset_dir
		self.en_vocabulary_size = config.FLAGS.en_vocabulary_size
		self.fr_vocabulary_size = config.FLAGS.fr_vocabulary_size
		self.file_name = os.path.join(config.FLAGS.dataset_dir, config.FLAGS.dataset)

		self.buckets = config.BUCKETS

		self.PAD_ID = config.PAD_ID
		self.GO_ID = config.GO_ID
		self.EOS_ID = config.EOS_ID
		self.UNK_ID = config.UNK_ID

		self._START_VOCAB = config.START_VOCAB
		self._WORD_SPLIT = config.WORD_SPLIT
		self._DIGIT_RE = config.DIGIT_RE

		self.load_dataset()

	def load_dataset(self):
		self.extract()
		self.build_vocabularies()
		self.convert_data_to_ids()
		self.read_data()

	def extract(self):
		train_path = os.path.join(self.dataset_dir, 'giga-fren.release2.fixed')

		if not (gfile.Exists(train_path + '.fr') and gfile.Exists(train_path + '.en')):
			try:
				with tarfile.open(self.file_name, 'r') as corpus_tar:
					print('Extracting tar file %s' % self.file_name)
					corpus_tar.extractall(self.dataset_dir)
			except IOError:
				print('Place the dataset into data folder')
				exit()

			self.gunzip_file(train_path + '.en.gz', train_path + '.en')
			self.gunzip_file(train_path + '.fr.gz', train_path + '.fr')

		self.train_path = train_path

	def gunzip_file(self, gz_path, new_path):
		print('Unpacking %s to %s' % (gz_path, new_path))
		with gzip.open(gz_path, 'rb') as gz_file:
			with open(new_path, 'wb') as new_file:
				for line in gz_file:
					new_file.write(line)

	def build_vocabularies(self):
		self.en_vocabulary_path = en_vocabulary_path = os.path.join(self.dataset_dir, 'vocab%d.en' % self.en_vocabulary_size)
		self.en_data_path = en_data_path = self.train_path + '.en'

		if not gfile.Exists(en_vocabulary_path):
			self.build_vocabulary(en_vocabulary_path, en_data_path, self.en_vocabulary_size)

		self.fr_vocabulary_path = fr_vocabulary_path = os.path.join(self.dataset_dir, 'vocab%d.fr' % self.fr_vocabulary_size)
		self.fr_data_path = fr_data_path = self.train_path + '.fr'

		if not gfile.Exists(fr_vocabulary_path):
			self.build_vocabulary(fr_vocabulary_path, fr_data_path, self.fr_vocabulary_size)


	def build_vocabulary(self, vocabulary_path, data_path, max_vocabulary_size):
		print('Creating vocabulary %s from data %s' % (vocabulary_path, data_path))
		vocab = {}
		with gfile.GFile(data_path, 'rb') as f:
			counter = 0
			for line in f:
				counter += 1
				if not counter % 100000:
					print(' Processing line %d' % counter)

				line = tf.compat.as_bytes(line)
				tokens = self.basic_tokenizer(line)

				for w in tokens:
					word = self._DIGIT_RE.sub(b'0', w)
					if word in vocab:
						vocab[word] += 1
					else:
						vocab[word] = 1

			vocab_list = self._START_VOCAB + sorted(vocab, key=vocab.get, reverse=True)

			if len(vocab_list) > max_vocabulary_size:
				vocab_list = vocab_list[:max_vocabulary_size]

			with gfile.GFile(vocabulary_path, 'wb') as vocab_file:
				for w in vocab_list:
					vocab_file.write(w + b'\n')

	def basic_tokenizer(self, sentence):
		words = []

		for space_separated_sentence in sentence.strip().split():
			words.extend(self._WORD_SPLIT.split(space_separated_sentence))

		return [w for w in words if w]

	def convert_data_to_ids(self):
		self.en_train_ids_path = en_train_ids_path = self.train_path + ('.ids%d.en' % self.en_vocabulary_size)
		
		if not gfile.Exists(en_train_ids_path):
			self.data_to_ids(self.en_data_path, en_train_ids_path, self.en_vocabulary_path)

		self.fr_train_ids_path = fr_train_ids_path = self.train_path + ('.ids%d.fr' % self.fr_vocabulary_size)

		if not gfile.Exists(fr_train_ids_path):
			self.data_to_ids(self.fr_data_path, fr_train_ids_path, self.fr_vocabulary_path)

	def data_to_ids(self, data_path, target_path, vocabulary_path):
		vocab = self.load_vocabulary(vocabulary_path)
		print('Converting data %s into ids in %s' % (data_path, target_path))	
		with gfile.GFile(data_path, 'rb') as data_file:
			with gfile.GFile(target_path, 'w') as f:
				counter = 0
				for line in data_file:
					counter += 1
					if not counter % 100000:
						print(' Processing line %d' % counter)

					word_ids = self.sentence_to_ids(line, vocab)	

					f.write(" ".join([str(word_id) for word_id in word_ids]) + '\n')

	def load_vocabulary(self, vocabulary_path):
		vocab_list = []

		with gfile.GFile(vocabulary_path, 'rb') as f:
			vocab_list.extend(f.readlines())

		vocab_list = [line.strip() for line in vocab_list]
		vocab = dict([(x, y) for (y, x) in enumerate(vocab_list)])

		return vocab

	def sentence_to_ids(self, line, vocab):
		tokens = self.basic_tokenizer(line)

		return [vocab.get(self._DIGIT_RE.sub(b'0', w), self.UNK_ID) for w in tokens]

	def read_data(self):
		data = [[] for _ in self.buckets]

		with tf.gfile.GFile(self.en_train_ids_path, 'r') as source_file:
			with tf.gfile.GFile(self.fr_train_ids_path, 'r') as target_file:
				source, target = source_file.readline(), target_file.readline()
				counter = 0

				while source and target and (not self.max_train_data_size or counter < self.max_train_data_size):
					counter += 1

					if not counter % 100000:
						print(' reading data line %d' % counter)
						sys.stdout.flush()

					source_ids = [int(x) for x in source.split()]
					target_ids = [int(x) for x in target.split()]

					target_ids.append(self.EOS_ID)

					for bucket_id, (source_size, target_size) in enumerate(self.buckets):
						if len(source_ids) < source_size and len(target_ids) < target_size:
							data[bucket_id].append([source_ids, target_ids])
							break

					source, target = source_file.readline(), target_file.readline()

		self.data = data
