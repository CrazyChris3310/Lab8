package utilities;

import exceptions.WrongConfigurationDataException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConfigReader {

    private BufferedReader br;
    private Pattern pattern;

    public ConfigReader() throws IOException {
        br = new BufferedReader(new InputStreamReader(new FileInputStream("Server\\Files\\config.txt")));
        pattern = Pattern.compile(".*?: (.*)");
    }

    public String readLine() throws WrongConfigurationDataException, IOException {
        Matcher matcher = pattern.matcher(br.readLine());
        if (!matcher.find())
            throw new WrongConfigurationDataException();
        return matcher.group(1).trim();
    }

}
