EPOCHS=20
STEPS=100
CUDA_CORE=2
CUDA_VISIBLE_DEVICES=$CUDA_CORE python ./frcnn/keras_retinanet/bin/train.py --epochs $EPOCHS --steps $STEPS csv ./frcnn/vanilla_labeled_data.csv ./frcnn/class_data.csv --first=1
