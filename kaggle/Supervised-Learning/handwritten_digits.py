'''
=========================
Digit Recognizer - Kaggle
=========================
'''

import numpy as np
import matplotlib.pyplot as plt
from sklearn import cross_validation
from sklearn import ensemble
from sklearn.externals import joblib
from sklearn import metrics
from sklearn import feature_selection
from common.fn import *

def load_digits(file_name, file_path = 'datasets/digits/', max_rows=None):
	digits = {}

	raw_data = np.genfromtxt(file_path+file_name, delimiter=',', skip_header=1, max_rows=max_rows, dtype=int)
	digits['data'] = raw_data[:, 1:]
	digits['target'] = raw_data[:, 0]

	return digits

def draw_digit(digit_data, target):
	plt.title(target)	

	# cmap values plt.cm.bone, plt.cm.gray_r
	plt.imshow(np.reshape(digit_data, (28, 28)), cmap=plt.cm.gray_r)

	plt.xticks(())
	plt.yticks(())
	plt.show()

digits = load_digits('train.csv')

X = digits['data']
y = digits['target']

X_train, X_test, y_train, y_test = cross_validation.train_test_split(X, y, test_size=0.10, random_state=42)

selector = feature_selection.SelectPercentile(feature_selection.chi2, 53)
selector.fit(X_train, y_train)
X_train_reduced = selector.transform(X_train)
X_test_reduced = selector.transform(X_test)
# '''
clf = ensemble.ExtraTreesClassifier(n_estimators=37, n_jobs=-1)
clf.fit(X_train_reduced, y_train)
joblib.dump(clf, 'datasets/digits/model/extra_trees.pkl')
# '''
# clf = joblib.load('datasets/digits/model/extra_trees.pkl')

print clf.score(X_test_reduced, y_test)
exit()
print metrics.classification_report(y_test, y_predict)
print metrics.confusion_matrix(y_test, y_predict)