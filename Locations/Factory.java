package Locations;

import Classes.Market;
import Classes.Messager;
import People.Chief;
import People.Person;
import People.Servant;

public class Factory extends Facility {
    private Chief chief;

    public Factory(Chief chief, String name, float[] position, Person[] employees) {
        super(name, position, employees);
        this.chief = chief;
    }

    public Factory(Chief chief, String name, Person[] employees) {
        this(chief, name, new float[] {0, 0, 0}, employees);
    }

    public Factory(Chief chief, Person[] employees) {
        this(chief, "Фабрика", employees);
    }

    public Chief getChief() {
        return chief;
    }

    public void setChief(Chief chief) {
        this.chief = chief;
    }

    public static void setProductivity(Market market, int productivity, Messager messager) {
        if(messager != null) {
            messager.addMessage("Фабрики ");
            if(market.getFactoriesProductivity() > productivity) messager.addMessage("снизили выпуск продукции.\n");
            else messager.addMessage("увеличили выпуск продукции.\n");
        }
        market.setFactoriesProductivity(productivity);
    }

    @Override
    public void employ(Messager messager, Person... employees) {
        if(employees.length != 0) {
            if (messager != null) {
                messager.addMessage(this + " нанимает");
            }
            for(Person employee : employees) {
                this.employees.add(employee);
                if(messager != null) messager.addMessage(" " + employee);
            }
            if(messager != null) messager.addMessage(".\n");
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (chief == null ? 0 : chief.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!super.equals(o)) return false;
        if (this.getClass() != o.getClass()) return false;
        Factory oFct = (Factory) o;
        if (this.chief == null) {
            if (oFct.chief != null ) return false;
        } else if (!this.chief.equals(oFct.chief))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
