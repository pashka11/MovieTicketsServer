package org.yuval.resource;

import com.mongodb.util.JSON;
import org.bson.Document;
import org.yuval.dao.Crud;
import org.yuval.dao.ShowDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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
    public Response getAllShows() {
        List<Document> documentArrayList = new ShowDao().readAll();
        if (new ShowDao().readAll() == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(INVALID_SHOW_ID).build();
        }
        return Response.status(Response.Status.OK).entity(JSON.serialize(documentArrayList)).build();
    }



    /**
     * @param showId to return
     * @return requested show
     */
    @GET
    @Path("/{showId}")
    public Response getShowById(@PathParam("showId") int showId) {
        Document document = new ShowDao().read(String.valueOf(showId));
        if (document == null) {
        return Response.status(Response.Status.NOT_FOUND).entity(INVALID_SHOW_ID).build();
    }
        return Response.status(Response.Status.OK).entity(JSON.serialize(document)).build();
    }
    /**
     * @param showId is a json format object to insert
     * @return insertion status message
     */
    @POST
    public Response insertShow(String showId){
        //turn string into document
        Document document = Document.parse(showId);
        String s = new ShowDao().insertValidation(document);
        //        there is a problem ,so we return info
        if (!s.equals(Crud.status.OK.toString())) {
            return Response.status(Response.Status.BAD_REQUEST).entity(s).build();
        }
        //        insertion went ok ,return OK status
        return Response.status(Response.Status.OK).entity(s).build();
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
