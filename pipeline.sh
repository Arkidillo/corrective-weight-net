python3 ./utils/iterative-image-manager.py 0
java -jar ./iterative_labeler/generic-incremental-classifier.jar
python3 ./frcnn/keras_retinanet/bin/train.py --epochs 1 --steps 1 csv ./frcnn/labeled_data_1.csv ./frcnn/class_data.csv --first=1
python3 ./utils/iterative-image-manager.py 1
python3 ./frcnn/keras_retinanet/bin/test.py
java -jar ./iterative_labeler/generic-incremental-classifier.jar
python3 ./frcnn/keras_retinanet/bin/train.py --epochs 1 --steps 1 --snapshot ./snapshots/resnet50_csv_01.h5 csv ./frcnn/corrected_data.csv ./frcnn/class_data.csv --super=1
python3 ./frcnn/keras_retinanet/bin/train.py --epochs 1 --steps 1 --snapshot ./snapshots/resnet50_csv_01.h5 csv ./frcnn/labeled_data_1.csv ./frcnn/class_data.csv
python3 ./utils/iterative-image-manager.py 2
python3 ./frcnn/keras_retinanet/bin/test.py
