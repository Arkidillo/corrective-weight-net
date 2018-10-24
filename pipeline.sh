python ./utils/iterative-image-manager.py 0
java -jar ./iterative_labeler/generic-incremental-classifier.jar
python ./frcnn/keras_retinanet/bin/train.py --epochs 1 --steps 1 csv ./frcnn/labeled_data.csv ./frcnn/class_data.csv --first=1
python ./utils/iterative-image-manager.py 1
python ./frcnn/keras_retinanet/bin/test.py
java -jar ./iterative_labeler/generic-incremental-classifier.jar
python ./frcnn/keras_retinanet/bin/train.py --epochs 20 --steps 100 --snapshot ./snapshots/resnet50_csv_01.h5 csv ./frcnn/corrected_data.csv ./frcnn/class_data.csv --super=1
python ./frcnn/keras_retinanet/bin/train.py --epochs 20 --steps 100 --snapshot ./snapshots/resnet50_csv_01.h5 csv ./frcnn/labeled_data.csv ./frcnn/class_data.csv
python ./utils/iterative-image-manager.py 2
python ./frcnn/keras_retinanet/bin/test.py
