# Common functions

def transformDataset(dataset):
	'''Transform dataset through exchanging keys with inner_keys'''

	transformed_dataset = {}

	for key in dataset:
		for inner_key in dataset[key]:
			transformed_dataset.setdefault(inner_key, {})
			transformed_dataset[inner_key][key] = dataset[key][inner_key]

	return transformed_dataset

	