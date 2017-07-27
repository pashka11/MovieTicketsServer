//package nimrodpasha.cinema.purchase;
//
//
//import nimrodpasha.cinema.dao.TicketUpdate;
//import nimrodpasha.cinema.dao.UserDao;
//
//import javax.ws.rs.BeanParam;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//import static nimrodpasha.cinema.utils.Parameters.SEAT_WAS_NOT_PURCHASED;
//import static nimrodpasha.cinema.utils.Parameters.SEAT_WAS_PURCHASED;
//
///**
// * Created by Yuval on 17-Mar-17.
// * class that handles purchase approval
// */
//@Path("purchase_approve")
//public class PurchaseApprove implements CheckParameters {
//
//
//    /**
//     * @param filterBean includes row, column, user, id, show, showInstance
//     * @return response about the status of purchase
//     */
//    @POST
//    @Produces(MediaType.TEXT_PLAIN)
//    public Response securedMethod(@BeanParam PurchaseFilterBean filterBean) {
//
////check all input parameters
//        PurchaseParmsValidationInterface validationInterface = new PurchaseParmsValidation();
//        Response errorMsg = validationInterface .checkParameters(filterBean);
//        if (errorMsg != null) {
//            return errorMsg;
//        }
//
//        //lock the seat until the user confirms
//        SingeltonShowInstanceMap singeltonShowInstanceMap = SingeltonShowInstanceMap.getInstance();
//        MultitonShowInstance multitonShowInstance = singeltonShowInstanceMap.getMultitonShowInstance(filterBean.getShowInstanceID());
//        if (multitonShowInstance.approveSeat(filterBean.getRow() - 1, filterBean.getColumn() - 1, filterBean.getShowId(), filterBean.getUser())) {
//
//
//            //insert ticket into user document
//            TicketUpdate ticketUpdate = new UserDao();
//            ticketUpdate.setTicket(filterBean.getRow() - 1, filterBean.getColumn() - 1, filterBean.getShowInstanceID(), filterBean.getUser(), filterBean.getShowId());
//
//            return Response
//                    .status(Response.Status.ACCEPTED)
//                    .entity(SEAT_WAS_PURCHASED)
//                    .build();
//        }
//
//        return Response
//                .status(Response.Status.CONFLICT)
//                .entity(SEAT_WAS_NOT_PURCHASED)
//                .build();
//    }
//}
