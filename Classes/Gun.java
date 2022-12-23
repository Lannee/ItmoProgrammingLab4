package Classes;

public class Gun extends Item {

    public Gun(String name) {
        super(name);
        this.itemType = ItemType.GUN;
    }

    public Gun() {
        this("Ствол");
    }

}
