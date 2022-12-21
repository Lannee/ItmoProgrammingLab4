package People;

public class Astronaut extends Person {

    private static int amount = 0;

    public Astronaut(String name) {
        super(name);
    }

    public Astronaut() {
        this("Асторнавт №" + ++amount);
    }
}
