package Exeptions;

public class EmptyGroupException extends RuntimeException {
    public EmptyGroupException() {
        this("group with no members is trying to perform an action");
    }

    public EmptyGroupException(String message) {
        super(message);
    }

    public EmptyGroupException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyGroupException(Throwable cause) {
        super(cause);
    }
}
