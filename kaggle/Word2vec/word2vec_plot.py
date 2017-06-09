import matplotlib.pyplot as plt

def plot(embeddings, labels):
	plt.figure(figsize=(15, 15))

	for i, label in enumerate(labels):
		x, y = embeddings[i, :]
		plt.scatter(x, y)
		plt.annotate(label, xy=(x, y), xytext=(5, 2), textcoords='offset points', ha='right', va='bottom')

	return plt