package Classes;

import People.Group;
import People.Person;

import java.util.ArrayList;

public class Closes extends Item {

    public Closes(String name) {
        super(name);
    }

    public Closes() {
        this("Одежда");
    }


    public void cloth(Person person, boolean interactive) {
        if(interactive) {
            System.out.println(person + " надевает на себя " + this);
        }
        person.getStorage().addItem(this);
    }

    public void cloth(Group<? extends Person> group, boolean interactive) {
        if(interactive) {
            System.out.println(group + " надевает на себя " + this);
        }
        ArrayList<? extends Person> participants = group.getParticipants();
        for(int i = 0; i < participants.size(); i++){
            participants.get(i).getStorage().addItem(this);
        }
    }

}
