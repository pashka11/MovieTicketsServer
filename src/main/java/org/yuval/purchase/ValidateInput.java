package org.yuval.purchase;

import org.bson.Document;
import org.yuval.dao.ShowDao;
import org.yuval.dao.ShowInstanceDao;
import org.yuval.dao.TheaterDao;
import org.yuval.dao.UserDao;
import org.yuval.exceptions.ShowInstanceNotExistException;
import org.yuval.exceptions.ShowNotExistException;
import org.yuval.exceptions.TheaterOutOfBoundsException;
import org.yuval.exceptions.UserNotExistException;

import javax.ws.rs.BeanParam;

import static org.yuval.utils.Parameters.*;

/**
 * Created by Yuval on 08-Apr-17.
 */
public class ValidateInput implements InspectInput{


    /**
     * @param filterBean the input from the HTTP request
     * @throws UserNotExistException didn't find the user in the DB
     * @throws ShowInstanceNotExistException didn't find the show instance in the DB
     * @throws ShowNotExistException didn't find the show in the DB
     * @throws TheaterOutOfBoundsException the row or column parameter was invalid
     */
    public void validateInput(@BeanParam PurchaseFilterBean filterBean) throws UserNotExistException,ShowInstanceNotExistException,ShowNotExistException,
                    TheaterOutOfBoundsException{
//        check if user exists
        if (new UserDao().read(filterBean.getUser()) == null) {
            throw new UserNotExistException(INVALID_USER_ID, filterBean.getUser());
        }
//        check that show exists
        if (new ShowDao().read(String.valueOf(filterBean.getShowId())) == null) {
            throw new ShowNotExistException(INVALID_SHOW_ID, filterBean.getShowId());
        }
//        check that showInstance exists
        if (new ShowInstanceDao().read(filterBean.getShowInstanceID()) == null) {
            throw new ShowInstanceNotExistException(INVALID_SHOW_INSTANCE_ID, filterBean.getShowInstanceID());
        }
//        check that row and column are not out of bounds
//        if we got here then show instance is valid ,so we extract the theater document from it
        Document theaterDocument = new TheaterDao().read( new ShowInstanceDao().read(filterBean.getShowInstanceID()).get(SHOW_INSTANCE_THEATER_ID).toString());
        if (filterBean.getRow()<1 || filterBean.getRow()>(int)theaterDocument.get(THEATER_ROWS)) {
            throw new TheaterOutOfBoundsException(INVALID_ROW, filterBean.getRow());
        }
        if (filterBean.getColumn()<1 || filterBean.getColumn()>(int)theaterDocument.get(THEATER_COLUMNS)){
            throw new TheaterOutOfBoundsException(INVALID_COLUMN, filterBean.getColumn());
        }
    }
}
