package Exeptions;

public class StorageDoesNotContainsItemException extends Exception {
    public StorageDoesNotContainsItemException() {
        this("Can not get the item from storage.");
    }
    public StorageDoesNotContainsItemException(String message) {
        super(message);
    }
}
