package nimrod.cinema.purchase;

import nimrod.cinema.exceptions.ShowInstanceNotExistException;
import nimrod.cinema.exceptions.ShowNotExistException;
import nimrod.cinema.exceptions.TheaterOutOfBoundsException;
import nimrod.cinema.exceptions.UserNotExistException;

import javax.ws.rs.BeanParam;

/**
 * Created by Yuval on 08-Apr-17.
 */
public interface InspectInput {
    void validateInput(@BeanParam PurchaseFilterBean filterBean)throws UserNotExistException,ShowInstanceNotExistException,ShowNotExistException,
            TheaterOutOfBoundsException;
}
