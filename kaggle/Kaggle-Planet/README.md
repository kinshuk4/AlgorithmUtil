Code for the Kaggle competition - "Understanding the Amazon from Space"  [here](https://www.kaggle.com/c/planet-understanding-the-amazon-from-space)

Sketch of install instructions.

### Setup
Make sure you have the pre-requisites installed:
 - miniconda or conda
 - nvidia driver (optional)
 - cudnn (optional)
 - pip install scikit-learn scikit-image keras tensorflow opencv-python tqdm h5py jupyter 
 
### Running the code
 
1. Make sure you set up keras to use tensorflow or theano. I used tensorflow.

2. Code is in the ./src directory. Uncompress test-jpg and train-jpg to ./data/ directory.

3. Run the code
```bash
source activate env_name #replace env_name with your env_name
export CUDA_VISIBLE_DEVICES=0 #(optional)
python src/main.py
```

### Result 
The output prediction file is location in the ./data/test.csv. You can upload it to kaggle. My submission got a score of 0.86078.



https://github.com/reetawwsum
