package logic;

import gui.Label;
import util.ImageHandler;
import util.Utils;

import java.util.Arrays;
import java.util.List;

import static util.ImageHandler.POSITIVE_IMAGES_FOLDER;

public class PositiveImageFormatHandler {

    public static void saveLabels(StringBuilder normalCsv, StringBuilder correctedCsv, String pathname, List<Label> labels) {
        labels.forEach(l -> {
            // Append to different csv depending on if it was corrected
            if (l.isModelWrong()) {
                correctedCsv.append(generateCsv(pathname, l));
            } else {
                normalCsv.append(generateCsv(pathname, l));
            }
        });
    }

    public static void outputCsv(StringBuilder csv, String csvName) {
        Utils.saveStrToFile(csv.toString(), csvName);
    }

    public static String generateCsv(String pathname, Label label) {
        byte imageClass = label.isModelWrong() ? ImageHandler.MODEL_WAS_CORRECTED : ImageHandler.MODEL_NOT_WRONG;
        List<String> rowStrs = Arrays.asList(pathname, Integer.toString(label.getX()), Integer.toString(label.getY()),
                Integer.toString(label.getX() + label.getWidth()), Integer.toString(label.getY() + label.getHeight()),
                "1");
        return addRowFormatting(rowStrs);
    }

    private static String addRowFormatting(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.size() - 1; i++) {
            sb.append(strs.get(i));
            sb.append(",");
        }
        sb.append(strs.get(strs.size() - 1));
        sb.append('\n');
        return sb.toString();
    }
}
