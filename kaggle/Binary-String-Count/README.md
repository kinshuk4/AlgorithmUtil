# Binary-String-Count
Count of 1s in a binary string using RNN-LSTM neural network model.

## Prerequisites

1. Python (2.7)
2. NumPy (1.11.2)
3. Tensorflow (0.11)

## Usage
To train a model having binary string length 10:

	$ python main.py --num_unrollings 10 --batch_size 100 --train_size 1000 --epochs 1000

To test with an existing model:

	$ python main.py --num_unrollings 10 --batch_size 100 --train_size 1000 --epochs 1000 --notrain --restore_model 1000

To see all options, run:

	$ python main.py -h

which will print:

	usage: main.py [-h] [--num_units NUM_UNITS] [--num_unrollings NUM_UNROLLINGS]
	               [--batch_size BATCH_SIZE] [--train_size TRAIN_SIZE]
	               [--epochs EPOCHS] [--checkpoint_step CHECKPOINT_STEP]
	               [--train [TRAIN]] [--notrain] [--dataset_dir DATASET_DIR]
	               [--checkpoint_dir CHECKPOINT_DIR]
	               [--batch_dataset_type BATCH_DATASET_TYPE]
	               [--accuracy_dataset_type ACCURACY_DATASET_TYPE]
	               [--model_name MODEL_NAME] [--restore_model RESTORE_MODEL]
	               [--random_prediction [RANDOM_PREDICTION]]
	               [--norandom_prediction]

	optional arguments:
	  -h, --help            show this help message and exit
	  --num_units NUM_UNITS
	                        Number of units in LSTM layer
	  --num_unrollings NUM_UNROLLINGS
	                        Input sequence length
	  --batch_size BATCH_SIZE
	                        The size of training batch
	  --train_size TRAIN_SIZE
	                        The size of training dataset
	  --epochs EPOCHS       Epochs to train
	  --checkpoint_step CHECKPOINT_STEP
	                        Step on which checkpoint is created
	  --train [TRAIN]       True for training, False for testing
	  --notrain
	  --dataset_dir DATASET_DIR
	                        Directory name to save the dataset
	  --checkpoint_dir CHECKPOINT_DIR
	                        Directory name to save the checkpoint
	  --batch_dataset_type BATCH_DATASET_TYPE
	                        Dataset used for generating training batches
	  --accuracy_dataset_type ACCURACY_DATASET_TYPE
	                        Dataset used for generating accuracy
	  --model_name MODEL_NAME
	                        Name of the model
	  --restore_model RESTORE_MODEL
	                        Model to restore to calculate accuracy
	  --random_prediction [RANDOM_PREDICTION]
	                        Show random prediction
	  --norandom_prediction

## License
[The MIT License (MIT)](LICENSE)
https://github.com/reetawwsum
