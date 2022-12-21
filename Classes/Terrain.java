package Classes;

public class Terrain {

    private Stash stash = new Stash();

    public Stash getStash() {
        return stash;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + stash.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!super.equals(o)) return false;
        if (this.getClass() != o.getClass()) return false;
        Terrain oTer = (Terrain) o;
        if(!this.stash.equals(oTer.stash)) return false;
        return true;
    }

    public String toString() {
        return " земля ";
    }
}
