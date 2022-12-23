package People;

import Classes.Factory;
import Classes.Status;
import Classes.Weightlessness;
import Interfaces.Banish;

import java.util.ArrayList;
import java.util.Arrays;

public class WorkersGroup extends Group<Worker> implements Banish {
    private Factory factory;
    private static int amount = 0;

    public static WorkersGroup getAny(ArrayList<WorkersGroup> groups) {
        if(groups.isEmpty()) return null;
        System.out.print("Некоторые группы рабочих");
        return groups.get((int)(Math.random() * groups.size() - 1));
    }

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
        for(Worker worker : this.participants) {
            if(worker.hasStatus(Status.WEIGHTLESSNESS) || worker.getStorage().contains(new Weightlessness()) || worker.getCourage() >= 70) {
                if (!this.participants.isEmpty()) {
                    System.out.println(this + " прогоняют персонажа " + chief);
                    this.factory.setChief(null);
                } else {
                    System.out.println("В группе некому прогонять персонажа " + chief.getName() + ".");
                }
                break;
            }
        }
    }

}
