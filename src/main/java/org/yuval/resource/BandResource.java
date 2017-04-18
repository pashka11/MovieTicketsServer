package org.yuval.resource;

import com.mongodb.util.JSON;
import org.bson.Document;
import org.yuval.dao.BandDao;
import org.yuval.dao.Crud;
import org.yuval.utils.Helpers;
import org.yuval.utils.ResponseDocument;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.yuval.utils.Parameters.*;

/**
 * Created by Yuval on 13-Mar-17.
 * This class handles the band related HTTP requests
 */

@Path("/bands")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class BandResource {

    /**
     * @return all the bands
     */
    @GET
    public Response getAllBands() {
        List<Document>documentList =new BandDao().readAll();
        if (documentList == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(INVALID_BAND_ID).build();
        }
        return Response.status(Response.Status.OK).entity(JSON.serialize(documentList)).build();
    }


    /**
     * @param bandId to return
     * @return requested band
     */
    @GET
    @Path("/{bandId}")
    public Response getBandById(@PathParam("bandId") int bandId) {
        Document document = new BandDao().read(String.valueOf(bandId));
        if (document == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(INVALID_BAND_ID).build();
        }
        return Response.status(Response.Status.OK).entity(JSON.serialize(document)).build();
    }

    /**
     * @param band is a json format object to insert
     * @return insertion status message
     */
    @POST
    public Response insertBand(String band) {
        ResponseDocument responseDocument = new Helpers();
        //turn string into document
        Document document = Document.parse(band);
        String s = new BandDao().insertValidation(document);
        //        there is a problem ,so we return info
        if (!s.equals(Crud.status.OK.toString())) {
            return Response.status(Response.Status.BAD_REQUEST).entity(JSON.serialize(responseDocument.docResponse(s))).build();
        }
        //        insertion went ok ,return OK status
        return Response.status(Response.Status.OK).entity(JSON.serialize(responseDocument.docResponse(s))).build();
    }

    /**
     * @param band is a json format object to update
     * @return update status message
     */
    @PUT
    public Response updateBand(String band) {
        ResponseDocument responseDocument = new Helpers();
        Document document = Document.parse(band);
        if (!new BandDao().update(document)) {
            return Response.status(Response.Status.CONFLICT).entity(JSON.serialize(responseDocument.docResponse(ERROR_IN_UPDATE_PROCESS))).build();
        }
        return Response.status(Response.Status.ACCEPTED).entity(JSON.serialize(responseDocument.docResponse(SUCCESSFULLY_UPDATED))).build();
    }

    /**
     * @param bandId to delete
     * @return deletion status message
     */
    @DELETE
    @Path("/{bandId}")
    public Response deleteBand(@PathParam("bandId") String bandId) {
        ResponseDocument responseDocument = new Helpers();
        BandDao bandDao = new BandDao();
        if (bandDao.read(bandId) == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(JSON.serialize(responseDocument.docResponse(DOES_NOT_EXIST))).build();
        }
        if (bandDao.isInUse(bandId)) {
            return Response.status(Response.Status.FORBIDDEN).entity(JSON.serialize(responseDocument.docResponse(RESOURCE_IS_IN_USE))).build();
        }
        if (!bandDao.drop(bandId)) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(JSON.serialize(responseDocument.docResponse(ERROR_IN_DELETION))).build();
        }
        return Response.status(Response.Status.OK).entity(JSON.serialize(responseDocument.docResponse(RESOURCE_HAS_BEEN_DELETED))).build();
    }
}
