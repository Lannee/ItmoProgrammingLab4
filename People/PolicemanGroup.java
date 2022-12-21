package People;

import java.util.ArrayList;
import java.util.Arrays;

public class PolicemanGroup extends Group<Policeman> {

    private ArrayList<Policeman> participants = new ArrayList<Policeman>();
    private static int amount = 0;

    public PolicemanGroup(String name, Policeman[] policemen) {
        this.name = name;
        this.participants.addAll(Arrays.asList(policemen));
    }


}
