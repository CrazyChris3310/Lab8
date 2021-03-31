package utilities;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Defines methods for parsing from csv files.
 */
public class Parser {

    public ArrayList<String> parseFromFile(File path) throws IOException{
        return parseFromFile(path, 0);
    }

    public ArrayList<String> parseFromFile(File path, int skipLine) throws IOException{
        ArrayList<String> result = new ArrayList<>();
        int indexLine = 1;

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));

        String line;
        while ((line = br.readLine()) != null) {

            if (indexLine++ <= skipLine) {
                continue;
            }
            result.add(line);
        }

        return result;
    }

    public ArrayList<String> getItems(String line) throws IOException, CsvValidationException {
        CSVReader reader = new CSVReader(new StringReader(line));
        ArrayList<String> result = new ArrayList<>();
        Collections.addAll(result, reader.readNext());

        return result;
    }
}