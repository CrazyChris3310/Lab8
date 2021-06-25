import utilities.Process;
import utilities.DragonCollection;
import utilities.dataBase.DataBaseConnection;

import java.io.*;
import java.nio.channels.AlreadyBoundException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String URL;
        String login;
        String password;

//        File file = new File("config.txt");
        File file = new File("Server\\Files\\config.txt");

        try {
            Properties properties = new Properties();
            properties.load(new FileReader(file));

            URL = properties.getProperty("URL");
            login = properties.getProperty("Login");
            password = properties.getProperty("Password");
        } catch (FileNotFoundException e) {
            System.out.println("Configuration file not found in this directory: " + file.getAbsolutePath());
            return;
        } catch (IOException e) {
            System.out.println("IOException happened while reading configuration file. " + e.getMessage());
            return;
        }

        DataBaseConnection DBConnection;
        try {
            DBConnection = new DataBaseConnection(URL, login, password);
        } catch (SQLException e) {
            System.out.println("Cannot connect to database. " + e.getMessage());
            return;
        } catch (ClassNotFoundException e) {
            System.out.println("Driver for postgreSQL is not installed on this device");
            return;
        }

        DragonCollection dragons;
        try {
            dragons = new DragonCollection(DBConnection);
        } catch (NumberFormatException | IndexOutOfBoundsException | IOException e) {
            System.out.println("Wrong data in the file");
            return;
        } catch (SQLException e) {
            System.out.println("Error happened while connecting to database. " + e.getMessage());
            return;
        }

        Scanner sc = new Scanner(System.in);

        int port;

        Process process;

        while (true) {
            try {
                System.out.print("Enter the port to bind server to: ");
                port = Integer.parseInt(sc.nextLine());
                process = new Process(dragons, DBConnection, port);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Wrong port format");
            } catch (AlreadyBoundException e) {
                System.out.println("This part is already in use. Try another one.");
            } catch (IOException e) {
                System.out.println("IOException happened. " + e.getMessage());
            }
        }

        process.run();
    }
}
