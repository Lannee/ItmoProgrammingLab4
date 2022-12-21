package People;

import Classes.Factory;
import Interfaces.Banish;

import java.util.ArrayList;
import java.util.Arrays;

public class WorkersGroup extends Group<Worker> implements Banish {
//    private ArrayList<Worker> participants = new ArrayList<Worker>();
    private Factory factory;
    private static int amount = 0;

    public WorkersGroup(Factory factory, String name, Worker[] workers) {
        this.name = name;
        this.participants.addAll(Arrays.asList(workers));
        this.factory = factory;
    }

    public WorkersGroup(Factory factory, String name) {
        this(factory, name, null);
    }

    public WorkersGroup(Factory factory, Worker[] workers) {
        this(factory, "Группа рабочих №" + ++amount, workers);
    }

    public Factory getFactory() {
        return factory;
    }

    @Override
    public ArrayList<Worker> getParticipants() {
        return this.participants;
    }

    @Override
    public void addParticipant(Worker worker) {
        super.addParticipant(worker);
    }

    @Override
    public void banish(Chief chief) {
        if(!this.participants.isEmpty()) {
            System.out.println(this + " прогоняют персонажа " + chief);
            this.factory.setChief(null);
        } else {
            System.out.println("В группе некому прогонять персонажа " + chief.getName() + ".");
        }
    }

}
