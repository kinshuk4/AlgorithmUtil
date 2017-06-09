'''
===================================
Playing with Echocardiogram Dataset
===================================
'''

import csv
import numpy as np
from collections import Counter
import matplotlib.pyplot as plt

def load_echocardiogram(file_path = 'datasets/echocardiogram.data'):

	echocardiogram = {}
	
	with open(file_path, 'r') as input_file:
		csv_reader = csv.reader(input_file, delimiter=',')

		X = []

		for line in csv_reader:
			X.append(line[:13])

		echocardiogram['data'] = np.array(X)

	return echocardiogram

echocardiogram = load_echocardiogram()

X = echocardiogram['data'][:, [0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]]
y = echocardiogram['data'][:, 1]

# Removing entry with blank target
valid_target = (y!='?')

X = X[valid_target]
y = y[valid_target]

# Assigning mean age to missing ages
ages = X[:, 1]
mean_age = np.mean(X[ages != '?', 1].astype(float))
X[ages == '?', 1] = mean_age

indexes = X[:, 3]
mean_value = np.mean(X[indexes != '?', 3].astype(float))
X[indexes == '?', 3] = mean_value