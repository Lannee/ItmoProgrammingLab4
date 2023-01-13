package People;

import Classes.Messager;
import Exeptions.EmptyGroupException;
import Interfaces.Oppose;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Arrays;

public class PolicemanGroup extends Group<Policeman> implements Oppose {

    private static int amount = 0;

    public PolicemanGroup(String name, Policeman[] policemen) {
        this.name = name;
        if(policemen.length < 2) throw new EmptyGroupException();
        this.participants.addAll(Arrays.asList(policemen));
    }

    @Override
    public void oppose(Group<?> group, Messager messager) {
        if(messager != null) messager.addMessage(this + " выступают против " + group);
    }

}
