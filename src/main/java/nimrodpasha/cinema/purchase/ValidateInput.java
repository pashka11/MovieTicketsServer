//package nimrodpasha.cinema.purchase;
//
//import nimrodpasha.cinema.dao.*;
//import nimrodpasha.cinema.exceptions.ShowInstanceNotExistException;
//import nimrodpasha.cinema.exceptions.ShowNotExistException;
//import nimrodpasha.cinema.exceptions.TheaterOutOfBoundsException;
//import nimrodpasha.cinema.exceptions.UserNotExistException;
//import nimrodpasha.cinema.utils.Parameters;
//import org.bson.Document;
//
//import javax.ws.rs.BeanParam;
//
///**
// * Created by Yuval on 08-Apr-17.
// */
//public class ValidateInput implements InspectInput{
//
//
//    /**
//     * @param filterBean the input from the HTTP request
//     * @throws UserNotExistException didn't find the user in the DB
//     * @throws ShowInstanceNotExistException didn't find the show instance in the DB
//     * @throws ShowNotExistException didn't find the show in the DB
//     * @throws TheaterOutOfBoundsException the row or column parameter was invalid
//     */
//    public void validateInput(@BeanParam PurchaseFilterBean filterBean) throws UserNotExistException,ShowInstanceNotExistException,ShowNotExistException,
//                    TheaterOutOfBoundsException{
//        Crud userCrud = new UserDao();
//        Crud showCrud = new ShowDao();
//        Crud showInstanceCrud = new ShowInstanceDao();
//        Crud theaterCrud = new TheaterDao();
//
////        check if user exists
//        if (userCrud.read(filterBean.getUser()) == null) {
//            throw new UserNotExistException(Parameters.INVALID_USER_ID, filterBean.getUser());
//        }
////        check that show exists
//        if (showCrud.read(String.valueOf(filterBean.getShowId())) == null) {
//            throw new ShowNotExistException(Parameters.INVALID_SHOW_ID, filterBean.getShowId());
//        }
////        check that showInstance exists
//        if (showInstanceCrud.read(filterBean.getShowInstanceID()) == null) {
//            throw new ShowInstanceNotExistException(Parameters.INVALID_SHOW_INSTANCE_ID, filterBean.getShowInstanceID());
//        }
////        check that row and column are not out of bounds
////        if we got here then show instance is valid ,so we extract the theater document from it
//        Document theaterDocument = theaterCrud.read( new ShowInstanceDao().read(filterBean.getShowInstanceID()).get(Parameters.SHOW_INSTANCE_THEATER_ID).toString());
//        if (filterBean.getRow()<1 || filterBean.getRow()>(int)theaterDocument.get(Parameters.THEATER_ROWS)) {
//            throw new TheaterOutOfBoundsException(Parameters.INVALID_ROW, filterBean.getRow());
//        }
//        if (filterBean.getColumn()<1 || filterBean.getColumn()>(int)theaterDocument.get(Parameters.THEATER_COLUMNS)){
//            throw new TheaterOutOfBoundsException(Parameters.INVALID_COLUMN, filterBean.getColumn());
//        }
//    }
//}
