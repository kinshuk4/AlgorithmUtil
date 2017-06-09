'''
====================================
Playing with Car Evaluation Dataset
====================================
'''
import numpy as np
import csv
from collections import Counter
from sklearn import preprocessing
from sklearn import cross_validation
from sklearn import tree
from sklearn import metrics

def load_car_evaluation(file_path = 'datasets/car.data'):
	car_evaluation = {}

	with open(file_path, 'r') as csv_file:
		reader = csv.reader(csv_file, delimiter=',')

		X = []
		y = []

		for row in reader:
			X.append(row[:-1])
			y.append(row[-1])

	le = preprocessing.LabelEncoder()
	target = le.fit_transform(y)

	car_evaluation['target'] = target

	X = np.array(X)
	samples = len(X)

	le1 = preprocessing.LabelEncoder()
	feature1 = le1.fit_transform(X[:, 0]).reshape(samples, 1)

	feature2 = le1.transform(X[:, 1]).reshape(samples, 1)

	le2 = preprocessing.LabelEncoder()
	feature3 = le2.fit_transform(X[:, 2]).reshape(samples, 1)

	le3 = preprocessing.LabelEncoder()
	feature4 = le3.fit_transform(X[:, 3]).reshape(samples, 1)

	le4 = preprocessing.LabelEncoder()
	feature5 = le4.fit_transform(X[:, 4]).reshape(samples, 1)

	le5 = preprocessing.LabelEncoder()
	feature6 = le5.fit_transform(X[:, 5]).reshape(samples, 1)

	samples = np.hstack([feature1, feature2, feature3, feature4, feature5, feature6])

	ohe = preprocessing.OneHotEncoder()
	data = ohe.fit_transform(samples)

	car_evaluation['data'] = data

	return car_evaluation

car_evaluation = load_car_evaluation()
X = car_evaluation['data']
y = car_evaluation['target']

clf = tree.DecisionTreeClassifier()

skf = cross_validation.StratifiedShuffleSplit(y, 2, test_size=0.10, random_state=42)

for train_index, test_index in skf:

	X_train, X_test = X[train_index], X[test_index]
	y_train, y_test = y[train_index], y[test_index]

	clf.fit(X_train, y_train)

	y_predict = clf.predict(X_test)

	print metrics.accuracy_score(y_test, y_predict)
	print metrics.classification_report(y_test, y_predict)
	print metrics.confusion_matrix(y_test, y_predict)