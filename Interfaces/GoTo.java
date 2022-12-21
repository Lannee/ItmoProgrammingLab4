package Interfaces;

import Classes.Factory;
import People.Person;

public interface GoTo {
    void goTo(Person person, boolean interactive);
    void goTo(Factory factory, boolean interactive);
}
