package org.yuval.resource;

import com.mongodb.util.JSON;
import org.bson.Document;
import org.yuval.dao.Crud;
import org.yuval.dao.TheaterDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.yuval.utils.Parameters.*;

/**
 * Created by Yuval on 13-Mar-17.
 * This class handles the theater related HTTP requests
 */

@Path("/theaters")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TheaterResource {

    /**
     * @return all the theaters
     */
    @GET
    public Response getAllTheater() {
        List<Document> documentList =new TheaterDao().readAll();
        if (documentList == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(INVALID_THEATER_ID).build();
        }
        return Response.status(Response.Status.OK).entity(JSON.serialize(documentList)).build();
    }

    /**
     * @param theaterId to return
     * @return requested band
     */
    @GET
    @Path("/{theaterId}")
    public Response getBandById(@PathParam("theaterId") int theaterId) {
        Document document = new TheaterDao().read(String.valueOf(theaterId));
        if (document == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(INVALID_THEATER_ID).build();
        }
        return Response.status(Response.Status.OK).entity(JSON.serialize(document)).build();
    }

    /**
     * @param theater is a json format object to insert
     * @return insertion status message
     */
    @POST
    public Response insertTheater(String theater){
        //turn string into document
        Document document = Document.parse(theater);
        String s = new TheaterDao().insertValidation(document);
        //        there is a problem ,so we return info
        if (!s.equals(Crud.status.OK.toString())) {
            return Response.status(Response.Status.BAD_REQUEST).entity(s).build();
        }
        //        insertion went ok ,return OK status
        return Response.status(Response.Status.OK).entity(s).build();
    }

    /**
     * @param theater is a json format object to update
     * @return update status message
     */
    @PUT
    public Response updateTheater(String theater){
        Document document = Document.parse(theater);
        if (!new TheaterDao().update(document)){
            return Response.status(Response.Status.CONFLICT).entity(ERROR_IN_UPDATE_PROCESS).build();
        }
        return Response.status(Response.Status.ACCEPTED).entity(SUCCESSFULLY_UPDATED).build();
    }

    /**
     * @param theaterId to delete
     * @return deletion status message
     */
    @DELETE
    @Path("/{theaterId}")
    public Response deleteTheater(@PathParam("theaterId") String theaterId){
        //check if theater exists
        TheaterDao theaterDao = new TheaterDao();
        if (theaterDao.read(theaterId)==null){
               return  Response.status(Response.Status.NOT_FOUND).entity(DOES_NOT_EXIST).build();
        }
        //check if theater is in use - if not delete it
        if (theaterDao.isInUse(theaterId)){
            return Response.status(Response.Status.FORBIDDEN).entity(RESOURCE_IS_IN_USE).build();
        }
        //theater is not in use - try to delete it and return proper response
        if (theaterDao.drop(theaterId)==false){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ERROR_IN_DELETION).build();
        }
        return Response.status(Response.Status.OK).entity(RESOURCE_HAS_BEEN_DELETED).build();
    }
}
