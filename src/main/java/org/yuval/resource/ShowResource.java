package org.yuval.resource;

import com.mongodb.util.JSON;
import org.bson.Document;
import org.yuval.dao.ShowDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.yuval.utils.Parameters.*;

/**
 * Created by Yuval on 14-Mar-17.
 * This class handles the show related HTTP requests
 */
@Path("/shows")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ShowResource {

    /**
     * @return all the shows
     */
    @GET
    public String getAllShows() {
        return JSON.serialize(new ShowDao().readAll());
    }


    /**
     * @param showId to return
     * @return requested show
     */
    @GET
    @Path("/{showId}")
    public String getShowById(@PathParam("showId") int showId) {
        return JSON.serialize(new ShowDao().showInstancesForShowNoSeats(showId).get(SHOW_INSTANCE));
    }

    /**
     * @param showId is a json format object to insert
     * @return insertion status message
     */
    @POST
    public String insertShow(String showId){
        //turn string into document
        Document document = Document.parse(showId);
        return new ShowDao().insertValidation(document);
    }

    /**
     * @param show is a json format object to update
     * @return update status message
     */
    @PUT
    public Response updateBand(String show){
        Document document = Document.parse(show);
        if (!new ShowDao().update(document)){
            return Response.status(Response.Status.CONFLICT).entity(ERROR_IN_UPDATE_PROCESS).build();
        }
        return Response.status(Response.Status.ACCEPTED).entity(SUCCESSFULLY_UPDATED).build();
    }

    /**
     * @param showId is a json format object to delete
     * @return deletion status message
     */
    @DELETE
    @Path("/{showId}")
    public Response deleteShow(@PathParam("showId")String showId){
        ShowDao showDao = new ShowDao();
        if (showDao.read(showId)==null){
            return  Response.status(Response.Status.NOT_FOUND).entity(DOES_NOT_EXIST).build();
        }
        if (showDao.isInUse(showId)){
            return Response.status(Response.Status.FORBIDDEN).entity(RESOURCE_IS_IN_USE).build();
        }
        if (showDao.drop(showId)==false){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ERROR_IN_DELETION).build();
        }
        return Response.status(Response.Status.OK).entity(RESOURCE_HAS_BEEN_DELETED).build();
    }
}
