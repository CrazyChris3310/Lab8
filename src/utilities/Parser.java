package utilities;

import java.io.*;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Defines methods for parsing from csv files.
 */
public class Parser {

    private static final char SEPARATOR = ',';
    private static final char DOUBLE_QUOTES = '"';

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

    public ArrayList<String> getItems(String line) {
        ArrayList<String> result = new ArrayList<>();

        boolean inQuotes = false;
        boolean isFieldWithEmbeddedDoubleQuotes = false;

        StringBuilder field = new StringBuilder();

        for (char c : line.toCharArray()) {

            if (c == DOUBLE_QUOTES) {               // handle embedded double quotes ""
                if (isFieldWithEmbeddedDoubleQuotes) {

                    if (field.length() > 0) {       // handle for empty field like "",""
                        field.append(DOUBLE_QUOTES);
                        isFieldWithEmbeddedDoubleQuotes = false;
                    }

                } else {
                    isFieldWithEmbeddedDoubleQuotes = true;
                }
            } else {
                isFieldWithEmbeddedDoubleQuotes = false;
            }

            if (c == DOUBLE_QUOTES) {
                inQuotes = !inQuotes;
            } else {
                if (c == SEPARATOR && !inQuotes) {  // if find separator and not in quotes, add field to the list
                    result.add(field.toString());
                    field.setLength(0);             // empty the field and ready for the next
                } else {
                    field.append(c);                // else append the char into a field
                }
            }
        }

        result.add(field.toString());           // this is the last field

        return result;
    }

}
