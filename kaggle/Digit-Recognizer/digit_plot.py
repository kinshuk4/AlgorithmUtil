import numpy as np
import matplotlib.pyplot as plt
from digit_recognizer_input import *

def draw_digit(digit, target='Unknown'):
	plt.figure()
	plt.title(target)	

	plt.imshow(np.reshape(digit, (28, 28)), cmap=plt.cm.gray_r)

	plt.xticks(())
	plt.yticks(())
	
	return plt

if __name__ == '__main__':
	dataset = load_digits(0.01, 0.1)
	train = dataset['train_dataset']
	validation = dataset['validation_dataset']
	X_train = train.data
	X_validation = validation.data
	y_train = train.target
	y_validation = validation.target

	num1 = np.random.randint(len(X_train))
	num2 = np.random.randint(len(X_validation))

	draw_digit(X_train[num1].astype(int), y_train[num1])

	draw_digit(X_validation[num2].astype(int), y_validation[num2])

	plt.show()

