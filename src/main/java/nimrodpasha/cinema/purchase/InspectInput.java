package nimrodpasha.cinema.purchase;

import nimrodpasha.cinema.exceptions.ShowInstanceNotExistException;
import nimrodpasha.cinema.exceptions.ShowNotExistException;
import nimrodpasha.cinema.exceptions.TheaterOutOfBoundsException;
import nimrodpasha.cinema.exceptions.UserNotExistException;

import javax.ws.rs.BeanParam;

/**
 * Created by Yuval on 08-Apr-17.
 */
public interface InspectInput {
    void validateInput(@BeanParam PurchaseFilterBean filterBean)throws UserNotExistException,ShowInstanceNotExistException,ShowNotExistException,
            TheaterOutOfBoundsException;
}
