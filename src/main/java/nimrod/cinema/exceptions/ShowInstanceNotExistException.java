package nimrod.cinema.exceptions;

/**
 * Created by Yuval on 08-Apr-17.
 * gets thrown if show instance does not exists
 */
public class
ShowInstanceNotExistException extends Throwable {

    private String showInstanceID,message;

    public ShowInstanceNotExistException(String message, String showInstanceID) {
        this.message=message;
        this.showInstanceID = showInstanceID;
    }

    @Override
    public String getMessage() {
        return message+" "+showInstanceID;
    }
}
