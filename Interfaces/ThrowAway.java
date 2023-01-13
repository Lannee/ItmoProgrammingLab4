package Interfaces;

import Items.Item;
import Classes.Messager;

public interface ThrowAway {
    void throwAway(Class<? extends Item> itemClass, Messager messager);

}
