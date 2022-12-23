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
//        System.out.println(this + " получила статус " + status.name());
        for(T participant : participants) {
            participant.addStatus(status);
        }
    }

    public int getCourage() {
        int result = 0;
        for(T participant : participants) {
            result += participant.getCourage();
        }
        return result / participants.size();
    }

    public void setCourage(int courage) {
        System.out.println(" осмелели");
        for(T participant : participants) {
            participant.setCourage(courage);
        }
    }

    public void addItem(Item item) {
        System.out.println(this + " обрели предмет " + item);
        for(T participant : participants) {
            participant.getStorage().addItem(item);
        }
    }


    public void setFear(int fear) {
        System.out.println(this + " испугались.");
        for(T participant : this.participants) {
            participant.setFear(fear);
        }
    }

    public int getFear() {
        int fear = 0;
        for(T participant : this.participants) {
            fear += participant.getFear();
        }
        return fear / this.participants.size();
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
    public void bury(ItemType itemType, Terrain terrain, boolean interactive) {
        if(interactive) {
            System.out.print(this + " закапывает предметы ");
        }
        this.hide(itemType, terrain.getStash(), false);
    }

    @Override
    public void hide(ItemType itemType, Stash stash, boolean interactive) {
        if(interactive) {
            System.out.println(this + " прячет ");
        }
        for(T participant : this.getParticipants()) {
            participant.hide(itemType, stash, false);
        }
        System.out.println();
    }

    @Override
    public void give(Item item, Person person, boolean interactive) {
        for (T participant : this.participants) {
            if(participant.isCloseTo(person)) {
                if(interactive) {
                    System.out.println(this + " передает " + item + " персонажу " + person);
                }
                for(T par : this.getParticipants()) {
                    if(par.getStorage().contains(item)) {
                        par.getStorage().removeItem(item);
                        person.getStorage().addItem(item);
                        break;
                    }

                }
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
    public void throwAway(ItemType itemType, boolean interactive) {
        if(interactive) {
            System.out.print(this + " выкидывает предметы ");
        }
        for(int i = 0; i < this.getParticipants().size(); i++) {
            this.getParticipants().get(i).throwAway(itemType, false);
        }
        System.out.println();
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
        return "\"" + this.getName() + "\"";
    }
}
