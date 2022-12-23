package Classes;

import Interfaces.ThrowAway;

import java.util.ArrayList;
import java.util.Arrays;

public class Storage {
    private ArrayList<Item> items = new ArrayList<Item>();

    public ArrayList<Item> getItems() {
        return items;
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void addItems(Item[] items) {
        for(Item item : items) {
            this.addItem(item);
        }
    }

    public void addItems(Item item, int amount) {
        for(int i = 0; i < amount; i++) {
            this.addItem(item);
        }
    }

    public boolean contains(Item item) {
        for(Item storageItem : this.getItems()) {
            if(storageItem.getName() == item.getName()) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Item> getItemsByClass(ItemType itemType) {
        ArrayList<Item> itemsWithClass = new ArrayList<Item>();
        for(Item item : this.getItems()) {
            if(item.getType() == itemType) {
                itemsWithClass.add(item);
            }
        }
        return itemsWithClass;
    }

    public Item getItemByClass(ItemType itemType) {
        for(Item item : this.getItems()) {
            if(item.getType() == itemType) {
                return item;
            }
        }
        return null;
    }

}
