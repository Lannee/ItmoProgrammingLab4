package People;

public class Worker extends Person {
    private static int amount = 0;
    public Worker() {
        super("Рабочий номер №" + ++amount);
    }
}
