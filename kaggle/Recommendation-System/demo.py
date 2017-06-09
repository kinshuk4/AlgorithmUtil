from sklearn.externals import joblib
from common.fn import *
from classes.recommendation import Recommendation

'''
Download movies dataset
http://files.grouplens.org/datasets/movielens/ml-100k.zip

Place the unzip folder 'ml-100k' into 'datasets' folder in root directory of project.
'''

def loadMovieLens(path):

    # Load movie titles
    movies = {}
    for line in open(path+'/u.item'):
        (id, title) = line.split('|')[0:2]
        movies[id] = title

    # Load data
    dataset = {}
    for line in open(path+'/u.data'):
        (user, movieid, rating, ts) = line.split('\t')
        dataset.setdefault(user, {})
        dataset[user][movies[movieid]] = float(rating)

    return dataset

dataset_name = '/ml-100k'
path = 'datasets' + dataset_name

dataset = loadMovieLens(path)

print 'Collaborative Recommendation'

print '\n'

print 'Question: My user id is 87. Suggest me some movies.'
recommend = Recommendation(dataset)
suggestions = recommend.collaborativeRecommendation('87')

print 'Answer: I think you will love these movies, and I believe you gonna give them these ratings.'
for (rating, movie) in suggestions:
    print "%s - %s" % (movie, rating)

print '\n'

print 'Question: I like Toy Story (1995). Suggest me some movies like this.'
transformed_dataset = transformDataset(dataset)
recom = Recommendation(transformed_dataset)
suggestions = recom.contentBasedFiltering('Toy Story (1995)')

print 'Answer: I think you will love these movies.'
for (_, movie) in suggestions:
    print movie

print '\n'

print 'Content-based Recommendation'

'''Note: Initially use this'''

transformed_dataset = transformDataset(dataset)

filter = Recommendation(transformed_dataset)
filtered_dataset = filter.contentBasedFilteringAll()

joblib.dump(filtered_dataset, path+dataset_name+'.pkl')

'''End'''

'''Note: Once the filtered dataset is prepared'''

# filtered_dataset = joblib.load(path+dataset_name+'.pkl')

'''End'''

print '\n'

print 'Question: My user id is 87. Suggest me some movies.'
recommend = Recommendation(dataset)
suggestions = recommend.contentBasedRecommendation(filtered_dataset, '87')

print 'Answer: I think you will love these movies, and I believe you gonna give them these ratings.'
for (rating, movie) in suggestions:
    print "%s - %s" % (movie, rating)

print '\n'

print 'Question: I like Toy Story (1995). Suggest me some movies like this.'
print 'Answer: I think you will love these movies.'
for (_, movie) in filtered_dataset['Toy Story (1995)'][0:3]:
    print movie

