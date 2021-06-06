package utilities.dataBase;

import dragon.*;
import exceptions.NoRightsException;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.PriorityQueue;

public class DataBaseRequests {

    DataBaseConnection dataBaseConnection;
    Connection connection;

    public DataBaseRequests(DataBaseConnection dbc) {
        dataBaseConnection = dbc;
        connection = dataBaseConnection.getConnection();

    }

    public void parseIntoCollection(PriorityQueue<Dragon> collection) throws SQLException {
        Statement st = connection.createStatement();
        ResultSet set = st.executeQuery("select * from dragons");
        collection.clear();
        while (set.next()) {
            Dragon dragon = new Dragon();
            Person killer = new Person();
            dragon.setId(set.getLong(1)); // Dragon's id
            dragon.setName(set.getString(2)); // Dragon's name
            dragon.setCreationDate(ZonedDateTime.parse(set.getString("creationDateTime")));
            dragon.setCoordinates(new Coordinates(set.getLong("xCord"), set.getFloat("yCord")));
            dragon.setAge(set.getInt("age"));
            dragon.setDescription(set.getString("description"));
            if (set.getLong("wingspan") == 0) {
                dragon.setWingspan(null);
            } else {
                dragon.setWingspan(set.getLong("wingspan"));
            }
            if (set.getString("type") == null) {
                dragon.setWingspan(null);
            } else {
                dragon.setType(DragonType.valueOf(set.getString("type").toUpperCase()));
            }
            if (set.getInt("killerId") == 0) {
                killer = null;
            } else {
                PreparedStatement ps = connection.prepareStatement("select * from person where id = ?");
                ps.setInt(1, set.getInt("killerId"));
                ResultSet rs = ps.executeQuery();
                rs.next();
                killer.setName(rs.getString(13));
                killer.setBirthday(LocalDateTime.parse(rs.getString("birthday")));
                killer.setEyeColor(Color.valueOf(rs.getString("eyeColor").toUpperCase()));
                killer.setHairColor(Color.valueOf(rs.getString("hairColor").toUpperCase()));
                killer.setNationality(Country.valueOf(rs.getString("nationality").toUpperCase()));
                killer.setLocation(new Location(rs.getInt("xLocation"), rs.getLong("yLocation"),
                        rs.getInt("zLocation")));
            }
            dragon.setKiller(killer);
            dragon.setOwner(set.getString("username"));
            collection.add(dragon);
        }
    }

    public long insertIntoCollection(Dragon dragon, String login) throws SQLException {
        PreparedStatement st = connection.prepareStatement("insert into dragons values(nextval('dragonSerial')," +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        st.setString(1, dragon.getName());
        st.setString(2, dragon.getCreationDate().toString());
        st.setLong(3, dragon.getCoordinates().getX());
        st.setFloat(4, dragon.getCoordinates().getY());
        st.setInt(5, dragon.getAge());
        st.setString(6, dragon.getDescription());
        if (dragon.getWingspan() == null) {
            st.setNull(7, Types.BIGINT);
        } else {
            st.setLong(7, dragon.getWingspan());
        }
        if (dragon.getType() == null) {
            st.setNull(8, Types.VARCHAR);
        } else {
            st.setString(8, dragon.getType().name().toLowerCase());
        }

        if (dragon.getKiller() == null) {
            st.setNull(9, Types.NULL);
        } else {
            Person killer = dragon.getKiller();
            PreparedStatement ps = connection.prepareStatement("insert into person values(nextval('killerSerial')," +
                    "?, ?, ?, ?, ?, ?, ?, ?)");
            makeStatement(ps, killer);

            ps = connection.prepareStatement("select last_value from killerSerial");
            ResultSet rs = ps.executeQuery();
            int id;
            if (rs.next()) {
                id = rs.getInt("last_value");
                st.setInt(9, id);
            }
        }

        st.setString(10, login);
        st.execute();
        st = connection.prepareStatement("select last_value from dragonSerial");
        ResultSet rs = st.executeQuery();
        rs.next();

        return rs.getInt("last_value");

    }

    public void clear(String login) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("delete from dragons where username = ?");
        ps.setString(1, login);
        PreparedStatement st = connection.prepareStatement("delete from person using dragons where " +
                "dragons.killerid = person.id AND dragons.username = ?");
        st.setString(1, login);

        st.execute();
        ps.execute();
    }

    public void removeById(long id, String login) throws SQLException, NoRightsException {
        int killerId = checkUserNameAndKillerId(id, login);
        PreparedStatement statement;
        if (killerId != 0) {
            statement = connection.prepareStatement("delete from person where id = ?");
            statement.setInt(1, killerId);
            statement.execute();
        }
        statement = connection.prepareStatement("delete from dragons where id = ?");
        statement.setLong(1, id);
        statement.execute();
    }

    public void updateId(long id, Dragon dragon, String login) throws SQLException, NoRightsException {

        int killerId = checkUserNameAndKillerId(id, login);
        PreparedStatement ps;
        ps = connection.prepareStatement("update dragons set name = ?, " +
                "xcord = ?, yCord = ?, age = ?, description = ?, wingspan = ?, type = ? where id = ?");
        ps.setString(1, dragon.getName());
        ps.setLong(2, dragon.getCoordinates().getX());
        ps.setFloat(3, dragon.getCoordinates().getY());
        ps.setInt(4, dragon.getAge());
        ps.setString(5, dragon.getDescription());
        if (dragon.getWingspan() == null) {
            ps.setNull(6, Types.BIGINT);
        } else {
            ps.setLong(6, dragon.getWingspan());
        }
        if (dragon.getType() == null) {
            ps.setNull(7, Types.NULL);
        } else {
            ps.setString(7, dragon.getType().name().toLowerCase());
        }
        ps.setLong(8, id);
        ps.execute();

        Person killer = dragon.getKiller();

        if (killerId == 0) {
            return;
        }
        ps = connection.prepareStatement("update person set name = ?, birthday = ?, eyecolor = ?, haircolor = ?," +
                "nationality = ?, xLocation = ?, yLocation = ?, zLocation = ? where id = ?");
        ps.setInt(9, killerId);
        makeStatement(ps, killer);
    }

    private int checkUserNameAndKillerId(long id, String login) throws SQLException, NoRightsException {
        PreparedStatement ps = connection.prepareStatement("select * from dragons where " +
                "id = ? and username = ?");
        ps.setLong(1, id);
        ps.setString(2, login);
        ResultSet rs = ps.executeQuery();
        if (!rs.next()) {
            throw new NoRightsException();
        }
        return rs.getInt("killerId");
    }

    private void makeStatement(PreparedStatement ps, Person killer) throws SQLException {
        ps.setString(1, killer.getName());
        ps.setString(2, killer.getBirthday().toString());
        ps.setString(3, killer.getEyeColor().name().toLowerCase());
        ps.setString(4, killer.getHairColor().name().toLowerCase());
        ps.setString(5, killer.getNationality().name().toLowerCase());
        ps.setInt(6, killer.getLocation().getX());
        ps.setLong(7, killer.getLocation().getY());
        ps.setLong(8, killer.getLocation().getZ());
        ps.execute();
    }
}
