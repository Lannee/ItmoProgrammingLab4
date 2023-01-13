package Classes;

import Interfaces.GoTo;
import Locations.Location;

import java.util.Arrays;
import java.util.Objects;

public class Steamer {
    private String name;
    private float[] position;
    private Premises premises;

    public Steamer(Premises premises) {
        this("пароход", premises);
    }

    public Steamer(String name, Premises premises) {
        this(name, new float[] {0, 0 , 0}, premises);
    }

    public Steamer(String name, float[] position, Premises premises) {
        this.position = position;
        this.name = name;
        this.premises = premises;
    }

    public Premises getPremises() {
        return premises;
    }

    public float[] getPosition() {
        return position;
    }

    public void setPosition(float[] position) {
        this.position = position;
    }

    public void goToThrought(Location locationTo, Location locationThrought,  Messager messager) {
        if(messager != null) messager.addMessage(this + " переместился в локацию " + locationTo + ", пройдя при этом через локацию " + locationThrought + ".\n");
        position = locationThrought.getPosition();
        goTo(locationTo, null);
    }

    public void goTo(Location location, Messager messager) {
        if(messager != null) messager.addMessage(this + " переместился в локацию " + location + ".\n");
        position = location.getPosition();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Steamer steamer = (Steamer) o;
        return Objects.equals(name, steamer.name) &&
                Arrays.equals(position, steamer.position) &&
                Objects.equals(premises, steamer.premises);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + premises.hashCode();
        result = 31 * result + Arrays.hashCode(position);
        return result;
    }

    @Override
    public String toString() {
        return name;
    }

}
