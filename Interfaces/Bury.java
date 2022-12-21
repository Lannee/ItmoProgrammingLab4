package Interfaces;

import Classes.Item;
import Classes.Terrain;
import People.Group;
import People.Person;

public interface Bury {
    void bury(Item item, Terrain terrain, boolean interactive);
}
