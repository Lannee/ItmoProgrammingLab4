package People;

import Classes.*;

import Interfaces.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Person implements GoTo, Say, Bury, Hide, Give, ThrowAway {

    private static int amount = 0;
    protected ArrayList<Status> statuses = new ArrayList<Status>(2);
    protected Storage storage = new Storage();
    protected String name;
    protected float[] position;
    protected int courage = 50;
    protected int fear = 0;


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

    public ArrayList<Status> getStatuses() {
        return statuses;
    }

    public boolean hasStatus(Status status) {
        if(this.getStatuses().isEmpty()) return false;
        for(Status element : this.getStatuses()) {
            if(element == status) return true;
        }
        return false;
    }

    public int getCourage() {
        return courage;
    }

    public void setCourage(int courage) {
        if(courage >= 0 && courage <= 100) {
            this.courage = courage;
        }
    }

    public int getFear() {
        return fear;
    }

    public void setFear(int fear) {
        if(fear >= 0 && fear <= 100) {
            this.fear = fear;
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
    public void bury(ItemType itemType, Terrain terrain, boolean interactive) {
        if(interactive) {
            System.out.println(this + " закапывает предметы ");
        }
        this.hide(itemType, terrain.getStash(), false);
    }

    @Override
    public void hide(ItemType itemType, Stash stash, boolean interactive) {
        if(interactive) {
            System.out.print(this + " прячет ");
        }
        ArrayList<Item> items = this.getStorage().getItemsByClass(itemType);
        for(Item item : items) {
            System.out.print(item.getName() + " ");
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

    public boolean isCloseTo(Person person) {
        float xThis = this.getPosition()[0];
        float yThis = this.getPosition()[1];
        float zThis = this.getPosition()[2];

        float xPer = person.getPosition()[0];
        float yPer = person.getPosition()[1];
        float zPer = person.getPosition()[2];

        return xThis > xPer + 10 && xThis < xPer - 10 &&
                yThis > yPer + 10 && yThis < yPer - 10 &&
                zThis > zPer + 10 && zThis < zPer - 10;
    }

    @Override
    public void throwAway(ItemType itemType, boolean interactive) {
        ArrayList<Item> items = this.getStorage().getItemsByClass(itemType);
        if(interactive) {
            System.out.println(this + " выкидывает предметы ");
        }
        for(Item item : items) {
            System.out.print(item.getName() + " ");
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
