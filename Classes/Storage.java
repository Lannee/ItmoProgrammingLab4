package Classes;

import Items.Item;

import java.util.ArrayList;
import java.util.Objects;

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

    public ArrayList<Item> getItemsByClass(Class<? extends Item> itemClass) {
        ArrayList<Item> itemsWithClass = new ArrayList<Item>();
        for(Item item : getItems()) {
            if(item.getClass() == itemClass) {
                itemsWithClass.add(item);
            }
        }
        return itemsWithClass;
    }

    public Item getItemByClass(Class<? extends Item> itemClass) {
        for(Item item : this.getItems()) {
            if(item.getClass() == itemClass) {
                return item;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Storage storage = (Storage) o;
        return Objects.equals(items, storage.items);
    }

    @Override
    public int hashCode() {
        return super.hashCode() * 31 + items.hashCode();
    }
}
