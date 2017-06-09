import time
import numpy as np
from similarity import Similarity

class Recommendation:
	'Various recommendation systems'

	def __init__(self, dataset):
		'''
		Structure of dataset
		dataset = {
			key1: {
				inner_key1: inner_value1,
				inner_key2: inner_value2,
				.......
			},
			key2: {
				inner_key1: inner_value1,
				inner_key2: inner_value2,
				.......
			},
			.......
		}
		'''
		self.dataset = dataset

	def collaborativeRecommendation(self, key, n=3):
		'''Return list of n top match scores along with inner keys'''

		dataset = self.dataset
		weighted_inner_values = {}
		total_scores = {}

		for other_key in dataset:
			if other_key == key:
				continue

			# Fetching common inner keys to calculate similarity score
			common_inner_keys = self.fetchCommonInnerKeys(key, other_key)

			# If there is no common inner key, skip this other keys
			if len(common_inner_keys) == 0:
				continue

			x = [dataset[key][inner_key] for inner_key in common_inner_keys]
			y = [dataset[other_key][inner_key] for inner_key in common_inner_keys]

			# Finding similarity score
			sim = Similarity()
			score = sim.pearson(x, y)

			# Ignoring scores of zero or below
			if score <= 0:
				continue

			for inner_key in dataset[other_key]:
				if inner_key not in dataset[key] or dataset[key][inner_key] == 0:
					# Weighted sum of value times similarity score
					weighted_inner_values.setdefault(inner_key, 0)
					weighted_inner_values[inner_key] += score * dataset[other_key][inner_key]

					# Sum of similarity score
					total_scores.setdefault(inner_key, 0)
					total_scores[inner_key] += score

		scores = [(weighted_inner_values[inner_key]/total_scores[inner_key], inner_key) for inner_key in weighted_inner_values]

		# Sorting the list so that highest score appear at the top
		scores.sort()
		scores.reverse()

		return scores[0:n]

	def contentBasedRecommendation(self, content_based_filtered_dataset, key, n=3):
		'''Return list of n top match scores along with inner keys'''

		dataset = self.dataset
		weighted_inner_values = {}
		total_scores = {}

		# Loop over inner keys of current key
		for inner_key in dataset[key]:
			# Loop over keys similiar to inner key
			for (score, other_key) in content_based_filtered_dataset[inner_key]:
				# Ignore if other_key is present in dataset of current key
				if other_key in dataset[key]:
					continue

				if score <= 0:
					continue

				# Weighted sum of value times similarity score
				weighted_inner_values.setdefault(other_key, 0)
				weighted_inner_values[other_key] += score * dataset[key][inner_key]
				
				# Sum of similarity score
				total_scores.setdefault(other_key, 0)
				total_scores[other_key] += score

		scores = [(weighted_inner_values[key]/total_scores[key], key) for key in weighted_inner_values]

		# Sorting the list so that highest score appear at the top
		scores.sort()
		scores.reverse()

		return scores[0:n]

	def contentBasedFiltering(self, key, n=3):
		'''Return list of n top match scores along with other keys'''

		dataset = self.dataset
		scores = []

		for other_key in dataset:
			if other_key == key:
				continue

			# Fetching common inner keys to calculate similarity score
			common_inner_keys = self.fetchCommonInnerKeys(key, other_key)

			# If there is no common inner key, skip this other keys
			if len(common_inner_keys) == 0:
				continue

			x = [dataset[key][inner_key] for inner_key in common_inner_keys]
			y = [dataset[other_key][inner_key] for inner_key in common_inner_keys]

			# Appending the similarity score to a list
			sim = Similarity()
			scores.append((sim.pearson(x, y), other_key))

		# Sorting the list so the highest score appear at the top
		scores.sort()
		scores.reverse()

		return scores[0:n]

	def contentBasedFilteringAll(self, n=50, verbose=False):
		'''Return dict of all keys and corresponding list of n top match scores along with other key'''

		print 'Building item based filtered dataset'
		dataset = self.dataset
		result = {}

		C = 0
		old = int(time.time())
		epoch_left = (len(dataset)/100) + 1
		difference = []

		for key in dataset:

			# Time keeping
			C += 1
			if C % 100 == 0:
				epoch_left -= 1
				new = int(time.time())
				difference.append(new-old)
				old = new
				time_left = int(np.mean(difference)) * epoch_left

				minutes = time_left / 60
				seconds = time_left % 60

				if verbose:
					if (minutes):
						print "%d minutes %d seconds left" % (time_left/60, time_left%60)
					else:
						print "%d seconds left" % seconds

			scores = self.contentBasedFiltering(key, n)
			result[key] = scores

		print 'Filtered dataset built'
		return result

	# Helper functions

	def fetchCommonInnerKeys(self, key1, key2):

		dataset = self.dataset

		inner_keys = [inner_key for inner_key in dataset[key1] if inner_key in dataset[key2]]

		return inner_keys

