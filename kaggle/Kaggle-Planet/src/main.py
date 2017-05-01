import numpy as np
import os
from tqdm import tqdm
import cv2
import keras
from keras.models import Sequential
from keras.layers import Dense, Dropout, Flatten
from keras.layers import Conv2D, MaxPooling2D
from keras.layers import GlobalMaxPooling2D
from keras import backend as K
import csv
from sklearn.metrics import fbeta_score

#
# Params
#

BASE_DIR = "./"
batch_size = 64
epochs = 16
# input image dimensions
height, width = 64, 64


#
# Load training images
#

def load_images_from_folder(folder, width, height):
    files = os.listdir(folder)
    imgs = list()
    for f in tqdm(files):
        img = cv2.imread(folder + '/' + f)
        img = cv2.resize(img, (height, width))
        imgs.append(img)
    imgs = np.float32(np.asarray(imgs))
    imgs /= 255.0
    return imgs, files


train_imgs, train_files = load_images_from_folder(BASE_DIR + '/data/train-jpg/', width, height)
n_train = train_imgs.shape[0]


#
# Load labels
#
def load_labels(labels_file):
    reader = csv.reader(open(labels_file, 'r'))
    raw_labels = dict()
    unique_labels = set()

    # Skip headder
    header = True
    for row in reader:
        if not header:  # Skip header
            raw_labels[row[0]] = row[1].split(' ')
        else:
            header = False

    # Get unique labels
    for labels in raw_labels.values():
        for label in labels:
            unique_labels.add(label)
    unique_labels = list(unique_labels)
    return raw_labels, unique_labels


raw_labels, unique_labels = load_labels(BASE_DIR + '/data/train.csv')
n_labels = len(unique_labels)

#
# Create target matrix
#       
#       each column is corresponds to one of the labels, and is 0 or 1
#
train_labels = np.zeros((n_train, n_labels), np.float32)
for i in range(n_train):
    training_file = train_files[i]
    img_id = training_file[:-4]
    for j in range(n_labels):
        label = unique_labels[j]
        if label in raw_labels[img_id]:
            train_labels[i, j] = 1


#
# Define model
#
#

def get_model(input_shape=(height, width, 3)):
    model = Sequential()
    model.add(Conv2D(16, kernel_size=(3, 3),
                     activation='relu', input_shape=input_shape))
    model.add(MaxPooling2D(pool_size=(2, 2)))
    model.add(Dropout(0.2))
    model.add(Conv2D(32, kernel_size=(3, 3),
                     activation='relu', input_shape=input_shape))
    model.add(MaxPooling2D(pool_size=(2, 2)))
    model.add(Dropout(0.3))
    model.add(Conv2D(64, kernel_size=(3, 3),
                     activation='relu', input_shape=input_shape))
    model.add(MaxPooling2D(pool_size=(2, 2)))
    model.add(Dropout(0.4))
    model.add(Conv2D(128, kernel_size=(3, 3),
                     activation='relu', input_shape=input_shape))
    model.add(GlobalMaxPooling2D())
    model.add(Dropout(0.4))
    model.add(Dense(128, activation='relu'))
    model.add(Dropout(0.2))
    model.add(Dense(n_labels, activation='sigmoid'))
    return model


model = get_model()

# Summary of model
model.summary()

# Compile model
model.compile(loss=keras.losses.binary_crossentropy,
              optimizer=keras.optimizers.Adam(),
              metrics=['accuracy'])

# Train model 
model.fit(train_imgs, train_labels,
          batch_size=batch_size,
          epochs=epochs,
          verbose=1,
          validation_split=0.2)
# Here keras used the last 20% of the data as validation set
n_valid = int(n_train * 0.2)

# Load test images
print("Loading test images...")
test_imgs, test_files = load_images_from_folder(BASE_DIR + '/data/test-jpg/', width, height)
n_test = test_imgs.shape[0]

# Making predictions
predictions = model.predict(test_imgs)

# Write the output file
f_out = open(BASE_DIR + '/data/test.csv', 'w+')
f_out.write("image_name,tags\n")
for i in tqdm(range(n_test)):
    row = test_files[i][:-4] + ','
    first_label = True
    for j in range(n_labels):
        if predictions[i][j] > 0.25:
            row = row + unique_labels[j] + ' '
    f_out.write(row + '\n')
f_out.close()

# Now to evaluate on training data
pred = model.predict(train_imgs)

train_score = fbeta_score(train_labels[:-n_valid], (pred[:-n_valid] > 0.25) * 1.0, beta=2, average='samples')
valid_score = fbeta_score(train_labels[-n_valid:], (pred[-n_valid:] > 0.25) * 1.0, beta=2, average='samples')
print("Training score: %f\nValidation score: %f\n" % (train_score, valid_score))
