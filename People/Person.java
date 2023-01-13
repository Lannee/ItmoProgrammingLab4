package People;

import Classes.*;

import Interfaces.*;
import Items.Item;
import Items.ItemEntity;
import Locations.Location;

import java.util.ArrayList;
import java.util.Arrays;

public class Person implements Say, Bury, Hide, Give, ThrowAway, GoTo {

    private static int amount = 0;
    private ArrayList<Status> statuses = new ArrayList<Status>(2);
    private Storage storage = new Storage();
    private String name;
    private float[] position;
    private int courage = 50;
    private int fear = 0;
    private int gladness = 50;
    private int excitement = 0;
    private ArrayList<Passion> passions = new ArrayList<>();

    public Person(String name, float[] position) {
        amount++;
        this.name = name;
        this.position = position;
    }
    public Person(String name) {
        this(name, new float[] {0, 0, 0});
    }
    public Person(String name, Passion[] passions) {
        this(name);
        for(Passion passion : passions) {
            addPassion(passion);
        }
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

    public void addStatus(Status status, Messager messager) {
        if(messager != null) messager.addMessage(this + " получает статус \"" + status.getTranslation() + "\".\n");
        if(!this.statuses.contains(status)) {
            this.statuses.add(status);
        }
    }

    public ArrayList<Status> getStatuses() {
        return statuses;
    }

    public boolean hasStatus(Status status) {
        return statuses.contains(status);
    }

    public boolean hasPassion(Passion passion, Messager messager) {
        boolean isHasPassion = passions.contains(passion);
        if(isHasPassion) {
            if(messager != null) messager.addMessage(this + " любит " + passion.getTranslation());
            return true;
        }
        if(messager != null) messager.addMessage(this + " не любит" + passion.getTranslation() + "");
        return false;
    }

    public void addPassion(Passion passion) {
        if(!passions.contains(passion)) passions.add(passion);
    }

    public void setGladness(int gladness) {
        if(gladness >= 0 && gladness <= 100) {
            this.gladness = gladness;
        }
    }

    public int getGladness() {
        return gladness;
    }

    public void setExcitement(int excitement) {
        if(excitement >= 0 && excitement <= 100) {
            this.excitement = excitement;
        }
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

    public class Heart {
        private int bpm = 75;

        private final int MAX_BPM = 180;
        private final int MIN_BPM = 20;

        public int getBpm() {
            return bpm;
        }

        public void quake(Messager messager) {
            messager.addMessage("От волнения сердце персонажа " + Person.this + " заколотилость.\n");
            bpm = (MAX_BPM - MIN_BPM) * excitement / 100;
        }

    }

    public class Hand {
        private Object holdedObject;

        public Hand() {}

        public Hand(Object holdedObject) {
            this.holdedObject = holdedObject;
        }

        public Object getHoldedObject() {
            return holdedObject;
        }

        public void take(Object object, Messager messager) {
            if(messager != null) messager.addMessage(Person.this + " берёт в руку \"" + object + "\".\n");
            holdedObject = object;
            if(object.getClass() == getClass() && ((Hand)object).getHoldedObject() != this) {
                ((Hand)object).take(this, null);
            }
        }

        @Override
        public String toString() {
            return "рука персонажа " + Person.this;
        }
    }

    public boolean canMove(Messager messager) {
        if(gladness > 70 || fear > 70 || excitement > 70) {
            if(messager != null) messager.addMessage(this + " не смеет степить ни шагу.\n");
            return false;
        }
        if(messager != null) messager.addMessage(this + " может двигаться.\n");
        return true;
    }

    @Override
    public void goTo(Person person, Messager messager) {
        if(messager != null) messager.addMessage(this + " отправился к персонажу " + person + ".\n");
        this.setPosition(person.getPosition());
    }

    @Override
    public void goTo(Location location, Messager messager) {
        if(messager != null) messager.addMessage(this + " отправился в " + location + ".\n");
        this.setPosition(location.getPosition());
    }

    @Override
    public void goTo(Group<?> group, Messager messager) {
        if(messager != null) messager.addMessage(this + " отправился к " + group + ".\n");
        this.setPosition(group.getParticipants().get(0).getPosition());
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
        ArrayList<Item> items = getStorage().getItemsByClass(itemClass);
        for(Item item : items) {
            if(messager != null) messager.addMessage(item.getName() + " ");
            this.getStorage().removeItem(item);
            stash.getStorage().addItem(item);
        }
    }

    public void jump(Location location, Messager messager) {
        if(messager != null) messager.addMessage(this + " прыгает в локации " + location + ".\n");
        float[] startPos = getPosition();
        setPosition(new float[] {startPos[0], startPos[1] + 10, startPos[2]});
        setPosition(startPos);
    }

    @Override
    public void give(Item item, Person person, Messager messager) {
        if(messager != null) messager.addMessage(this + " передает " + item + " персонажу " + person + ".\n");
        if(this.getStorage().contains(item)) {
            this.getStorage().removeItem(item);
            person.getStorage().addItem(item);
        }
    }

    @Override
    public void give(Item item, Group<?> group, Messager messager) {
        if(messager != null) messager.addMessage(this + " передает " + item + " группе " + group + ".\n");
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

        return xThis < xPer + 10 && xThis > xPer - 10 &&
                yThis < yPer + 10 && yThis > yPer - 10 &&
                zThis < zPer + 10 && zThis > zPer - 10;
    }

    @Override
    public void throwAway(Class<? extends Item> itemClass, Messager messager) {
        ArrayList<Item> items = this.getStorage().getItemsByClass(itemClass);
        for(Item item : items) {
            if(messager != null) messager.addMessage(item.getName() + " ");
            this.getStorage().removeItem(item);
        }
    }

    public void tossUp(Item item, Messager messager) {
        if(messager != null) messager.addMessage(this + " подбрасывает предмет " + item + ".\n");
        if(getStorage().contains(item)) {
            getStorage().removeItem(item);
            ItemEntity itemEntity = new ItemEntity(getPosition(), item.getName());
            float[] startPos = itemEntity.getPosition();
            itemEntity.setPosition(new float[] {startPos[0], startPos[1] + 10, startPos[2]});
            itemEntity.setPosition(startPos);
            itemEntity = null;
            getStorage().addItem(item);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + storage.hashCode();
        result = prime * result + Arrays.hashCode(position);
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
        return Arrays.equals(this.position, oPer.position);
    }

    public String toString() {
        return this.getName();
    }
}
