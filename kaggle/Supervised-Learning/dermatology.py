'''
================================
Playing with Dermatology Dataset
================================
'''

import csv
import numpy as np
from collections import Counter
from sklearn import cross_validation
from sklearn import tree
from sklearn import linear_model
from sklearn import naive_bayes
from sklearn import metrics

def load_dermatology(file_path = 'datasets/dermatology.data'):

	dermatology = {}

	with open(file_path, 'r') as input_file:
		csv_reader = csv.reader(input_file, delimiter=',')

		X = []
		y = []

		for line in csv_reader:
			X.append(line[:-1])
			y.append(line[-1])

		dermatology['data'] = np.array(X)
		dermatology['target'] = np.array(y)

	return dermatology

dermatology = load_dermatology()
X = dermatology['data']
y = dermatology['target']

# Filling missing age
ages = X[:, -1]
mean_age = np.mean(X[ages != '?', -1].astype(float))
X[ages == '?', -1] = mean_age

sss = cross_validation.StratifiedShuffleSplit(y, 3, test_size=0.1, random_state=42)

clf = linear_model.LogisticRegression(C=0.1, penalty='l2')

for train_index, test_index in sss:
	X_train, X_test = X[train_index].astype(int), X[test_index].astype(int)
	y_train, y_test = y[train_index].astype(int), y[test_index].astype(int)

	clf.fit(X_train, y_train)

	y_predict = clf.predict(X_test)

	print metrics.accuracy_score(y_test, y_predict)
	print metrics.classification_report(y_test, y_predict)
	print metrics.confusion_matrix(y_test, y_predict)
	print
	print