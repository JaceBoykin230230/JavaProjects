import java.util.HashMap;
import java.util.Map;

public class GameMap {
    private Map<Room, Map<String, Room>> game_map;

    public GameMap() {
        game_map = new HashMap<Room, Map<String, Room>>();
    }

    public void linkRoom(Room room1, String direction, Room room2) {
        Map<String, Room> exits = game_map.get(room1);
        if (exits == null) {
            exits = new HashMap<String, Room>();
            game_map.put(room1, exits);
        }
        exits.put(direction, room2);
    }

    public Room getRoom(Room room, String direction) {
        Map<String, Room> exits = game_map.get(room);
        if (exits == null) {
            return null;
        }
        return exits.get(direction);
    }
}
