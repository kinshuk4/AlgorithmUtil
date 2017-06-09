from __future__ import print_function
from __future__ import absolute_import

import re

import tensorflow as tf

flags = tf.app.flags
flags.DEFINE_integer('batch_size', 64, 'Size of training batch')
flags.DEFINE_integer('max_train_data_size', 0, 'Limit on the size of training data (0: no limit)')
flags.DEFINE_integer('num_units', 1024, 'Number of units in LSTM layer')
flags.DEFINE_integer('num_hidden_layers', 3, 'Number of hidden LSTM layers')
flags.DEFINE_float('learning_rate', 0.5, 'Initial learning rate')
flags.DEFINE_float('learning_rate_decay_factor', 0.99, 'Learning rate decays by this much')
flags.DEFINE_float('max_gradient_norm', 5.0, 'Clip gradients to this norm')
flags.DEFINE_integer('num_samples', 512, 'Number of samples for sampled softmax')
flags.DEFINE_integer('en_vocabulary_size', 40000, 'English vocabulary size')
flags.DEFINE_integer('fr_vocabulary_size', 40000, 'French vocabulary size')
flags.DEFINE_string('target_vocab', 'fr', 'Target vocabulary (en/fr)')
flags.DEFINE_integer('checkpoint_step', 200, 'Number of training steps per checkpoint')
flags.DEFINE_boolean('train', True, 'True for training, False for validating')
flags.DEFINE_string('dataset', 'training-giga-fren.tar', 'Name of the dataset file')
flags.DEFINE_string('model_name', 'seq2seq', 'Name of the model')
flags.DEFINE_string('dataset_dir', 'data', 'Directory name for the dataset')
flags.DEFINE_string('checkpoint_dir', 'checkpoint', 'Directory name to save the checkpoint')
FLAGS = flags.FLAGS

BUCKETS = [(5, 10), (10, 15), (20, 25), (40, 50)]

PAD_ID = 0
GO_ID = 1
EOS_ID = 2
UNK_ID = 3

START_VOCAB = [b'_PAD', b'_GO', b'_EOS', b'_UNK']

WORD_SPLIT = re.compile(b'([.,!?"\':;)(])')
DIGIT_RE = re.compile(br'\d')
