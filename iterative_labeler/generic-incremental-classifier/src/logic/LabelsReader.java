package logic;

import gui.Label;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;

import static util.ImageHandler.MODEL_GUESSES_CSV;

public class LabelsReader {
    public static HashMap<String, ArrayList<Label>> readLabels() {
        HashMap<String, ArrayList<Label>> labels = new HashMap<>();

        try {
            Reader in = new FileReader(MODEL_GUESSES_CSV);
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);

            // All the labels for 1 image
            ArrayList<Label> thisImageLabels = new ArrayList<>();
            // Keeps track of the last images filename to see if it has changed
            String filename = null;
            String oldFilename = null;
            Label newLabel;

            for (CSVRecord record : records) {
                newLabel = new Label();
                int x = (int)Double.parseDouble(record.get(0));
                int y = (int)Double.parseDouble(record.get(1));
                int x2 = (int)Double.parseDouble(record.get(2));
                int y2 = (int)Double.parseDouble(record.get(3));

                filename = record.get(4);
                // Happens for the first file
                if (oldFilename == null) {
                    oldFilename = filename;
                }

                newLabel.setTop(y);
                newLabel.setLeft(x);
                newLabel.setBottom(y2);
                newLabel.setRight(x2);

                if (!filename.equals(oldFilename)){
                    labels.put(oldFilename, thisImageLabels);
                    oldFilename = filename;
                    thisImageLabels = new ArrayList<>();
                }

                thisImageLabels.add(newLabel);
            }

            if (filename == null) {
                System.err.println("No files found in csv");
            } else {
                // For the last image
                labels.put(filename, thisImageLabels);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return labels;
    }
}
