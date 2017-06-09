'''
============================
Playing with Abalone dataset
============================
'''

import numpy as np
import csv
import matplotlib.pyplot as plt
from sklearn import preprocessing
from sklearn import cross_validation
from sklearn import svm
from sklearn import metrics
from sklearn import decomposition
from common.fn import *

def load_abalone(file_path='datasets/Abalone/', file_name='abalone.data'):
	abalone = {}
	feature_names = ['Sex', 'Length', 'Diameter', 'Height', 'Whole weight', 'Shucked weight', 'Viscera weight', 'Shell weight']

	with open(file_path+file_name, 'r') as csv_file:
		reader = csv.reader(csv_file, delimiter=',')

		X = []
		y = []

		for row in reader:
			X.append(row[:-1])
			y.append(row[-1])

		abalone['data'] = np.array(X)
		abalone['target'] = np.array(y)
		abalone['feature_names'] = np.array(feature_names)

	return abalone

abalone = load_abalone()
X = abalone['data']
y = abalone['target']

# Convert sex feature to numerical value
le = preprocessing.LabelEncoder()
le.fit(X[:, 0])
# print le.classes_
X[:, 0] = le.transform(X[:, 0])

X = X.astype(float)
y = y.astype(int)

X_train, X_test, y_train, y_test = cross_validation.train_test_split(X, y, test_size=0.10, random_state=42)

scaler = preprocessing.StandardScaler()
scaler.fit(X_train)

X_train_scaled = scaler.transform(X_train)
X_test_scaled = scaler.transform(X_test)

pca = decomposition.PCA(n_components=2)
pca.fit(X_train)

X_train_transformed = pca.transform(X_train_scaled)
X_test_transformed = pca.transform(X_test_scaled)

clf = svm.SVC(C=0.1, kernel='rbf')
clf.fit(X_train_transformed, y_train)

# print clf.score(X_train_transformed, y_train)
print clf.score(X_test_transformed, y_test)

'''
y_predict = clf.predict(X_test_transformed)

print metrics.accuracy_score(y_test, y_predict)
print metrics.classification_report(y_test, y_predict)
print metrics.confusion_matrix(y_test, y_predict)
'''