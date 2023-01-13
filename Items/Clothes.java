package Items;

import Classes.Messager;
import People.Group;
import People.Person;

import java.util.ArrayList;

public class Clothes extends Item {

    public Clothes(String name) {
        super(name);
    }

    public Clothes() {
        this("Одежда");
    }

    public void dress(Group<?> group, Messager messager) {
        if(messager != null) messager.addMessage(group + " надевает на себя " + this + ".\n");
        ArrayList<? extends Person> participants = group.getParticipants();
        for(int i = 0; i < participants.size(); i++){
            participants.get(i).getStorage().addItem(this);
        }
    }

    @Override
    public String toString() {
        return getName();
    }
}
