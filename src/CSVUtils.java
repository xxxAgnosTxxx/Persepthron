import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSVUtils {
    private final static File INLAYER = new File("weightsIN.csv");
    private final static File OUTLAYER = new File("weightsOUT.csv");

    public float[][] getWeightsFromFile(int isize, int jsize, File file) {
        float[][] weights = new float[isize][jsize];
        try {
            CSVReader reader = new CSVReader(new FileReader(file));
            String[] nextLine;
            for (int i = 0; i < isize; i++) {
                nextLine = reader.readNext();
                for (int j = 0; j < jsize; j++) {
                    weights[i][j] = Float.parseFloat(nextLine[j]);
                }
            }
            return weights;
        } catch (IOException e) {
            System.out.println("File not found.");
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }
        return weights;
    }

    public void setWeightsToFile(float[][] w, File f) {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(f));
            for (float[] floats : w) {
                String[] record = new String[floats.length];
                for (int j = 0; j < floats.length; j++) {
                    record[j] = String.valueOf(floats[j]);
                }
                writer.writeNext(record);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("File not found.");
        }
    }

    public File getInlayer() {
        return INLAYER;
    }

    public File getOutlayer() {
        return OUTLAYER;
    }
}
