'''
===========================
Playing with Badges dataset
===========================
'''

import numpy as np
from scipy.sparse import hstack
from collections import Counter
from sklearn import preprocessing
from sklearn import feature_extraction
from sklearn import naive_bayes
from sklearn import cross_validation
from sklearn import neighbors
from sklearn import linear_model
from sklearn import tree
from sklearn import ensemble
from sklearn import svm
import string
from common.fn import *

def load_badges(file_path = 'datasets/badges.data'):
	badges = {}

	with open(file_path, 'r') as input_file:
		X = []
		y = []
		for line in input_file:
			if len(line) > 1:
				X.append(line[2:-1])
				y.append(line[0])

	le = preprocessing.LabelEncoder()
	le.fit(y)
	target = le.transform(y)

	badges['target'] = np.array(target)

	vectorizer = feature_extraction.text.CountVectorizer(analyzer='char')
	vectorizer.fit(X)
	data = vectorizer.transform(X)

	more_features = []

	for name in X:

		first_char_vowel = 0
		if name[0].lower() in 'aeiou':
			first_char_vowel = 1

		second_char_vowel = 0
		if name[1] in 'aeiou':
			second_char_vowel = 1

		n = name.translate(None, string.punctuation)
		char_count = Counter(n.lower())

		vowels = 0
		consonants = 0
		total = 0
		
		for char in char_count:
			if char == ' ':
				continue

			if char in 'aeiou':
				vowels += char_count[char]
			else:
				consonants += char_count[char]

			total += char_count[char]

		sample = {'vowels': vowels, 'consonants': consonants, 'total': total, 'second_char_vowel': second_char_vowel, 'first_char_vowel': first_char_vowel}
		more_features.append(sample)

	vec = feature_extraction.DictVectorizer()
	vec.fit(more_features)
	data1 = vec.transform(more_features)

	final_data = hstack([data, data1])

	print vec.get_feature_names()
	badges['data'] = data1

	return badges

badges = load_badges()
X = badges['data']
y = badges['target']

clf = tree.DecisionTreeClassifier()

X_train, X_test, y_train, y_test = cross_validation.train_test_split(X, y, test_size=0.1, random_state=42)

clf.fit(X_train, y_train)

print clf.feature_importances_

print clf.score(X_test, y_test)

