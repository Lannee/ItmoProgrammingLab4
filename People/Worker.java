package People;

import Classes.Messager;

public class Worker extends Person {

    private int salary;
    private static int amount = 0;
    public Worker() {
        this("Рабочий номер №" + ++amount, 100);
    }

    public Worker(String name, int salary) {
        super(name);
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary, Messager messager) {
        if(salary > 0) {
            if(messager != null) {
                messager.addMessage(this + " стал получать за свою работу ");
                if(this.salary > salary) messager.addMessage("меньше.\n");
                else messager.addMessage("больше.\n");
            }
            this.salary = salary;
        }
        else {
            throw new IllegalArgumentException();
        }
    }
}
