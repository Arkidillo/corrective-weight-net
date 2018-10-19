python ./utils/iterative-image-manager.py 0
java -jar ./generic-incremental-classifier.jar
python ./frcnn/keras_retinanet/bin/train.py --epochs 1 --steps 1 csv /frcnn/labeled_data_1.csv /frcnn/class_data.csv --first=1
python ./frcnn/keras_retinanet/bin/convert_model.py ./snapshots/resnet50_csv_01.h5 ./snapshots/resnet50_csv_01.h5
python ./utils/iterative-image-manager.py 1
python ./frcnn/keras_retinanet/bin/test.py
java -jar ./generic-incremental-classifier.jar
python ./frcnn/keras_retinanet/bin/train.py --epochs 1 --steps 1 csv /frcnn/corrected_data_1.csv /frcnn/class_data.csv --super=1
python ./frcnn/keras_retinanet/bin/train.py --epochs 1 --steps 1 csv /frcnn/labeled_data_1.csv /frcnn/class_data.csv
python ./frcnn/keras_retinanet/bin/convert_model.py ./snapshots/resnet50_csv_01.h5 ./snapshots/resnet50_csv_01.h5
python ./utils/iterative-image-manager.py 2
python ./frcnn/keras_retinanet/bin/test.py
