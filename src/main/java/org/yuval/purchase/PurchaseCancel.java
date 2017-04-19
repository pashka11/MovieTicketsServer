package org.yuval.purchase;

import javax.ws.rs.BeanParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.yuval.utils.Parameters.SEAT_WAS_RELEASED;

/**
 * Created by Yuval on 17-Mar-17.
 * class that handles purchase cancellation
 */
@Path("purchase_cancel")
public class PurchaseCancel {


    /**
     * @param filterBean includes row, column, user, id, show, showInstance
     * @return response about the status of seat release
     */
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response securedMethod(@BeanParam PurchaseFilterBean filterBean) {

//check all input parameters
        PurchaseParmsValidationInterface validationInterface = new PurchaseParmsValidation();
        Response errorMsg = validationInterface.checkParameters(filterBean);
        if (errorMsg != null) {
            return errorMsg;
        }

        //cancel the seat
        SingeltonShowInstanceMap singeltonShowInstanceMap = SingeltonShowInstanceMap.getInstance();
        MultitonShowInstance multitonShowInstance = singeltonShowInstanceMap.getMultitonShowInstance(filterBean.getShowInstanceID());
        multitonShowInstance.releaseSeat(filterBean.getRow() - 1, filterBean.getColumn() - 1, filterBean.getShowId(), filterBean.getUser());


        return Response
                .status(Response.Status.ACCEPTED)
                .entity(SEAT_WAS_RELEASED)
                .build();
    }


}
