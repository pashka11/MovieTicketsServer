package nimrodpasha.cinema.WebApi;

import com.mongodb.util.JSON;
import nimrodpasha.cinema.dao.TheaterDao;
import nimrodpasha.cinema.utils.Helpers;
import nimrodpasha.cinema.dao.UsageCheck;
import nimrodpasha.cinema.utils.Parameters;
import nimrodpasha.cinema.utils.ResponseDocument;
import org.bson.Document;
import nimrodpasha.cinema.dao.Crud;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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
        Crud crud = new TheaterDao();
        List<Document> documentList =crud.readAll();
        if (documentList == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(Parameters.INVALID_THEATER_ID).build();
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
        Crud crud = new TheaterDao();
        Document document = crud.read(String.valueOf(theaterId));
        if (document == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(Parameters.INVALID_THEATER_ID).build();
        }
        return Response.status(Response.Status.OK).entity(JSON.serialize(document)).build();
    }

    /**
     * @param theater is a json format object to insert
     * @return insertion status message
     */
    @POST
    public Response insertTheater(String theater){
        ResponseDocument responseDocument = new Helpers();
        //turn string into document
        Document document = Document.parse(theater);
        Crud crud = new TheaterDao();
        String s = crud.insertValidation(document);
        //        there is a problem ,so we return info
        if (!s.equals(Crud.status.OK.toString())) {
            return Response.status(Response.Status.BAD_REQUEST).entity(JSON.serialize(responseDocument.docResponse(s))).build();
        }
        //        insertion went ok ,return OK status
        return Response.status(Response.Status.OK).entity(JSON.serialize(responseDocument.docResponse(s))).build();
    }

    /**
     * @param theater is a json format object to update
     * @return update status message
     */
    @PUT
    public Response updateTheater(String theater){
        ResponseDocument responseDocument = new Helpers();
        Document document = Document.parse(theater);
        Crud crud = new TheaterDao();
        if (!crud.update(document)){
            return Response.status(Response.Status.CONFLICT).entity(JSON.serialize(responseDocument.docResponse(Parameters.ERROR_IN_UPDATE_PROCESS))).build();
        }
        return Response.status(Response.Status.ACCEPTED).entity(JSON.serialize(responseDocument.docResponse(Parameters.SUCCESSFULLY_UPDATED))).build();
    }

    /**
     * @param theaterId to delete
     * @return deletion status message
     */
    @DELETE
    @Path("/{theaterId}")
    public Response deleteTheater(@PathParam("theaterId") String theaterId){
        ResponseDocument responseDocument = new Helpers();
        //check if theater exists
        Crud crud = new TheaterDao();
        UsageCheck usageCheck = new TheaterDao();
        if (crud.read(theaterId)==null){
               return  Response.status(Response.Status.NOT_FOUND).entity(JSON.serialize(responseDocument.docResponse(Parameters.DOES_NOT_EXIST))).build();
        }
        //check if theater is in use - if not delete it
        if (usageCheck.isInUse(theaterId)){
            return Response.status(Response.Status.FORBIDDEN).entity(JSON.serialize(responseDocument.docResponse(Parameters.RESOURCE_IS_IN_USE))).build();
        }
        //theater is not in use - try to delete it and return proper response
        if (!crud.drop(theaterId)){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(JSON.serialize(responseDocument.docResponse(Parameters.ERROR_IN_DELETION))).build();
        }
        return Response.status(Response.Status.OK).entity(JSON.serialize(responseDocument.docResponse(Parameters.RESOURCE_HAS_BEEN_DELETED))).build();
    }
}
