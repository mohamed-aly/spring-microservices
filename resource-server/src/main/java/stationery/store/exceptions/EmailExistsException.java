package stationery.store.exceptions;

public class EmailExistsException extends Throwable {

    public EmailExistsException(final String message) {
        super(message);
    }

}
