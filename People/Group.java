package People;

import Classes.*;
import Interfaces.*;
import Items.Food;
import Items.Item;
import Locations.Location;

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

    public void addStatus(Status status, Messager messager) {
        if(messager != null) messager.addMessage(this + " получает статус \"" + status.getTranslation() + "\".\n");
        for(T participant : participants) {
            participant.addStatus(status, null);
        }
    }

    public void setCourage(int courage, Messager messager) {
        if(messager != null) messager.addMessage(" осмелели.\n");
        for(T participant : participants) {
            participant.setCourage(courage);
        }
    }

    public void addItem(Item item, Messager messager) {
        if(messager != null) messager.addMessage(this + " обрели предмет " + item + ".\n");
        for(T participant : participants) {
            participant.getStorage().addItem(item);
        }
    }


    public void setFear(int fear, Messager messager) {
        if(messager != null) messager.addMessage(this + " испугались.\n");
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

    public int getGladness() {
        int gladness = 0;
        for(T participant : this.participants) {
            gladness += participant.getFear();
        }
        return gladness / this.participants.size();
    }
    @Override
    public void goTo(Person person, Messager messager) {
        if(messager != null) messager.addMessage(this + " отправилася к персонажу " + person + ".\n");
        for(T participant : getParticipants()) {
            participant.goTo(person, null);
        }
    }

    @Override
    public void goTo(Location location, Messager messager) {
        if(messager != null) messager.addMessage(this + " отправилася в локацию " + location + ".\n");
        for(T participant : getParticipants()) {
            participant.goTo(location, null);
        }
    }

    @Override
    public void goTo(Group<?> group, Messager messager) {
        if(messager != null) messager.addMessage(this + " отправилася к " + group + ".\n");
        for(T participant : getParticipants()) {
            participant.goTo(group.getParticipants().get(0), null);
        }
    }

    public void feed(Person person, Messager messager) {
        for(Person participant : participants) {
            Item food = participant.getStorage().getItemByClass(Food.class);
            if(food != null) {
                if(messager != null) messager.addMessage(this + " кормят персонажа " + person + ".\n");
                participant.getStorage().removeItem(food);
                person.getStorage().addItem(food);
                break;
            }
        }
    }

    @Override
    public void say(String phrase, Messager messager) {
        if(messager != null) messager.addMessage(this + " говорит: \"" + phrase + "\".\n");
    }

    @Override
    public void bury(Class<? extends Item> itemClass, Terrain terrain, Messager messager) {
        if(messager != null) messager.addMessage(this + " закапывает предметы ");
        this.hide(itemClass, terrain.getStash(), messager);
        if(messager != null) messager.addMessage(".\n");
    }

    @Override
    public void hide(Class<? extends Item> itemClass, Stash stash, Messager messager) {
        for(T participant : this.getParticipants()) {
            participant.hide(itemClass, stash, messager);
        }
    }

    @Override
    public void give(Item item, Person person, Messager messager) {
        for (T participant : this.participants) {
            if(participant.isCloseTo(person)) {
                if(messager != null) messager.addMessage(this + " передает " + item + " персонажу " + person + ".\n");
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
    public void give(Item item, Group<?> group, Messager messager) {
        if(messager != null) messager.addMessage(this + " передает " + item + " группе " + group + ".\n");
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
    public void throwAway(Class<? extends Item> itemClass, Messager messager) {
        if(messager != null) messager.addMessage(this + " выкидывает предметы ");
        for(int i = 0; i < this.getParticipants().size(); i++) {
            this.getParticipants().get(i).throwAway(itemClass, messager);
        }
        if(messager != null) messager.addMessage(".\n");
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
        if (getClass() != o.getClass()) return false;
        Group<T> oGrp = (Group<T>)o;
        if (name == null) {
            if (oGrp.name != null) return false;
        } else if (!name.equals(oGrp.name))
            return false;
        return participants.equals(oGrp.participants);
    }

    @Override
    public String toString() {
        return "\"" + getName() + "\"";
    }
}
