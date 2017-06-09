# Character-Model
Implementation of Recurrent Neural Network (LSTM) character model

## Prerequisites

1. Python (2.7)
2. NumPy (1.11.2)
3. Tensorflow (0.11)

## Usage

To train a model with one hidden LSTM layer with 64 units:

	$ python main.py --epochs 10000 --batch_size 100 --num_units 64 --num_hidden_layers 1

To check accuracy of an existing model:

	$ python main.py --notrain --restore_model 10000

To see all options, run:

	$ python main.py -h

which will print:

	usage: main.py [-h] [--epochs EPOCHS] [--batch_size BATCH_SIZE]
	               [--num_unrollings NUM_UNROLLINGS]
	               [--validation_size VALIDATION_SIZE] [--num_units NUM_UNITS]
	               [--num_hidden_layers NUM_HIDDEN_LAYERS]
	               [--learning_rate LEARNING_RATE]
	               [--input_keep_prob INPUT_KEEP_PROB]
	               [--output_keep_prob OUTPUT_KEEP_PROB]
	               [--checkpoint_epoch CHECKPOINT_EPOCH] [--train [TRAIN]]
	               [--notrain] [--restore_model RESTORE_MODEL]
	               [--dataset_dir DATASET_DIR] [--checkpoint_dir CHECKPOINT_DIR]
	               [--dataset DATASET] [--model_name MODEL_NAME]
	               [--batch_dataset_type BATCH_DATASET_TYPE]
	               [--validation_dataset_type VALIDATION_DATASET_TYPE]

	optional arguments:
	  -h, --help            show this help message and exit
	  --epochs EPOCHS       Epochs to train
	  --batch_size BATCH_SIZE
	                        The size of training batch
	  --num_unrollings NUM_UNROLLINGS
	                        Input sequence length
	  --validation_size VALIDATION_SIZE
	                        Size of validation dataset
	  --num_units NUM_UNITS
	                        Number of units in LSTM layer
	  --num_hidden_layers NUM_HIDDEN_LAYERS
	                        Number of hidden LSTM layers
	  --learning_rate LEARNING_RATE
	                        Initial learning rate
	  --input_keep_prob INPUT_KEEP_PROB
	                        Keep probability for LSTM input dropout
	  --output_keep_prob OUTPUT_KEEP_PROB
	                        Keep probability for LSTM output dropout
	  --checkpoint_epoch CHECKPOINT_EPOCH
	                        After every checkpoint_epoch epochs, checkpoint is
	                        created
	  --train [TRAIN]       True for training, False for Validating
	  --notrain
	  --restore_model RESTORE_MODEL
	                        Model to restore to predict
	  --dataset_dir DATASET_DIR
	                        Directory name for the dataset
	  --checkpoint_dir CHECKPOINT_DIR
	                        Directory name to save the checkpoint
	  --dataset DATASET     Name of dataset
	  --model_name MODEL_NAME
	                        Name of the model
	  --batch_dataset_type BATCH_DATASET_TYPE
	                        Dataset used for generating training batches
	  --validation_dataset_type VALIDATION_DATASET_TYPE
	                        Dataset used for validation

## License
[The MIT License (MIT)](LICENSE)
https://github.com/reetawwsum
