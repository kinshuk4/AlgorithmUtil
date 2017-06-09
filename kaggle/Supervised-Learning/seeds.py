'''
=================================
Playing with Seeds(Wheat) dataset
=================================
'''

print __doc__

import numpy as np
import matplotlib.pyplot as plt
from sklearn import cross_validation
from sklearn import linear_model
from sklearn import pipeline
from sklearn import preprocessing
from sklearn import neighbors
from sklearn import metrics
from sklearn import decomposition

def load_seeds(filepath='datasets/seeds.data.txt'):
	
	seeds = {}

	raw_data = np.genfromtxt(filepath)

	data = raw_data[:, :-1]
	target = [int(t)-1 for t in raw_data[:, -1]]
	target_names = ['Kama', 'Rosa', 'Canadian']
	features = ['Area', 'Perimeter', 'Compactness', 'Length of kernel', 'Width of kernel', 'Asymmetry coefficient', 'Length of kernel groove']

	seeds['data'] = data
	seeds['target'] = np.array(target)
	seeds['target_names'] = np.array(target_names)
	seeds['features'] = np.array(features)

	return seeds

def plot_seeds(data, target, feature_at_x_axis, feature_at_y_axis, target_names):

	features = ['Area', 'Perimeter', 'Compactness', 'Length of kernel', 'Width of kernel', 'Asymmetry coefficient', 'Length of kernel groove']

	for t, marker, color, target_name in zip(xrange(3), '<ox', 'rgb', target_names):
		plt.scatter(data[target == t, feature_at_x_axis], data[target == t, feature_at_y_axis], marker=marker, color=color, label=target_name)

	plt.xlabel(features[feature_at_x_axis])
	plt.ylabel(features[feature_at_y_axis])
	plt.xticks(())
	plt.yticks(())
	plt.legend(loc=2)

	plt.show()

seeds = load_seeds()
X = seeds['data']
y = seeds['target']
target_names = seeds['target_names']

# plot_seeds(X, y, 0, 1, target_names)

X_train, X_test, y_train, y_test = cross_validation.train_test_split(X, y, test_size=0.2, random_state=0)

scaler = preprocessing.StandardScaler().fit(X_train)
logistic_regression = linear_model.LogisticRegression(penalty='l1')
nearest_neighbors = neighbors.KNeighborsClassifier(n_neighbors=5)

pipe1 = pipeline.make_pipeline(scaler, logistic_regression)
pipe2 = pipeline.make_pipeline(scaler, nearest_neighbors)

scores1 = cross_validation.cross_val_score(pipe1, X_train, y_train, cv=4)
print "Average score of logistic regression on validation set %s" %scores1.mean()

prediction1 = cross_validation.cross_val_predict(pipe1, X_test, y_test)
print "Score of logistic regression on test set %s" %metrics.accuracy_score(y_test, prediction1)

scores2 = cross_validation.cross_val_score(pipe2, X_train, y_train, cv=5)
print "Average score of k-nearest neighbors on validation set %s" %scores2.mean()

prediction2 = cross_validation.cross_val_predict(pipe2, X_test, y_test)
print "Score of k-nearest neighbors on test set %s" %metrics.accuracy_score(y_test, prediction2)

pca = decomposition.PCA(n_components=2)
X_transformed = pca.fit(X).transform(X)

# plot_seeds(X_transformed, y, 0, 1, target_names)

