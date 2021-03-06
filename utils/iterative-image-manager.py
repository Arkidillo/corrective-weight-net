# Class that will handle moving the images between iterations
import glob
import os
import sys
import shutil

im_path = "./images/"
sample_path = "./iterative_labeler/samples/"
initial_train_images_path = sample_path + "initial_train_images/"
test_im_dir = sample_path + "test_images/"

if __name__ == "__main__":
	n_iterations = 3	# Add 1 for initial java training
	curr_iteration = int(sys.argv[1])

	all_im_list = glob.glob(os.path.join(im_path,"*"))

	# Get number of images per iteration batch
	iteration_batch_sz = int(1.0/n_iterations * len(all_im_list) + 0.01)

	i_start = min(iteration_batch_sz * curr_iteration, len(all_im_list))
	i_end = min(iteration_batch_sz * (curr_iteration + 1), len(all_im_list))

	if (curr_iteration == n_iterations - 1):
		i_end = len(all_im_list)


	# Give 1 batch images to the java initial labeler (images)
	if curr_iteration == 0:
		target_dir = initial_train_images_path[:-1]
	else:
		# Give 1 batch images to python tester (test_images)
		# ^ Loop this for `iterations`
		for file in os.listdir(test_im_dir):
			fp = os.path.join(test_im_dir,file)
			os.unlink(fp)

		target_dir = test_im_dir[:-1]

	print("Images in this batch:")
	for i in range(i_start, i_end):
		print(all_im_list[i])
		new_path = all_im_list[i].replace(im_path[:-1], target_dir)
		shutil.copyfile(all_im_list[i], new_path)
