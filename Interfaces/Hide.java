package Interfaces;

import Exeptions.StorageDoesNotContainsItemException;
import Items.Item;
import Classes.Messager;
import Classes.Stash;

public interface Hide {
    void hide(Class<? extends Item> itemClass, Stash stash, Messager messager) throws StorageDoesNotContainsItemException;
}
