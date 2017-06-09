from model import *

flags = tf.app.flags
flags.DEFINE_integer('epochs', 10000, 'Epochs to train')
flags.DEFINE_integer('batch_size', 100, 'The size of training batch')
flags.DEFINE_integer('num_unrollings', 10, 'Input sequence length')
flags.DEFINE_integer('validation_size', 1000, 'Size of validation dataset')
flags.DEFINE_integer('num_units', 64, 'Number of units in LSTM layer')
flags.DEFINE_integer('num_hidden_layers', 2, 'Number of hidden LSTM layers')
flags.DEFINE_float('learning_rate', 0.001, 'Initial learning rate')
flags.DEFINE_float('input_keep_prob', 0.5, 'Keep probability for LSTM input dropout')
flags.DEFINE_float('output_keep_prob', 0.5, 'Keep probability for LSTM output dropout')
flags.DEFINE_integer('checkpoint_epoch', 5, 'After every checkpoint_epoch epochs, checkpoint is created')
flags.DEFINE_boolean('train', True, 'True for training, False for Validating')
flags.DEFINE_integer('restore_model', 10000, 'Model to restore to predict')
flags.DEFINE_string('dataset_dir', 'data', 'Directory name for the dataset')
flags.DEFINE_string('checkpoint_dir', 'checkpoint', 'Directory name to save the checkpoint')
flags.DEFINE_string('dataset', 'text8.zip', 'Name of dataset')
flags.DEFINE_string('model_name', 'rnn-lstm', 'Name of the model')
flags.DEFINE_string('batch_dataset_type', 'train_dataset', 'Dataset used for generating training batches')
flags.DEFINE_string('validation_dataset_type', 'validation_dataset', 'Dataset used for validation')
FLAGS = flags.FLAGS

def main(_):
	# Validating flags
	assert FLAGS.num_unrollings > 0, 'Input greater sequence length should be greater than 0'

	if FLAGS.train:
		model = Model(FLAGS)
		model.train()
	else:
		FLAGS.num_unrollings = 1
		FLAGS.batch_size = 1
		FLAGS.batch_dataset_type = FLAGS.validation_dataset_type
		model = Model(FLAGS)
		model.accuracy()

if __name__ == '__main__':
	tf.app.run()
