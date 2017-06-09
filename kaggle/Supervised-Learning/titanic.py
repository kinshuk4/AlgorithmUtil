'''
=================
Titanic - Kaggle
=================
'''

import csv
from collections import Counter
import numpy as np
from sklearn import preprocessing
from sklearn import tree
from sklearn import cross_validation
from sklearn import metrics
from common.fn import *

file_path = 'datasets/titanic/'
file_name = 'train.csv'

def load_titanic(file_path, file_name):
	titanic = {}

	with open(file_path+file_name, 'rb') as csv_file:
		reader = csv.reader(csv_file, delimiter=',', quotechar='"')

		first_row = reader.next()

		X, y = [], []
		for row in reader:
			X.append(row)
			y.append(row[1])

		titanic['data'] = np.array(X)
		titanic['target'] = np.array(y)
		titanic['feature_names'] = np.array(first_row)

		return titanic

titanic = load_titanic(file_path, file_name)

X = titanic['data'][:, [2, 4, 5]]
y = titanic['target']
feature_names = titanic['feature_names'][[2, 4, 5]]

# Filling missing age
ages = X[:, 2]
mean_age = np.mean(X[ages != '', 2].astype(np.float))
X[ages == '', 2] = mean_age

# Converting sex into real values
le = preprocessing.LabelEncoder()
le.fit(X[:, 1])
X[:, 1] = le.transform(X[:, 1])

X = X.astype(float)
y = y.astype(float)

X_train, X_test, y_train, y_test = cross_validation.train_test_split(X, y, test_size=0.10, random_state=42)

clf = tree.DecisionTreeClassifier()
clf.fit(X_train, y_train)
y_predict = clf.predict(X_test)

for i in xrange(len(y_predict)):
	if y_predict[i] == 0:
		if X_test[i][0] == 1 and X_test[i][1] == 0:
			y_predict[i] = 1
		if X_test[i][1] == 0 and X_test[i][2] < 20:
			y_predict[i] = 1

print metrics.accuracy_score(y_test, y_predict)