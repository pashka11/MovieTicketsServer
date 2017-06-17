package nimrodpasha.cinema.exceptions;

/**
 * Created by Yuval on 08-Apr-17.
 * gets thrown if show does not exists
 */
public class ShowNotExistException extends Throwable {

    private String message;
    private int showId;

    public ShowNotExistException(String message, int showId) {
        this.message = message;
        this.showId = showId;
    }

    @Override
    public String getMessage() {
        return message+" "+String.valueOf(showId);
    }
}
