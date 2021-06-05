package utilities.dataBase;

import exceptions.UserAlreadyExistsException;
import exceptions.UserNotExistsException;
import exceptions.WrongPasswordException;

import java.io.Console;
import java.sql.*;
import java.util.Arrays;

public class DataBaseConnection {

    private static final String URL = "jdbc:postgresql://pg:5432/studs";


    private final Connection connection;

    protected DataBaseConnection() throws SQLException {
        Console console = System.console();
        System.out.print("Login for database: ");
        String hostName = console.readLine();
        System.out.print("Password for database: ");
        String hostPassword = new String(console.readPassword());
        connection = DriverManager.getConnection(URL, hostName, hostPassword);
    }

    public boolean userExists(String login) throws SQLException {
        PreparedStatement st = connection.prepareStatement("select * from users where login = ?");
        st.setString(1, login);
        ResultSet set = st.executeQuery();
        return set.next();
    }

    public void register(String login, byte[] hash) throws SQLException, UserAlreadyExistsException {
        if (userExists(login)) {
            throw new UserAlreadyExistsException();
        }

        PreparedStatement ps = connection.prepareStatement("insert into users values(nextval('userSerial'), ?, ?)");
        ps.setString(1, login);
        ps.setBytes(2, hash);
        ps.execute();

    }

    public void signIn(String login, byte[] hash) throws SQLException, WrongPasswordException, UserNotExistsException {
        if (!userExists(login)) {
            throw new UserNotExistsException();
        }

        PreparedStatement st = connection.prepareStatement("select * from users where login = ?");
        st.setString(1, login);
        ResultSet set = st.executeQuery();
        set.next();
        if (Arrays.equals(set.getBytes("password"), hash)) {
            return;
        }

        throw new WrongPasswordException();
    }

    public Connection getConnection() {
        return connection;
    }

    public static DataBaseConnection getInstance() throws SQLException {
        return new DataBaseConnection();
    }
}
