package People;

import Interfaces.Oppose;

import java.util.ArrayList;
import java.util.Arrays;

public class PolicemanGroup extends Group<Policeman> implements Oppose {

    private static int amount = 0;

    public PolicemanGroup(String name, Policeman[] policemen) {
        this.name = name;
        this.participants.addAll(Arrays.asList(policemen));
    }

    @Override
    public void oppose(Group<?> group) {
        System.out.println(this + " выступают против " + group);
    }

}
