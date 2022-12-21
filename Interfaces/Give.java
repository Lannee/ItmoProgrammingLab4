package Interfaces;

import Classes.Item;
import People.Group;
import People.Person;

public interface Give {
    void give(Item item, Person person, boolean interactive);
    void give(Item item, Group<?> group, boolean interactive);
}
