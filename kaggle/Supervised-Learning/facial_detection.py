'''
====================================
Facial Keypoints Detection - Kaggle
====================================
'''

import csv
import numpy as np
import matplotlib.pyplot as plt
from sklearn import svm
from sklearn import cross_validation
from sklearn import grid_search
from sklearn import preprocessing
from sklearn.externals import joblib
from common.fn import *

file_path = 'datasets/facial/'
file_name = 'training.csv'

def load_facial_data(file_path, file_name, max_rows=1):
	facial_data = {}

	with open(file_path+file_name, 'rb') as csv_file:
		reader = csv.reader(csv_file)
		header = reader.next()
		target_names = header[:-1]
		
		X, y = [], []

		while max_rows:
			raw_data = reader.next()
			image_data = raw_data[-1].split()
			X.append(image_data)
			y.append(raw_data[:-1])

			max_rows -= 1

		facial_data['data'] = np.array(X)
		facial_data['targets'] = np.array(y)
		facial_data['target_names'] = np.array(target_names)

	return facial_data

def draw_image(data, target=None):
	plt.figure()

	plt.imshow(np.reshape(data, (96, 96)), cmap='gray')

	if target is not None: 
		plt.plot(target[0::2], target[1::2], 'o')

	return plt

facial_data = load_facial_data(file_path, file_name, 353)
X = facial_data['data'].astype(float)

# Handling missing values (210, 350)
y = facial_data['targets']

for column in xrange(y.shape[1]):
	column_values = y[:, column]
	mean_column_values = np.mean(y[column_values != '', column].astype(float))
	y[column_values == '', column] = mean_column_values

y = y.astype(float)
target_names = facial_data['target_names']

X_train, X_test, y_train, y_test = cross_validation.train_test_split(X, y, test_size=0.01, random_state=42)
scaler = preprocessing.StandardScaler().fit(X)
X_scaled = scaler.transform(X)
# '''
regressor = svm.SVR(C=0.01, kernel='linear')
regressor.fit(X_train, y_train[:, 0])
joblib.dump(regressor, file_path+'model/svm.pkl')
# '''
# regressor = joblib.load(file_path+'model/svm.pkl')

print regressor.score(X_train, y_train[:, 0])
print regressor.score(X_test, y_test[:, 0])