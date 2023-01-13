package Interfaces;

import Items.Item;
import Classes.Messager;
import People.Group;
import People.Person;

public interface Give {
    void give(Item item, Person person, Messager messager);
    void give(Item item, Group<?> group, Messager messager);
}
