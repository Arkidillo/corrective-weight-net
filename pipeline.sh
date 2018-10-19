python ./utils/iterative-image-manager.py 0
java -jar ./generic-incremental-classifier.jar
python ./frcnn/keras-retinanet/bin/train.py --epochs 1 --steps 1 csv /frcnn/labeled_data_1.csv /frcnn/class_data.csv --first=1
python ./frcnn/keras-retinanet/bin/test.py
java -jar ./generic-incremental-classifier.jar
