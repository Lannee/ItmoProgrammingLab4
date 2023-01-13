package Items;

import java.util.Arrays;
import java.util.Objects;

public class ItemEntity {

    private float[] position;
    private final String name;

    public ItemEntity(String name) {
        this(new float[] {0, 0, 0}, name);
    }

    public ItemEntity(float[] position, String name) {
        this.position = position;
        this.name = name;
    }

    public float[] getPosition() {
        return position;
    }

    public void setPosition(float[] position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemEntity that = (ItemEntity) o;
        return Arrays.equals(position, that.position) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode() * 31 + name.hashCode();
        result = 31 * result + Arrays.hashCode(position);
        return result;
    }
}
