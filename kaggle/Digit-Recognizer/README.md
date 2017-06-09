# Digit Recognizer (Kaggle)
Classify handwritten digits using the famous MNIST data

## Various models implemented

1. Linear model - 1 Layer (784-10)
2. Two layer Neural Network - 2 Layer (784-800-10)
3. Three layer Neural Network - 3 layer (784-128-32-10)
4. Deep Neural Network - 6 layer (784-2500-2000-1500-1000-500-10)
5. Four layer Convolutional Network - 4 layer Convnet (1-32-P-64-P-1024FC-D-10) with l2 regularization
6. Four layer Convolutional Network - 4 layer Convnet (1-20-P-40-P-150FC-10)
7. Five layer Convolutional Network - 5 layer Convnet (1-32-P-64-P-1024FC-D-512FC-D-10) with l2 regularization

>Note: Different models are committed in a single file *digit_recognizer_model.py*

## Libraries used

1. NumPy (1.11.0)
2. Matplotlib (1.3.1)
3. Tensorflow (0.8)
4. Scikit-learn (0.17)

## How to run

1. Place train.csv and test.csv from Kaggle into dataset folder.
2. Run **digit_recognizer.py**.

## Licence
The MIT License (MIT)

https://github.com/reetawwsum
