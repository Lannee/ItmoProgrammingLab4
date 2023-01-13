package Classes;

import People.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Premises {

    private ArrayList<Person> people = new ArrayList<>();
    private float[] position;
    private final String name;

    public Premises(String name, Person[] people) {
        this(name, people, new float[] {0, 0, 0});
    }

    public Premises(String name, Person[] people, float[] position) {
        this.name = name;
        this.people.addAll(Arrays.asList(people));
        this.position = position;
        for(Person person : people) {
            person.setPosition(position);
        }
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    public float[] getPosition() {
        return position;
    }

    public void exit(Messager messager, Person... people) {
        float[] premisesPosition = getPosition();
        if(people.length != 0) {
            for (Person person : people) {
                if (this.people.contains(person)) {
                    this.people.remove(person);
                    person.setPosition(new float[]{premisesPosition[0], premisesPosition[1], premisesPosition[2] + 20});
                    if (messager != null) messager.addMessage(person + " ");
                }
            }
            if (messager != null) messager.addMessage("выходят из " + this + ".\n");
        } else {
            if(messager != null) messager.addMessage("Некому выходить из " + this + ".\n");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Premises premises = (Premises) o;
        return Objects.equals(people, premises.people) &&
                Arrays.equals(position, premises.position) &&
                Objects.equals(name, premises.name);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + people.hashCode();
        result = 31 * result + Arrays.hashCode(position);
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
