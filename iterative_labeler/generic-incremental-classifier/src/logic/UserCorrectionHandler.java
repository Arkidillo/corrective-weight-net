package logic;

import gui.Label;
import gui.buttons.ButtonIDs;
import util.ImageHandler;
import util.Utils;

import javax.imageio.ImageReader;
import java.util.ArrayList;
import java.util.HashMap;

public class UserCorrectionHandler {
    private GUIHandler guiHandler;

    public UserCorrectionHandler(GUIHandler guiHandler) {
        this.guiHandler = guiHandler;
    }

    public void correct() {
        // Read all labels generated by the python
        HashMap<String, ArrayList<Label>> labelsForFile = LabelsReader.readLabels();

        guiHandler.loadNextImage(ImageHandler.CURR_TEST_BATCH_FOLDER);

        // Place the current image's
        String imageFileName = ImageHandler.getCurrentFileName();
        placeLabelsForImage(labelsForFile, imageFileName);

        while(true /* How many time do we want this to train? */) {
            boolean next = labelThisImage();
            if (next) {
                guiHandler.loadNextImage(ImageHandler.CURR_TEST_BATCH_FOLDER);
                imageFileName = ImageHandler.getCurrentFileName();
                placeLabelsForImage(labelsForFile, imageFileName);
            } else {
                guiHandler.loadPrevImage(ImageHandler.CURR_TEST_BATCH_FOLDER);
                imageFileName = ImageHandler.getCurrentFileName();
                placeLabelsForImage(labelsForFile, imageFileName);
            }
        }
    }

    public void placeLabelsForImage(HashMap<String, ArrayList<Label>> labelsForFile, String fileName) {
        ArrayList<Label> labels = labelsForFile.get(fileName);

        for (Label label: labels) {
            guiHandler.addLabel(label);
        }
    }

    public boolean labelThisImage() {
        while (true) {
            Utils.sleep(1);
            // wait for input
            // draw box
            // (this is taken care of by a background listener)

            // return when next or back button is pressed
            if (guiHandler.isButtonPressed(ButtonIDs.NEXT_BUTTON)) {
                return true;
            } else if (guiHandler.isButtonPressed(ButtonIDs.BACK_BUTTON)) {
                return false;
            }
        }
    }

}