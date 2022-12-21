package People;

public class Chief extends Person {

    private static int amount = 0;

    public Chief(String name, float[] position) {
        super(name, position);
    }

    public Chief(String name) {
        super(name);
    }

    public Chief() {
        super("Начальник №" + ++amount);
    }
}
