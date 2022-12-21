package Classes;

import People.Chief;

import java.util.Arrays;

public class Factory {
    private Chief chief;
    private String name;
    private float[] position;

    public Factory(Chief chief, String name, float[] position) {
        this.chief = chief;
        this.name = name;
        this.position = position;
    }

    public Factory(Chief chief, String name) {
        this(chief, name, new float[] {0, 0, 0});
    }

    public Factory(Chief chief) {
        this(chief, "Фабрика");
    }

    public String getName() {
        return name;
    }

    public Chief getChief() {
        return chief;
    }

    public void setChief(Chief chief) {
        this.chief = chief;
    }

    public float[] getPosition() {
        return position;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (chief == null ? 0 : chief.hashCode());
        result = prime * result + position.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!super.equals(o)) return false;
        if (this.getClass() != o.getClass()) return false;
        Factory oFct = (Factory) o;
        if (this.name == null) {
            if (oFct.name != null ) return false;
        } else if (!this.name.equals(oFct.name))
            return false;
        if(!Arrays.equals(this.position, oFct.position)) return false;
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
