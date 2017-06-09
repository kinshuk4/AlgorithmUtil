# Recommendation System
User-based (*Collaborative*) and item-based (*Content-based*) recommendation system.

## How to use
```
from classes.recommendation import Recommendation

# Collaborative Recommendation
recommend = Recommendation(dataset)
suggestions = recommend.collaborativeRecommendation(Person)

# Content-based Recommendation
filter = Recommendation(transformed_dataset)
filtered_dataset = filter.contentBasedFilteringAll()
recommend = Recommendation(dataset)
suggestions = recommend.contentBasedRecommendation(filtered_dataset, Person)	
```
>NOTE: **dataset** and **transformed_dataset** are same dataset but with keys and inner keys interchanged with each other. Refer *demo.py* for further details.

## Motivation
Recommendation System solves two categories of question.

1. I am Reet. Suggest me some more ***things***.
2. I like ***thing***. Suggest me some more.

Here, ***thing*** can be replace with anything.
For e.g In case of movies dataset:

1. I am Reet. Suggest me some more ***movies***.
2. I like ***Star Wars***. Suggest me some more movies like this.

## Installation
Requirements:

1. Python (2.7.10)
2. NumPy package
3. scikit-learn package


## Licence
The MIT License (MIT)

https://github.com/reetawwsum
