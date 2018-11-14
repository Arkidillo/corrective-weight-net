EPOCHS=10
STEPS=1
CUDA_CORE=2
python ./utils/iterative-image-manager.py 0
java -jar ./iterative_labeler/generic-incremental-classifier.jar
CUDA_VISIBLE_DEVICES=$CUDA_CORE python ./frcnn/keras_retinanet/bin/train.py --epochs $EPOCHS --steps $STEPS csv ./frcnn/labeled_data.csv ./frcnn/class_data.csv --first=1
python ./utils/iterative-image-manager.py 1
CUDA_VISIBLE_DEVICES=$CUDA_CORE python ./frcnn/keras_retinanet/bin/test.py $EPOCHS
java -jar ./iterative_labeler/generic-incremental-classifier.jar 1
CUDA_VISIBLE_DEVICES=$CUDA_CORE python ./frcnn/keras_retinanet/bin/train.py --epochs $EPOCHS --steps $STEPS --snapshot ./snapshots/resnet50_csv_$EPOCHS.h5 csv ./frcnn/corrected_data.csv ./frcnn/class_data.csv --super=1
CUDA_VISIBLE_DEVICES=$CUDA_CORE python ./frcnn/keras_retinanet/bin/train.py --epochs $EPOCHS --steps $STEPS --snapshot ./snapshots/resnet50_csv_$EPOCHS.h5 csv ./frcnn/labeled_data.csv ./frcnn/class_data.csv
python ./utils/iterative-image-manager.py 2
CUDA_VISIBLE_DEVICES=$CUDA_CORE python ./frcnn/keras_retinanet/bin/test.py $EPOCHS
