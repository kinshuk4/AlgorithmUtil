import tensorflow as tf

from model import *

flags = tf.app.flags
flags.DEFINE_integer('batch_size', 100, 'Size of training batch')
flags.DEFINE_integer('epochs', 10000, 'Epochs to train')
flags.DEFINE_integer('num_units', 200, 'Number of units in LSTM layer')
flags.DEFINE_integer('num_hidden_layers', 2, 'Number of hidden LSTM layers')
flags.DEFINE_integer('num_unrollings', 10, 'Input sequence length')
flags.DEFINE_float('learning_rate', 0.001, 'Initial learning rate')
flags.DEFINE_integer('checkpoint_epoch', 5, 'Checkpoint is created after every checkpoint epoch')
flags.DEFINE_integer('vocabulary_size', 10000, 'Vocabulary Size')
flags.DEFINE_integer('embedding_size', 200, 'Size of embedding vector')
flags.DEFINE_float('input_keep_prob', 0.5, 'Keep probability for LSTM input dropout')
flags.DEFINE_float('output_keep_prob', 0.5, 'Keep probability for LSTM output dropout')
flags.DEFINE_boolean('train', True, 'True for training, False for validating')
flags.DEFINE_string('dataset', 'ptb', 'Name of dataset file')
flags.DEFINE_string('batch_dataset_type', 'train', 'Dataset used for generating training batches')
flags.DEFINE_string('validation_dataset_type', 'valid', 'Dataset used for validation')
flags.DEFINE_string('test_dataset_type', 'test', 'Dataset used for testing')
flags.DEFINE_string('model_name', 'rnn-lstm', 'Name of the model')
flags.DEFINE_string('dataset_dir', 'data', 'Directory name for the dataset')
flags.DEFINE_string('checkpoint_dir', 'checkpoint', 'Directory name to save the checkpoint')
FLAGS = flags.FLAGS

def main(_):
	if FLAGS.train:
		model = Model(FLAGS)
		model.train()

if __name__ == '__main__':
	tf.app.run()
