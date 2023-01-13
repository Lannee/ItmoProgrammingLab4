package Items;

import Classes.Messager;
import Classes.Status;
import People.Group;
import People.Person;

public class Weightlessness extends Item {

    public Weightlessness(String name) {
        super(name);
    }

    public Weightlessness() {
        this("Невесомость");
    }

    public void activate(Person person, Messager messager) {
        if(messager != null) messager.addMessage(person + " использует предмет " + this + ".\n");
        person.addStatus(Status.WEIGHTLESSNESS, null);
    }

    public void activate(Group<? extends Person> group, Messager messager) {
        if(messager != null) messager.addMessage(group + " использует предмет " + this + ".\n");
        group.addStatus(Status.WEIGHTLESSNESS, null);
    }
}
