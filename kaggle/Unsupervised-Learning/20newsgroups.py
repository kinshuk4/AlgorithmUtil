'''
=================================
Playing with 20newsgroups dataset
=================================
Instead of using any predefined function to download the ready made features, I'm converting the raw data to get the features.
Using TfidfVectorizer along with Stemmer from NLTK for feature extraction
Using joblib library, I'm saving my vectorizer and model to hard drive in order to save time to predict in future.
Finally, I'm using similiarity measure to return top post related to new post.
'''

print __doc__

import os
import numpy as np
import scipy as sp
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.cluster import KMeans
from sklearn.externals import joblib
import nltk.stem

DIR = 'datasets'
dataset_name = '/20_newsgroups'

newsgroups = os.listdir(DIR+dataset_name)
newsgroups_files_content = []

for group in newsgroups:
	group_path = DIR + dataset_name + '/' + group

	if os.path.isdir(group_path):
		newsgroups_files_content += [open(os.path.join(group_path, f)).read() for f in os.listdir(group_path)]

english_stemmer = nltk.stem.SnowballStemmer('english')

class StemmedTfidfVectorizer(TfidfVectorizer):
	def build_analyzer(self):
		analyzer = super(TfidfVectorizer, self).build_analyzer()
		return lambda doc: (english_stemmer.stem(w) for w in analyzer(doc))

vectorizer = StemmedTfidfVectorizer(encoding='latin-1', stop_words='english')
vectorizer.fit(newsgroups_files_content)

joblib.dump(vectorizer, DIR+dataset_name+'_vectorizer.pkl')

# vectorizer = joblib.load(DIR+dataset_name+'_vectorizer.pkl')

data = vectorizer.transform(newsgroups_files_content)

number_of_clusters = 50

km = KMeans(n_clusters=number_of_clusters, verbose=True)
km.fit(data)

joblib.dump(km, DIR+dataset_name+'.pkl')

# km = joblib.load(DIR+dataset_name+'.pkl')

new_post = "Disk drive problems. Hi, I have a problem with my hard disk.\
	After 1 year it is working only sporadically now.\
	I tried to format it, but now it doesn't boot any more.\
	Any ideas? Thanks."

new_post_vec = vectorizer.transform([new_post])

new_post_label = km.predict(new_post_vec)[0]

similiar_indices = (km.labels_ == new_post_label).nonzero()[0]

similiar = []
for i in similiar_indices:
	distance = sp.linalg.norm((new_post_vec-data[i]).toarray())
	similiar.append((distance, newsgroups_files_content[i]))

similiar.sort()
print similiar[0][1]