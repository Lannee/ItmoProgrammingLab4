package Interfaces;

import Exeptions.StorageDoesNotContainsItemException;
import Items.Item;
import Classes.Messager;
import People.Group;
import People.Person;

public interface Give {
    void give(Item item, Person person, Messager messager) throws StorageDoesNotContainsItemException;
    void give(Item item, Group<?> group, Messager messager) throws StorageDoesNotContainsItemException;
}
