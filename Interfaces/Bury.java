package Interfaces;

import Classes.Item;
import Classes.ItemType;
import Classes.Terrain;
import People.Group;
import People.Person;

public interface Bury {
    void bury(ItemType itemType, Terrain terrain, boolean interactive);
}
