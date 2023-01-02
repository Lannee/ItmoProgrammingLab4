package Classes;

import People.Group;
import People.Person;

import java.util.ArrayList;

public class Clothes extends Item {

    public Clothes(String name) {
        super(name);
        this.itemType = ItemType.CLOTHES;
    }

    public Clothes() {
        this("Одежда");
    }


    public void dress(Person person, boolean interactive) {
        if(interactive) {
            System.out.println(person + " надевает на себя " + this);
        }
        person.getStorage().addItem(this);
    }

    public void dress(Group<?> group, boolean interactive) {
        if(interactive) {
            System.out.println(group + " надевает на себя " + this);
        }
        ArrayList<? extends Person> participants = group.getParticipants();
        for(int i = 0; i < participants.size(); i++){
            participants.get(i).getStorage().addItem(this);
        }
    }

}
