package org.yuval.resource;

import com.mongodb.util.JSON;
import org.bson.Document;
import org.yuval.dao.Crud;
import org.yuval.dao.ShowInstanceDao;
import org.yuval.utils.Helpers;
import org.yuval.utils.ResponseDocument;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.yuval.utils.Parameters.*;

/**
 * Created by Yuval on 28-Mar-17.
 * This class handles the show instance related HTTP requests
 */
@Path("/show_instance")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ShowInstanceResource {

    /**
     * @param showInstanceId is a json format object to insert
     * @return insertion status message
     */
    @POST
    public Response insertShowInstance(String showInstanceId) {
        ResponseDocument responseDocument = new Helpers();
        //turn string into document
        Document document = Document.parse(showInstanceId);
        String s = new ShowInstanceDao().insertValidation(document);
        //        there is a problem ,so we return info
        if (!s.equals(Crud.status.OK)) {
            return Response.status(Response.Status.BAD_REQUEST).entity(JSON.serialize(responseDocument.docResponse(s))).build();
        }
        //        insertion went ok ,return OK status
        return Response.status(Response.Status.OK).entity(JSON.serialize(responseDocument.docResponse(s))).build();
    }

    /**
     * @param showInstanceId to delete
     * @return deletion status message
     */
    @DELETE
    @Path("/{showInstanceId}")
    public Response deleteShowInstance(@PathParam("showInstanceId") String showInstanceId) {
        ResponseDocument responseDocument = new Helpers();
        //check if show instance exists
        ShowInstanceDao showInstanceDao = new ShowInstanceDao();
        if (showInstanceDao.read(showInstanceId) == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(JSON.serialize(responseDocument.docResponse(DOES_NOT_EXIST))).build();
        }
        if (showInstanceDao.isInUse(showInstanceId)) {
            return Response.status(Response.Status.FORBIDDEN).entity(JSON.serialize(responseDocument.docResponse(RESOURCE_IS_IN_USE))).build();
        }
        if (!showInstanceDao.drop(showInstanceId)) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(JSON.serialize(responseDocument.docResponse(ERROR_IN_DELETION))).build();
        }
        return Response.status(Response.Status.OK).entity(JSON.serialize(responseDocument.docResponse(RESOURCE_HAS_BEEN_DELETED))).build();
    }
}
