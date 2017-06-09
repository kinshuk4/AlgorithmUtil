from math import sqrt

class Similarity:
	'Various algorithms to calculate similarity score'

	def euclidean(self, x, y):
		'''Calculates euclidean score between two lists of same length'''

		sum_square = sum([pow(x[i]-y[i], 2) for i in xrange(len(x))])

		score = 1/(1+sqrt(sum_square))

		return score

	def pearson(self, x, y):
		'''Calculates pearson correlation coefficient between two lists of same length'''

		n = len(x)
		vals = range(n)

		# Simple sums
		sum_x = sum([float(x[i]) for i in vals])
		sum_y = sum([float(y[i]) for i in vals])

		# Sum up the squares
		sum_x_square = sum([x[i]**2.0 for i in vals])
		sum_y_square = sum([y[i]**2.0 for i in vals])

		# Sum up the products
		product_sum = sum([x[i]*y[i] for i in vals])

		# Calculate Pearson score
		num = product_sum-(sum_x*sum_y/n)
		den = sqrt((sum_x_square-pow(sum_x,2)/n)*(sum_y_square-pow(sum_y,2)/n))

		if den == 0: 
			return 0
		
		score = num/den
		
		return score

