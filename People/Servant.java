package People;

import Classes.Messager;
import Interfaces.GetAwayFromChief;

import java.util.Objects;

public class Servant extends Person implements GetAwayFromChief {

    private Person chief;
    public final ServantSpecialization servantSpecialization;

    public Servant(ServantSpecialization servantSpecialization) {
        this.servantSpecialization = servantSpecialization;
    }

    public void setChief(Person chief) {
        this.chief = chief;
    }

    @Override
    public void getAwayFromChief(Messager messager) {
        if(chief != this) {
            if (messager != null) messager.addMessage(this + " ушел от хозяина.\n");
            chief = this;
        }
    }

    public ServantSpecialization getSpecialization() {
        return servantSpecialization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Servant servant = (Servant) o;
        return Objects.equals(chief, servant.chief) && servantSpecialization == servant.servantSpecialization;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode() * 31 + chief.hashCode();
        return result * 31 + servantSpecialization.hashCode();
    }
}
