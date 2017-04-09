package org.yuval.resource;

import com.mongodb.util.JSON;
import org.bson.Document;
import org.yuval.dao.Crud;
import org.yuval.dao.UserDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.yuval.utils.Parameters.*;

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
        List<Document> documentList =new UserDao().readAll();
        if (documentList == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(INVALID_USER_ID).build();
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
        Document document = new UserDao().read(String.valueOf(userId));
        if (document == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(INVALID_USER_ID).build();
        }
        return Response.status(Response.Status.OK).entity(JSON.serialize(document)).build();
    }

    /**
     * @param user is a json format object to insert
     * @return insertion status message
     */
    @POST
    public Response insertUser(String user){
        //turn string into document
        Document document = Document.parse(user);
        String s = new UserDao().insertValidation(document);
        //        there is a problem ,so we return info
        if (!s.equals(Crud.status.OK.toString())) {
            return Response.status(Response.Status.BAD_REQUEST).entity(s).build();
        }
        //        insertion went ok ,return OK status
        return Response.status(Response.Status.OK).entity(s).build();
    }

    /**
     * @param userId to delete
     * @return deletion status message
     */
    @DELETE
    @Path("/{userId}")
    public Response deleteUser(@PathParam("userId")String userId){
        UserDao userDao = new UserDao();
        //check if user exists
        if (userDao.read(userId)==null){
            return  Response.status(Response.Status.NOT_FOUND).entity(DOES_NOT_EXIST).build();
        }
        //try to delete and return proper response
        if (userDao.drop(userId)==false){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ERROR_IN_DELETION).build();
        }
        return Response.status(Response.Status.OK).entity(RESOURCE_HAS_BEEN_DELETED).build();
    }
}
