from __future__ import print_function

from model import *

flags = tf.app.flags
flags.DEFINE_integer('num_units', 24, 'Number of units in LSTM layer')
flags.DEFINE_integer('num_unrollings', 20, 'Input sequence length')
flags.DEFINE_integer('batch_size', 1000, 'The size of training batch')
flags.DEFINE_integer('train_size', 10000, 'The size of training dataset')
flags.DEFINE_integer('epochs', 3130, 'Epochs to train')
flags.DEFINE_integer('checkpoint_step', 50, 'Step on which checkpoint is created')
flags.DEFINE_boolean('train', True, 'True for training, False for testing')
flags.DEFINE_string('dataset_dir', 'data', 'Directory name to save the dataset')
flags.DEFINE_string('checkpoint_dir', 'checkpoint', 'Directory name to save the checkpoint')
flags.DEFINE_string('batch_dataset_type', 'train_dataset', 'Dataset used for generating training batches')
flags.DEFINE_string('accuracy_dataset_type', 'test_dataset', 'Dataset used for generating accuracy')
flags.DEFINE_string('model_name', 'rnn-lstm', 'Name of the model')
flags.DEFINE_integer('restore_model', 3110, 'Model to restore to calculate accuracy')
flags.DEFINE_boolean('random_prediction', True, 'Show random prediction')
FLAGS = flags.FLAGS

def main(_):
	# Validating flags
	assert FLAGS.num_units > 0, 'Number of units in LSTM layer should be greater than 0'
	assert FLAGS.num_unrollings > 0, 'Input Sequence length should be greater than 0'
	assert FLAGS.train_size < 2**FLAGS.num_unrollings, 'The size of training dataset should be less than %d' % 2**FLAGS.num_unrollings
	assert FLAGS.train_size % FLAGS.batch_size == 0, 'Train size should be divisible by batch size'
	assert FLAGS.epochs > 0, 'Epochs should be greater than 0'
	assert FLAGS.checkpoint_step % (FLAGS.train_size / FLAGS.batch_size) == 0, 'Checkpoint step should be an Epoch'

	model = Model(FLAGS)

	if FLAGS.train:
		model.train()
	else:
		vals = model.accuracy()

		if FLAGS.random_prediction:
			print('Model accuracy: %.2f' % vals[0])
			print('Random binary string: %s' % str(vals[1]))
			print('Count prediction: %d' % vals[2])
		else:
			print('Model accuracy: %.2f' % vals[0])

if __name__ == '__main__':
	tf.app.run()
