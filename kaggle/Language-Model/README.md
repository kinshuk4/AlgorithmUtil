# Language-Model
Implementation of Recurrent Neural Network (LSTM) language model

## Prerequisites

1. Python (2.7)
2. NumPy (1.11.2)
3. Tensorflow (0.11)

## Usage

To train a model with 1 hidden LSTM layer having 2 units for 100 epochs and 10 batch size:

	$ python main.py --epochs 100 --batch_size 10 --num_hidden_layers 1 --num_units 2

To see all options, run:

	$ python main.py -h

which will print:

	usage: main.py [-h] [--batch_size BATCH_SIZE] [--epochs EPOCHS]
	               [--num_units NUM_UNITS] [--num_hidden_layers NUM_HIDDEN_LAYERS]
	               [--num_unrollings NUM_UNROLLINGS]
	               [--learning_rate LEARNING_RATE]
	               [--checkpoint_epoch CHECKPOINT_EPOCH]
	               [--vocabulary_size VOCABULARY_SIZE]
	               [--embedding_size EMBEDDING_SIZE]
	               [--input_keep_prob INPUT_KEEP_PROB]
	               [--output_keep_prob OUTPUT_KEEP_PROB] [--train [TRAIN]]
	               [--notrain] [--dataset DATASET]
	               [--batch_dataset_type BATCH_DATASET_TYPE]
	               [--validation_dataset_type VALIDATION_DATASET_TYPE]
	               [--test_dataset_type TEST_DATASET_TYPE]
	               [--dataset_dir DATASET_DIR]

	optional arguments:
	  -h, --help            show this help message and exit
	  --batch_size BATCH_SIZE
	                        Size of training batch
	  --epochs EPOCHS       Epochs to train
	  --num_units NUM_UNITS
	                        Number of units in LSTM layer
	  --num_hidden_layers NUM_HIDDEN_LAYERS
	                        Number of hidden LSTM layers
	  --num_unrollings NUM_UNROLLINGS
	                        Input sequence length
	  --learning_rate LEARNING_RATE
	                        Initial learning rate
	  --checkpoint_epoch CHECKPOINT_EPOCH
	                        Checkpoint is created after every checkpoint epoch
	  --vocabulary_size VOCABULARY_SIZE
	                        Vocabulary Size
	  --embedding_size EMBEDDING_SIZE
	                        Size of embedding vector
	  --input_keep_prob INPUT_KEEP_PROB
	                        Keep probability for LSTM input dropout
	  --output_keep_prob OUTPUT_KEEP_PROB
	                        Keep probability for LSTM output dropout
	  --train [TRAIN]       True for training, False for validating
	  --notrain
	  --dataset DATASET     Name of dataset file
	  --batch_dataset_type BATCH_DATASET_TYPE
	                        Dataset used for generating training batches
	  --validation_dataset_type VALIDATION_DATASET_TYPE
	                        Dataset used for validation
	  --test_dataset_type TEST_DATASET_TYPE
	                        Dataset used for testing
	  --dataset_dir DATASET_DIR
	                        Directory name for the dataset

## License
[The MIT License (MIT)](LICENSE)
https://github.com/reetawwsum
