'''
==========================
Playing with Flags Dataset
==========================
'''

import csv
import numpy as np
from collections import Counter
from sklearn import preprocessing
from sklearn import cross_validation
from sklearn import tree
from sklearn import metrics
from common.fn import *

with open('datasets/flag.data', 'r') as input_file:
	csv_reader = csv.reader(input_file, delimiter=',')

	raw_data = []

	for line in csv_reader:
		raw_data.append(line)

data = []
target = []

for sample in raw_data:
	data.append(sample[7:])
	target.append(sample[6])

X = np.array(data)
y = np.array(target)

le = preprocessing.LabelEncoder()
X[:, 10] = le.fit_transform(X[:, 10])
X[:, -2] = le.fit_transform(X[:, -2])
X[:, -1] = le.fit_transform(X[:, -1])

X = X.astype(int)
y = y.astype(int)

sss = cross_validation.StratifiedShuffleSplit(y, 3, test_size=0.1, random_state=42)

for train_index, test_index in sss:
	X_train, X_test = X[train_index], X[test_index]
	y_train, y_test = y[train_index], y[test_index]

	clf = tree.DecisionTreeClassifier()
	clf.fit(X_train, y_train)

	y_predict = clf.predict(X_test)

	print metrics.accuracy_score(y_predict, y_test)
	# print metrics.classification_report(y_predict, y_test)