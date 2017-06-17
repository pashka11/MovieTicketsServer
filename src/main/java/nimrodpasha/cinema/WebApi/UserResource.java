package nimrodpasha.cinema.WebApi;

import com.mongodb.util.JSON;
import nimrodpasha.cinema.dao.Crud;
import nimrodpasha.cinema.dao.UserDao;
import nimrodpasha.cinema.utils.Helpers;
import nimrodpasha.cinema.utils.Parameters;
import nimrodpasha.cinema.utils.ResponseDocument;
import org.bson.Document;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Yuval on 20-Mar-17.
 * This class handles the user related HTTP requests
 */
@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    /**
     * @return all the users
     */
    @GET
    public Response getAllUsers() {
        Crud crud = new UserDao();
        List<Document> documentList =crud.readAll();
        if (documentList == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(Parameters.INVALID_USER_ID).build();
        }
        return Response.status(Response.Status.OK).entity(JSON.serialize(documentList)).build();
    }

    /**
     * @param userId to return
     * @return requested user
     */
    @GET
    @Path("/{userId}")
    public Response getUserTickets(@PathParam("userId")String userId){
        Crud crud = new UserDao();
        Document document = crud.read(String.valueOf(userId));
        if (document == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(Parameters.INVALID_USER_ID).build();
        }
        return Response.status(Response.Status.OK).entity(JSON.serialize(document.get(Parameters.USER_TICKETS))).build();
    }

    /**
     * @param user is a json format object to insert
     * @return insertion status message
     */
    @POST
    public Response insertUser(String user){
        //turn string into document
        Document document = Document.parse(user);
        Crud crud = new UserDao();
        ResponseDocument responseDocument = new Helpers();
        String s = crud.insertValidation(document);
        //        there is a problem ,so we return info
        if (!s.equals(Crud.status.OK.toString())) {
            return Response.status(Response.Status.BAD_REQUEST).entity(JSON.serialize(responseDocument.docResponse(s))).build();
        }
        //        insertion went ok ,return OK status
        return Response.status(Response.Status.OK).entity(JSON.serialize(responseDocument.docResponse(s))).build();
    }

    /**
     * @param userId to delete
     * @return deletion status message
     */
    @DELETE
    @Path("/{userId}")
    public Response deleteUser(@PathParam("userId")String userId){
        ResponseDocument responseDocument = new Helpers();
        Crud crud = new UserDao();
        //check if user exists
        if (crud.read(userId)==null){
            return  Response.status(Response.Status.NOT_FOUND).entity(JSON.serialize(responseDocument.docResponse(Parameters.ERROR_IN_DELETION))).build();
        }
        //try to delete and return proper response
        if (!crud.drop(userId)){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(JSON.serialize(responseDocument.docResponse(Parameters.ERROR_IN_DELETION))).build();
        }
        return Response.status(Response.Status.OK).entity(JSON.serialize(responseDocument.docResponse(Parameters.RESOURCE_HAS_BEEN_DELETED))).build();
    }
}
