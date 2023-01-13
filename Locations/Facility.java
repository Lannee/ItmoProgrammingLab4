package Locations;

import Classes.Messager;
import People.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public abstract class Facility extends Location {

    ArrayList<Person> employees = new ArrayList<>();

    public Facility(String name) {
        super(name);
    }

    public Facility(String name, float[] position, Person[] employees) {
        super(name, position);
        this.employees.addAll(Arrays.asList(employees));
    }

    public ArrayList<Person> getEmployees() {
        return employees;
    }

    public abstract void employ(Messager messager, Person ... employees);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Facility facility = (Facility) o;
        return Objects.equals(employees, facility.employees);
    }

    @Override
    public int hashCode() {
        return super.hashCode() * 31 + employees.hashCode();
    }
}
