package logic;

import gui.Label;
import util.ImageHandler;
import util.Utils;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static util.ImageHandler.NEGATIVE_IMAGES_FOLDER;
import static util.ImageHandler.POSITIVE_IMAGES_FOLDER;
import static util.ImageHandler.RELATIVE_WORKING_DIR;

public class SaveImagesHandler {
    public static final String NORMAL_CSV_FILENAME = RELATIVE_WORKING_DIR + "./frcnn/labeled_data.csv";
    public static final String CORRECTED_CSV_FILENAME = RELATIVE_WORKING_DIR + "./frcnn/corrected_data.csv";

    public static void saveAllImages(HashMap<String, ArrayList<Label>> allLabels) {
        // Make sure the folder is cleared
        createFolders();
        //clearFolders();

        StringBuilder fullNormalCsv = new StringBuilder();
        StringBuilder fullCorrectedCsv = new StringBuilder();

        // For each image/ entry in hashmap, save a JSON
        Iterator it = allLabels.entrySet().iterator();
        while (it.hasNext()) {
            // The pair is (imageName, Arraylist of labels)
            Map.Entry pair = (Map.Entry)it.next();
            ArrayList<Label> labels = (ArrayList<Label>) (pair.getValue());
            String fileName =         (String)pair.getKey();

            PositiveImageFormatHandler.saveLabels(fullNormalCsv, fullCorrectedCsv, fileName, labels);
        }


        // Actually save the fully formatted csv when done
        PositiveImageFormatHandler.outputCsv(fullNormalCsv, NORMAL_CSV_FILENAME);
        PositiveImageFormatHandler.outputCsv(fullCorrectedCsv, CORRECTED_CSV_FILENAME);
    }

    public static void clearFolders() {
        System.err.println("DELETING");
        File[] files;
        files = new File(POSITIVE_IMAGES_FOLDER).listFiles();
        if (files != null) { //some JVMs return null for empty dirs
            for (File f: files) {
                f.delete();
            }
        }
        files = new File(NEGATIVE_IMAGES_FOLDER).listFiles();
        if (files != null) { //some JVMs return null for empty dirs
            for (File f: files) {
                f.delete();
            }
        }
    }

    public static void createFolders() {
        File positiveImagesFolder = new File(POSITIVE_IMAGES_FOLDER);
        File negativeImagesFolder = new File(NEGATIVE_IMAGES_FOLDER);

        positiveImagesFolder.mkdir();
        negativeImagesFolder.mkdir();
    }
}
