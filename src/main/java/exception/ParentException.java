package exception;

public class ParentException extends RuntimeException {
    public ParentException() {
        super();
    }

    public ParentException(String m) {
        super(m);
    }
}
