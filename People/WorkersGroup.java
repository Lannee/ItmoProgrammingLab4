package People;

import Classes.Messager;
import Exeptions.EmptyGroupException;
import Locations.Factory;
import Classes.Status;
import Items.Weightlessness;
import Interfaces.Banish;

import java.util.ArrayList;
import java.util.Arrays;

public class WorkersGroup extends Group<Worker> implements Banish {
    private final Factory factory;
    private static int amount = 0;

    public static WorkersGroup getAny(ArrayList<WorkersGroup> groups, Messager messager) {
        if(groups.isEmpty()) return null;
        messager.addMessage("Некоторые группы рабочих");
        return groups.get((int)(Math.random() * groups.size() - 1));
    }

    public WorkersGroup(Factory factory, String name, Worker[] workers) {
        this.name = name;
        if(workers.length == 0) throw new EmptyGroupException();
        this.participants.addAll(Arrays.asList(workers));
        this.factory = factory;
    }

    public int getAvrgSalary() {
        int output = 0;
        if(getParticipants().isEmpty()) return 0;
        for(Worker worker : getParticipants()) {
            output += worker.getSalary();
        }
        return output / getParticipants().size();
    }

    public void setSalary(int salary, Messager messager) {
        if(!getParticipants().isEmpty()) {
            if(messager != null) {
                messager.addMessage(this + " стали получать за свою работу ");
                if(getAvrgSalary() > salary) messager.addMessage("меньше.\n");
                else messager.addMessage("больше.\n");
            }
            for(Worker worker : getParticipants()) {
                worker.setSalary(salary, null);
            }
        }
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
    public void banish(Chief chief, Messager messager) {
        for(Worker worker : this.participants) {
            if(worker.hasStatus(Status.WEIGHTLESSNESS) || worker.getStorage().contains(new Weightlessness()) || worker.getCourage() >= 70) {
                if (!this.participants.isEmpty()) {
                    if(messager != null) messager.addMessage(this + " прогоняют персонажа " + chief + ".\n");
                    this.factory.setChief(null);
                }
            }
            break;
        }
    }
}
