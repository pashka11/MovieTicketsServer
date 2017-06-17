package nimrodpasha.cinema.exceptions;

/**
 * Created by Yuval on 08-Apr-17.
 * gets thrown if index is out of bounds
 */
public class TheaterOutOfBoundsException extends Throwable {

    private String message;
    private int value;

    public TheaterOutOfBoundsException(String message, int value) {
        this.message=message;
        this.value=value;
    }

    @Override
    public String getMessage() {
        return message+" "+String.valueOf(value);
    }
}
