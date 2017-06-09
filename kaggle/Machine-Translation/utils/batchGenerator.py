from __future__ import print_function
from __future__ import absolute_import

import random
import numpy as np

import config
from utils.dataset import Dataset

class BatchGenerator():
	'''Generate Batches'''
	def __init__(self):
		self.batch_size = config.FLAGS.batch_size
		self.buckets = buckets = config.BUCKETS
		self.PAD_ID = config.PAD_ID
		self.GO_ID = config.GO_ID

		self.load_dataset()

		data_bucket_sizes = [len(self.data[b]) for b in xrange(len(buckets))]
		data_total_size = float(sum(data_bucket_sizes))

		self.data_buckets_scale = [sum(data_bucket_sizes[:i + 1]) / data_total_size for i in xrange(len(data_bucket_sizes))]

	def load_dataset(self):
		dataset = Dataset()
		self.data = dataset.data

	def next(self):
		random_number_01 = np.random.random_sample()
		bucket_id = min([i for i in xrange(len(self.data_buckets_scale)) if self.data_buckets_scale[i] > random_number_01])

		encoder_size, decoder_size = self.buckets[bucket_id]
		encoder_inputs, decoder_inputs = [], []

		for _ in xrange(self.batch_size):
			encoder_input, decoder_input = random.choice(self.data[bucket_id])

			encoder_pad = [self.PAD_ID] * (encoder_size - len(encoder_input))
			encoder_inputs.append(list(reversed(encoder_input + encoder_pad)))

			decoder_pad = [self.PAD_ID] * (decoder_size - len(decoder_input) - 1)
			decoder_inputs.append([self.GO_ID] + decoder_input + decoder_pad)

		batch_encoder_inputs, batch_decoder_inputs, batch_target_weights = [], [], []

		for length_idx in xrange(encoder_size):
			batch_encoder_inputs.append(np.array([encoder_inputs[batch_idx][length_idx] for batch_idx in xrange(self.batch_size)], dtype=np.int32))

		for length_idx in xrange(decoder_size):
			batch_decoder_inputs.append(np.array([decoder_inputs[batch_idx][length_idx] for batch_idx in xrange(self.batch_size)], dtype=np.int32))

			batch_target_weight = np.ones(self.batch_size, dtype=np.float32)
			
			for batch_idx in xrange(self.batch_size):
				if length_idx < decoder_size - 1:
					target = decoder_inputs[batch_idx][length_idx + 1]

				if length_idx == decoder_size - 1 or target == self.PAD_ID:
					batch_target_weight[batch_idx] = 0.0

				batch_target_weights.append(batch_target_weight)

		return batch_encoder_inputs, batch_decoder_inputs, batch_target_weights, bucket_id
