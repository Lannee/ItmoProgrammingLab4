package Interfaces;

import Classes.Messager;
import Locations.Location;
import People.Group;
import People.Person;

public interface GoTo {
    void goTo(Person person, Messager messager);
    void goTo(Location location, Messager messager);
    void goTo(Group<?> group, Messager messager);
}
