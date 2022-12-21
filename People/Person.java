package People;

import Classes.*;

import Interfaces.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Person implements GoTo, Say, Bury, Hide, Give, ThrowAway {

    private static int amount = 0;
    private ArrayList<Status> statuses = new ArrayList<Status>(2);
    private Storage storage = new Storage();
    private String name;
    private float[] position;

    public Person(String name, float position[]) {
        amount++;
        this.name = name;
        this.position = position;
    }
    public Person(String name) {
        this(name, new float[] {0, 0, 0});
    }
    public Person() {
        this("Человек №" + amount);
    }

    public String getName() {
        return this.name;
    }

    public float[] getPosition() {
        return this.position;
    }

    public void setPosition(float[] position) {
        this.position = position;
    }

    public Storage getStorage() {
        return storage;
    }

    public void addStatus(Status status) {
        if(!this.statuses.contains(status)) {
            this.statuses.add(status);
        }
    }


    @Override
    public void goTo(Person person, boolean interactive) {
        if(interactive) {
            System.out.println(this + " отправился к персонажу " + person);
        }
        this.setPosition(person.getPosition());
    }

    @Override
    public void goTo(Factory factory, boolean interactive) {
        if(interactive) {
            System.out.println(this + " отправился в " + factory);
        }
        this.setPosition(factory.getPosition());
    }

    @Override
    public void say(String phrase) {
        System.out.println(this + " говорит: " + phrase);
    }

    @Override
    public void bury(Item item, Terrain terrain, boolean interactive) {
        if(interactive) {
            System.out.println(this + " закапывает предмет " + item);
        }
        this.hide(item, terrain.getStash(), false);
    }

    @Override
    public void hide(Item item, Stash stash, boolean interactive) {
        if(interactive) {
            System.out.println(this + " прячет " + item);
        }
        if(this.getStorage().contains(item)) {
            this.getStorage().removeItem(item);
            stash.getStorage().addItem(item);
        }
    }

    @Override
    public void give(Item item, Person person, boolean interactive) {
        if(interactive) {
            System.out.println(this + " передает " + item + " персонажу " + person);
        }
        if(this.getStorage().contains(item)) {
            this.getStorage().removeItem(item);
            person.getStorage().addItem(item);
        }
    }

    @Override
    public void give(Item item, Group<?> group, boolean interactive) {
        if(interactive) {
            System.out.println(this + " передает " + item + " группе " + group);
        }
        if(this.getStorage().contains(item)) {
            this.getStorage().removeItem(item);
            for(int i = 0; i < group.getParticipants().size(); i++) {
                group.getParticipants().get(i).getStorage().addItem(item);
            }
        }
    }

    @Override
    public void throwAway(Item classItem, boolean interactive) {
        ArrayList<Item> items = this.getStorage().getItemsByClass(classItem);
        System.out.println(items);
        if(interactive) {
            System.out.println(this + " выкидывает предметы " + classItem);
        }
        for(Item item : items) {
            this.getStorage().removeItem(item);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + storage.hashCode();
        result = prime * result + position.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!super.equals(o)) return false;
        if (this.getClass() != o.getClass()) return false;
        Person oPer = (Person) o;
        if (this.name == null) {
            if (oPer.name != null ) return false;
        } else if (!this.name.equals(oPer.name))
            return false;
        if(!this.getStorage().equals(oPer.getStorage())) return false;
        if(!Arrays.equals(this.position, oPer.position)) return false;
        return true;
    }

    public String toString() {
        return this.getName();
    }
}
