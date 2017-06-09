# Machine-Translation
Sequence-to-Sequence machine translation model

## Prerequisites

1. Python (2.7)
2. NumPy (1.11.2)
3. Tensorflow (0.11)

## Usage

To train a model with 1 hidden LSTM layer having 2 units and 10 batch size:

	$ python main.py --num_hidden_layers 1 --num_units 2 --batch_size 10

To see all options, run:

	$ python main.py -h

which will print:

	usage: main.py [-h] [--batch_size BATCH_SIZE]
	               [--max_train_data_size MAX_TRAIN_DATA_SIZE]
	               [--num_units NUM_UNITS] [--num_hidden_layers NUM_HIDDEN_LAYERS]
	               [--learning_rate LEARNING_RATE]
	               [--learning_rate_decay_factor LEARNING_RATE_DECAY_FACTOR]
	               [--max_gradient_norm MAX_GRADIENT_NORM]
	               [--num_samples NUM_SAMPLES]
	               [--en_vocabulary_size EN_VOCABULARY_SIZE]
	               [--fr_vocabulary_size FR_VOCABULARY_SIZE]
	               [--target_vocab TARGET_VOCAB]
	               [--checkpoint_step CHECKPOINT_STEP] [--train [TRAIN]]
	               [--notrain] [--dataset DATASET] [--model_name MODEL_NAME]
	               [--dataset_dir DATASET_DIR] [--checkpoint_dir CHECKPOINT_DIR]

	optional arguments:
	  -h, --help            show this help message and exit
	  --batch_size BATCH_SIZE
	                        Size of training batch
	  --max_train_data_size MAX_TRAIN_DATA_SIZE
	                        Limit on the size of training data (0: no limit)
	  --num_units NUM_UNITS
	                        Number of units in LSTM layer
	  --num_hidden_layers NUM_HIDDEN_LAYERS
	                        Number of hidden LSTM layers
	  --learning_rate LEARNING_RATE
	                        Initial learning rate
	  --learning_rate_decay_factor LEARNING_RATE_DECAY_FACTOR
	                        Learning rate decays by this much
	  --max_gradient_norm MAX_GRADIENT_NORM
	                        Clip gradients to this norm
	  --num_samples NUM_SAMPLES
	                        Number of samples for sampled softmax
	  --en_vocabulary_size EN_VOCABULARY_SIZE
	                        English vocabulary size
	  --fr_vocabulary_size FR_VOCABULARY_SIZE
	                        French vocabulary size
	  --target_vocab TARGET_VOCAB
	                        Target vocabulary (en/fr)
	  --checkpoint_step CHECKPOINT_STEP
	                        Number of training steps per checkpoint
	  --train [TRAIN]       True for training, False for validating
	  --notrain
	  --dataset DATASET     Name of the dataset file
	  --model_name MODEL_NAME
	                        Name of the model
	  --dataset_dir DATASET_DIR
	                        Directory name for the dataset
	  --checkpoint_dir CHECKPOINT_DIR
	                        Directory name to save the checkpoint

## License
[The MIT License (MIT)](LICENSE)
https://github.com/reetawwsum
