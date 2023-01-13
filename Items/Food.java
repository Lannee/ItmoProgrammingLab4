package Items;

public class Food extends Item {

    public Food() {
        this("еда");
    }

    public Food(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return getName();
    }

}
