package Locations;

import People.Chief;

import java.util.Arrays;

public class Location {
    private String name;
    private float[] position;

    public Location() {}
    public Location(String name) {
        this(name, new float[] {0, 0, 0});
    }

    public Location(String name, float[] position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }
    public float[] getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (this.name == null ? 0 : name.hashCode());
        result = prime * result + position.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!super.equals(o)) return false;
        if (this.getClass() != o.getClass()) return false;
        Location oLoc = (Location) o;
        if (this.name == null) {
            if (oLoc.name != null ) return false;
        } else if (!this.name.equals(oLoc.name))
            return false;
        if(!Arrays.equals(this.position, oLoc.position)) return false;
        return true;
    }
}
