public class Main {
    public static void main(String[] args) {
        GameMap game_map = new GameMap();
        Room dungeon = new Room("Dungeon", "A dark and damp dungeon.");
        Room cave = new Room("Cave", "A dark and damp cave.");
        Room forest = new Room("Forest", "A dark and damp forest.");
        Room castle = new Room("Castle", "A dark and damp castle.");
        Room tower = new Room("Tower", "A dark and damp tower.");
        Room beach = new Room("Beach", "A dark and damp beach.");

        game_map.linkRoom(dungeon, "north", cave);
        game_map.linkRoom(cave, "south", dungeon);
        game_map.linkRoom(cave, "east", forest);
        game_map.linkRoom(forest, "west", cave);
        game_map.linkRoom(forest, "north", castle);
        game_map.linkRoom(castle, "south", forest);
        game_map.linkRoom(castle, "east", tower);
        game_map.linkRoom(tower, "west", castle);
        game_map.linkRoom(tower, "north", beach);
        game_map.linkRoom(beach, "south", tower);

        Player player = new Player();
        player.setName("Player");
        player.setHealth(100.0f);
        player.addItem(new Item("Sword"));
        player.addItem(new Item("Shield"));
        player.addItem(new Item("Potion"));

        System.out.println("Welcome to the game!");
        System.out.println("You are in the " + dungeon.getName() + ".");
        System.out.println("You have " + player.getHealth() + " health.");
        System.out.println("You have the following items:");
        for (Item item : player.getInventory()) {
            System.out.println(item.getName());
        }



    }

}
