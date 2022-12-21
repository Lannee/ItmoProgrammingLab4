package People;

import Classes.*;
import Interfaces.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Group<T extends Person> implements GoTo, Say, Hide, Give, Bury, ThrowAway {

    protected ArrayList<T> participants = new ArrayList<T>();
    private static int amount = 0;
    protected String name;

    public Group(String name, T[] participants) {
        this.name = name;
        this.participants.addAll(Arrays.asList(participants));
    }

    public Group(T[] participants) {
        this("Группа №" + ++amount, participants);
    }

    public Group(){}

    public ArrayList<T> getParticipants() {
        return participants;
    }

    public void addParticipant(T person) {
        participants.add(person);
    }

    public String getName() {
        return name;
    }

    public void addStatus(Status status) {
        System.out.println(this + " получила статус " + status.name());
        for(T participant : this.getParticipants()) {
            participant.addStatus(status);
        }
    }

    @Override
    public void goTo(Person person, boolean interactive) {
        if(interactive) {
            System.out.println(this + " отправилась к персонажу " + person);
        }
        for(T participant : participants) {
            participant.setPosition(person.getPosition());
        }
    }

    @Override
    public void goTo(Factory factory, boolean interactive) {
        if(interactive) {
            System.out.println(this + " отправилась на фабрику " + factory);
        }
        for(T participant : participants) {
            participant.setPosition(factory.getPosition());
        }
    }

    @Override
    public void say(String phrase) {
        System.out.println(this + " говорит: " + phrase);
    }

    @Override
    public void bury(Item item, Terrain terrain, boolean interactive) {
        if(interactive) {
            System.out.println(this + " закапывает предметы " + item);
        }
        this.hide(item, terrain.getStash(), false);
    }

    @Override
    public void hide(Item item, Stash stash, boolean interactive) {
        if(interactive) {
            System.out.println(this + " прячет " + item);
        }
        for(T participant : this.getParticipants()) {
            participant.hide(item, stash, false);
        }
    }

    @Override
    public void give(Item item, Person person, boolean interactive) {
        if(interactive) {
            System.out.println(this + " передает " + item + " персонажу " + person);
        }
        for(T participant : this.getParticipants()) {
            if(participant.getStorage().contains(item)) {
                participant.getStorage().removeItem(item);
                person.getStorage().addItem(item);
                break;
            }

        }
    }

    @Override
    public void give(Item item, Group<?> group, boolean interactive) {
        if(interactive) {
            System.out.println(this + " передает " + item + " группе " + group);
        }
        for(T participant1 : this.getParticipants()) {
            if(participant1.getStorage().contains(item)) {
                for(int i = 0; i < group.getParticipants().size(); i++) {
                    group.getParticipants().get(i).getStorage().addItem(item);
                }
                break;
            }
        }
    }

    @Override
    public void throwAway(Item classItem, boolean interactive) {
        if(interactive) {
            System.out.println(this + " выкидывает предметы " + classItem);
        }
        for(int i = 0; i < this.getParticipants().size(); i++) {
            this.getParticipants().get(i).throwAway(classItem, true);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + participants.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!super.equals(o)) return false;
        if (this.getClass() != o.getClass()) return false;
        Group<Person> oGrp = (Group<Person>)o;
        if (this.name == null) {
            if (oGrp.name != null ) return false;
        } else if (!this.name.equals(oGrp.name))
            return false;
        if(!this.participants.equals(oGrp.participants)) return false;
        return true;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
