package org.yuval.purchase;

import com.mongodb.util.JSON;
import org.bson.Document;
import org.yuval.objects.Seat;

import javax.ws.rs.BeanParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.yuval.utils.Parameters.*;

/**
 * Created by Yuval on 17-Mar-17.
 * class that handles purchase request
 */
@Path("purchase_request")
public class PurchaseRequest {


    /**
     * @param filterBean the input from the HTTP request
     * @return response about the status of the request
     */
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response securedMethod(@BeanParam PurchaseFilterBean filterBean) {

        //check all input parameters
        Response errorMsg = new PurchaseParmsValidation().checkParameters(filterBean);
        if (errorMsg != null) {
            return errorMsg;
        }

        //lock the seat until the user confirms cancel or time runs out
        SingeltonShowInstanceMap singeltonShowInstanceMap = SingeltonShowInstanceMap.getInstance();
        MultitonShowInstance multitonShowInstance = singeltonShowInstanceMap.getMultitonShowInstance(filterBean.getShowInstanceID());
        if (multitonShowInstance.reserveSeat(filterBean.getRow() - 1, filterBean.getColumn() - 1,
                filterBean.getShowInstanceID(), filterBean.getShowId(), filterBean.getUser())) {


            return Response
                    .status(Response.Status.ACCEPTED)
                    .entity(SEAT_WAS_RESERVED)
                    .build();
        }

        return Response
                .status(Response.Status.CONFLICT)
                .entity(SEAT_RESERVASION_NOT_ACCEPTED)
                .build();
    }

    /**
     * @param filterBean the input from the HTTP request
     * @return the row and column of the seat if request was successful
     */
    @POST
    @Path("/{unmarked}")
    public Response secureMethodUnmarkedSeat(@BeanParam PurchaseFilterBean filterBean) {
//check all input parameters
        Response errorMsg = new PurchaseParmsValidation().checkParametersUnmarkedSeat(filterBean);
        if (errorMsg != null) {
            return errorMsg;
        }
        SingeltonShowInstanceMap singeltonShowInstanceMap = SingeltonShowInstanceMap.getInstance();
        MultitonShowInstance multitonShowInstance = singeltonShowInstanceMap.getMultitonShowInstance(filterBean.getShowInstanceID());
        Seat seat = multitonShowInstance.reserveUnmarkedSeat(filterBean.getShowInstanceID(), filterBean.getShowId(), filterBean.getUser());
        //could not find free
        if (!seat.isSaved()) {
            return Response.status(Response.Status.NOT_FOUND).entity(FREE_SEAT_NOT_FOUND).build();
        }
        Document document = new Document()
                .append(ROW_NUMBER,String.valueOf(seat.getRow()+1))
                .append(COLUMN_NUMBER,String.valueOf(seat.getColumn()+1));
        return Response.status(Response.Status.OK).entity(JSON.serialize(document)).build();
    }
}
