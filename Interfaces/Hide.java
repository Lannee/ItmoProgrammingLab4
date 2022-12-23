package Interfaces;

import Classes.Item;
import Classes.ItemType;
import Classes.Stash;

public interface Hide {
    void hide(ItemType itemType, Stash stash, boolean interactive);
}
