package utilities.commands;

import exceptions.NoRightsException;
import exceptions.NoSuchIdException;
import exceptions.NoSuchKillerException;
import utilities.DragonCollection;
import utilities.Response;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Command "show"
 */
public class ShowCommand extends Command {

    private static final long serialVersionUID = 102L;

    public ShowCommand(DragonCollection collection) {
        super(collection);
        description = "show - show all the elements of collection";
    }

    /**
     * Method prints each element of collection.
     *
     * @param login
     * @return
     */
    @Override
    public Response execute(String login) throws NoSuchKillerException, NoSuchIdException, SQLException, NoRightsException {
//        ArrayList<String[]> list = new ArrayList<>();
//        for (Dragon dragon : drg.getCollection()) {
//            String[] values = new String[18];
//            values[0] = dragon.getId().toString();
//            values[1] = dragon.getName();
//            values[2] = dragon.getCreationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss xxxxx"));
//            values[3] = dragon.getCoordinates().getX().toString();
//            values[4] = String.valueOf(dragon.getCoordinates().getY());
//            values[5] = String.valueOf(dragon.getAge());
//            values[6] = dragon.getDescription();
//            if (dragon.getWingspan() != null) {
//                values[7] = dragon.getWingspan().toString();
//            }
//            if (dragon.getType() != null) {
//                values[8] = dragon.getType().toString();
//            }
//            Person killer = dragon.getKiller();
//            if (killer != null) {
//                values[9] = killer.getName();
//                values[10] = killer.getBirthday().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
//                values[11] = killer.getEyeColor().toString();
//                values[12] = killer.getHairColor().toString();
//                values[13] = killer.getNationality().toString();
//                values[14] = String.valueOf(killer.getLocation().getX());
//                values[15] = String.valueOf(killer.getLocation().getY());
//                values[16] = String.valueOf(killer.getLocation().getZ());
//            }
//
//            values[17] = dragon.getOwner();
//
//            list.add(values);
//        }
//        return new Response(list);
        Response response = new Response();
        response.setCollection(new ArrayList<>(drg.getCollection()));
        return response;
    }

}
