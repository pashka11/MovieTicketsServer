package nimrod.cinema.exceptions;

/**
 * Created by Yuval on 08-Apr-17.
 * gets thrown if user does not exists
 */
public class UserNotExistException extends Exception {

    private String value,message;


    public UserNotExistException(String message, String value) {
        this.value=value;
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message+" "+value;
    }
}
