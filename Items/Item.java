package Items;

public abstract class Item {
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public Item() {
        this("Предмет");
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (name == null ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!super.equals(o)) return false;
        if (this.getClass() != o.getClass()) return false;
        Item oItm = (Item) o;
        if (this.name == null) {
            if (oItm.name != null ) return false;
        } else if (!this.name.equals(oItm.name)) return false;
        return true;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
