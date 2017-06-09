'''
=========================
Playing with Iris Dataset
=========================
'''

print __doc__

import numpy as np
import matplotlib.pyplot as plt
from sklearn import datasets
from sklearn import preprocessing
from sklearn import cross_validation
from sklearn import linear_model
from sklearn import neighbors
from sklearn import pipeline
from sklearn import decomposition

def load_iris(filepath='datasets/iris.data.txt'):
	iris = {}
	data = []
	target = []
	target_names = []

	with open(filepath) as iris_data:
		for line in iris_data:
			sample = [float(feature) for feature in line.split("\n")[0].split(",")[:-1]]
			target_name = line.split("\n")[0].split(",")[-1][5:]
			if len(sample):
				data.append(sample)
			if len(target_name):
				if target_name not in target_names:
					target_names.append(target_name)
				target.append(target_names.index(target_name))

	iris['data'] = np.array(data)
	iris['target'] = np.array(target)
	iris['target_names'] = np.array(target_names)

	return iris

def plot_iris(data, target, x_feature, y_feature, target_names):

	features = ['Sepal Length', 'Sepal Width', 'Petal Length', 'Petal Width']

	x_axis = features.index(x_feature)
	y_axis = features.index(y_feature)

	for t, marker, color, target_name in zip(xrange(3), '<ox', 'rgb', target_names):
		plt.scatter(data[target == t, x_axis], data[target == t, y_axis], marker=marker, color=color, label=target_name)

	plt.title('Iris Dataset')
	plt.xlabel(x_feature)
	plt.ylabel(y_feature)
	plt.xticks(())
	plt.yticks(())
	plt.legend()

	plt.show()

iris = load_iris()
X = iris['data']
y = iris['target']
target_names = iris['target_names']

'''
print 'Matching the downloaded iris dataset with inbuilt iris dataset in scikit-learn'
inbuilt_iris = datasets.load_iris()
print (X == inbuilt_iris.data).all()
print (y == inbuilt_iris.target).all()
print (target_names == inbuilt_iris.target_names).all()
'''

X_train, X_test, y_train, y_test = cross_validation.train_test_split(X, y, test_size=0.2, random_state=0)

# Visualising the dataset taking two features at a time
x_feature = 'Sepal Length'
# x_feature = 'Sepal Width'
# x_feature = 'Petal Length'
# x_feature = 'Petal Width'

# y_feature = 'Sepal Length'
y_feature = 'Sepal Width'
# y_feature = 'Petal Length'
# y_feature = 'Petal Width'

# plot_iris(X, y, x_feature, y_feature, target_names)

scaler = preprocessing.StandardScaler().fit(X_train)

logistic_regression = linear_model.LogisticRegression(penalty='l1')
pipe1 = pipeline.make_pipeline(scaler, logistic_regression)

nearest_neighbors = neighbors.KNeighborsClassifier(n_neighbors=5)
pipe2 = pipeline.make_pipeline(scaler, nearest_neighbors)

pipe1.fit(X_train, y_train)
print "Logistic Regression score %s" %pipe1.score(X_test, y_test)

pipe2.fit(X_train, y_train)
print "k-nearest neighbors score %s" %pipe2.score(X_test, y_test)

# Converting the dataset into two dimension to actually visualise the data by maintaining high variance.
pca = decomposition.PCA(n_components=2)
X_transformed = pca.fit(X).transform(X)

# plot_iris(X_transformed, y, x_feature, y_feature, target_names)

