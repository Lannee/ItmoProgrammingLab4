package Interfaces;

import Items.Item;
import Classes.Messager;
import Classes.Terrain;

public interface Bury {
    void bury(Class<? extends Item> itemClass, Terrain terrain, Messager messager);
}
