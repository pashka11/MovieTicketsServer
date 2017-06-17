package nimrodpasha.cinema.purchase;

import nimrodpasha.cinema.utils.Parameters;

import javax.ws.rs.BeanParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Yuval on 17-Mar-17.
 * class that handles purchase request
 */
@Path("purchase_request")
public class PurchaseRequest {



        @POST
        @Produces(MediaType.TEXT_PLAIN)
        public Response securedMethod(@BeanParam PurchaseFilterBean filterBean){

        //check all input parameters
            PurchaseParmsValidationInterface validationInterface = new PurchaseParmsValidation();
            Response errorMsg = validationInterface.checkParameters(filterBean);
            if (errorMsg!=null){
                return errorMsg;
            }

            //lock the seat until the user confirms cancel or time runs out
            SingeltonShowInstanceMap singeltonShowInstanceMap= SingeltonShowInstanceMap.getInstance();
            MultitonShowInstance multitonShowInstance =singeltonShowInstanceMap.getMultitonShowInstance(filterBean.getShowInstanceID());
            if (multitonShowInstance.reserveSeat(filterBean.getRow()-1,filterBean.getColumn()-1,
                    filterBean.getShowInstanceID(),filterBean.getShowId(),filterBean.getUser())){


                return Response
                        .status(Response.Status.ACCEPTED)
                        .entity(Parameters.SEAT_WAS_RESERVED)
                        .build();
            }

            return Response
                    .status(Response.Status.CONFLICT)
                    .entity(Parameters.SEAT_RESERVASION_NOT_ACCEPTED)
                    .build();
        }
}
