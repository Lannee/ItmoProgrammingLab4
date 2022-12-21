package Classes;

import People.Group;
import People.Person;

public class Weightlessness extends Item {

    public Weightlessness(String name) {
        super(name);
    }

    public Weightlessness() {
        this("Невесомость");
    }

    public void activate(Person person, boolean interactive) {
        if(interactive) {
            System.out.println(person + " использует предмет " + this);
        }
        person.addStatus(Status.WEIGHTLESSNESS);
    }

    public void activate(Group<? extends Person> group, boolean interactive) {
        if(interactive) {
            System.out.println(group + " использует предмет " + this);
        }
        group.addStatus(Status.WEIGHTLESSNESS);
    }
}
