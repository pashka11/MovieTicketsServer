package org.yuval.resource;

import com.mongodb.util.JSON;
import org.yuval.dao.ShowInstanceDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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
     * @param showInstanceId to return its seats
     * @return requested show instance seats
     */
    @GET
    @Path("/{showInstanceId}")
    public String getShowById(@PathParam("showInstanceId") String showInstanceId) {
        return JSON.serialize(new ShowInstanceDao().read(showInstanceId).get(SHOW_INSTANCE_SEATS));
    }
}
