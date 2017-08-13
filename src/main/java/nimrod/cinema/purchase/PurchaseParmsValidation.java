//package nimrodpasha.cinema.purchase;
//
//import nimrod.cinema.exceptions.ShowInstanceNotExistException;
//import nimrod.cinema.exceptions.ShowNotExistException;
//import nimrod.cinema.exceptions.TheaterOutOfBoundsException;
//import nimrod.cinema.exceptions.UserNotExistException;
//
//import javax.ws.rs.BeanParam;
//import javax.ws.rs.core.Response;
//
///**
// * Created by Yuval on 20-Mar-17.
// * if any parameter is invalid throw proper exception
// */
//public class PurchaseParmsValidation implements PurchaseParmsValidationInterface {
//    //if method returns null then all parameters are valid
//    public Response checkParameters(@BeanParam PurchaseFilterBean filterBean) {
//
//        try {
//            new ValidateInput().validateInput(filterBean);
//        } catch (UserNotExistException | ShowInstanceNotExistException | ShowNotExistException | TheaterOutOfBoundsException e) {
//            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
//        }
//        return null;
//    }
//}