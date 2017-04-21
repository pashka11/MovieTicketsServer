package org.yuval.resource;

import com.mongodb.util.JSON;
import org.bson.Document;
import org.yuval.dao.Crud;
import org.yuval.dao.ShowInstanceDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.yuval.utils.Parameters.INVALID_SHOW_INSTANCE_ID;
import static org.yuval.utils.Parameters.SHOW_INSTANCE_SEATS;

/**
 * Created by Yuval on 18-Mar-17.
 * This class handles the seat related HTTP requests
 */
@Path("/seats")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SeatsResource {


    /**
     * @param showInstanceId to return
     * @return requested show instance seats
     */
    @GET
    @Path("/{showInstanceId}")
    public Response getShowInstanceSeats(@PathParam("showInstanceId") String showInstanceId) {
        Crud crud =new ShowInstanceDao();
        Document document = crud.read(showInstanceId);
        if (document!=null && document.get(SHOW_INSTANCE_SEATS)!=null){
            return Response.status(Response.Status.OK).entity(JSON.serialize(document.get(SHOW_INSTANCE_SEATS))).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(INVALID_SHOW_INSTANCE_ID).build();
    }
}
