'''
===========================
Playing with Boston Dataset
===========================
'''

import numpy as np
from sklearn import datasets
from sklearn import cross_validation
from sklearn import preprocessing
import matplotlib.pyplot as plt
from sklearn import pipeline
from sklearn import ensemble
from sklearn import metrics

def load_boston(filepath = 'datasets/boston.data'):
	boston = {}

	features = [float(feature.strip()) for feature in open(filepath).read().split() if feature and feature.strip() != '\\']
	samples = np.reshape(features, (506, 14))
	boston['data'] = samples[:, :-1]
	boston['target'] = samples[:, -1]
	boston['feature_names'] = np.array(['CRIM', 'ZN', 'INDUS', 'CHAS', 'NOX', 'RM', 'AGE', 'DIS', 'RAD', 'TAX', 'PTRATIO', 'B', 'LSTAT'])

	return boston

boston = load_boston()

X = boston['data']
y = boston['target']
feature_names = boston['feature_names']

'''
print 'Matching the downloaded boston dataset with inbuilt boston dataset in scikit-learn'
inbuilt_boston = datasets.load_boston()
inbuilt_X = inbuilt_boston.data
inbuilt_y = inbuilt_boston.target
inbuilt_feature_names = inbuilt_boston.feature_names

print np.array_equal(X, inbuilt_X)
print np.array_equal(y, inbuilt_y)
print np.array_equal(feature_names, inbuilt_feature_names)
print 'Turned out, first feature of sample 446 was different in both the dataset. I altered it in downloaded dataset.' 
'''

'''
print 'Plotting single feature against price'
plt.scatter(X[:, 0], y)
plt.show()
'''

X_train, X_test, y_train, y_test = cross_validation.train_test_split(X, y, test_size=0.25, random_state=42)

def train_and_evaluate(clf, X_train, y_train):
	clf.fit(X_train, y_train)
	print clf.score(X_train, y_train)

	cv = cross_validation.KFold(X_train.shape[0], 5, shuffle=True, random_state=33)
	scores = cross_validation.cross_val_score(clf, X_train, y_train, cv=cv)
	print np.mean(scores)

scaler = preprocessing.StandardScaler().fit(X_train)
extra_tree=ensemble.ExtraTreesRegressor(n_estimators=10, random_state=42)

pipe = pipeline.make_pipeline(scaler, extra_tree)

train_and_evaluate(pipe, X_train, y_train)
exit()
y_predicted = pipe.predict(X_test)

print metrics.r2_score(y_test, y_predicted)

