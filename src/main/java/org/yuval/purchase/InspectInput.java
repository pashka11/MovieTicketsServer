package org.yuval.purchase;

import org.yuval.exceptions.ShowInstanceNotExistException;
import org.yuval.exceptions.ShowNotExistException;
import org.yuval.exceptions.TheaterOutOfBoundsException;
import org.yuval.exceptions.UserNotExistException;

import javax.ws.rs.BeanParam;

/**
 * Created by Yuval on 08-Apr-17.
 */
public interface InspectInput {
    void validateInput(@BeanParam PurchaseFilterBean filterBean)throws UserNotExistException,ShowInstanceNotExistException,ShowNotExistException,
            TheaterOutOfBoundsException;

    void validateInputUnmarkedSeat(@BeanParam PurchaseFilterBean filterBean)throws UserNotExistException,ShowInstanceNotExistException,ShowNotExistException;
}
