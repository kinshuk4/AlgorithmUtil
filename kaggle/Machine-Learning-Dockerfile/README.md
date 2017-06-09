[![Docker Automated build](https://img.shields.io/docker/automated/reetawwsum/machine-learning.svg)](https://hub.docker.com/r/reetawwsum/machine-learning)
[![Docker Pulls](https://img.shields.io/docker/pulls/reetawwsum/machine-learning.svg)](https://hub.docker.com/r/reetawwsum/machine-learning)

# Machine-Learning-Dockerfile
Dockerfile for Machine Learning

## Features

1. CentOS 7
2. Python 2.7.12
3. pip
4. IPython
5. jupyter
6. matplotlib
7. nltk
8. NumPy
9. pandas
10. scikit-learn
11. SciPy
12. seaborn
13. tensorflow
14. virtualenv
15. xlrd

## Usage

Pull docker image from [DockerHub](https://hub.docker.com/r/reetawwsum/machine-learning)

	$ docker pull reetawwsum/machine-learning

To launch Jupyter Notebook:

	$ docker run --rm -t -i --name ml -p 8888:8888 reetawwsum/machine-learning --ip=0.0.0.0

To launch Jupyter Notebook on current directory:

	$ docker run --rm -t -i --name ml -p 8888:8888 -v $PWD:/usr/local/src/notebooks reetawwsum/machine-learning --ip=0.0.0.0

To run shell after launching Jupyter Notebook:

	$ docker exec -t -i ml /bin/bash

To run Python REPL after launching Jupyter Notebook:

	$ docker exec -t -i ml python2.7

Clone this repo and

	$ git clone https://github.com/reetawwsum/Machine-Learning-Dockerfile.git
	$ cd Machine-Learning-Dockerfile

to build image from Dockerfile:

	$ docker build -t machine-learning .

to build python script present in current directory from Sublime Text 3:

	$ cp Docker-Python.sublime-build [user-packages folder]

## License
[The MIT License (MIT)](LICENSE)
https://github.com/reetawwsum
