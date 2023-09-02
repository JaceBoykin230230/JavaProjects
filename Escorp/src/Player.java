import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Item> inventory;
    private String name;
    private float health;

    public Player() {
        this.health = 100.0f;
        this.inventory = new ArrayList<Item>();
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getHealth() {
        return this.health;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    // Add an item to the player's inventory.
    public void addItem(Item item) {
        this.inventory.add(item);
    }

    // Remove an item from the player's inventory.
    public void removeItem(Item item) {
        this.inventory.remove(item);
    }

    // Return the player's inventory.
    public List<Item> getInventory() {
        return this.inventory;
    }
}
