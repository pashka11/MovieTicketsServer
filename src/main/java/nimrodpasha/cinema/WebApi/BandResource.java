package nimrodpasha.cinema.WebApi;

import com.mongodb.util.JSON;
import nimrodpasha.cinema.dao.BandDao;
import nimrodpasha.cinema.dao.Crud;
import nimrodpasha.cinema.objects.MovieDetails;
import nimrodpasha.cinema.utils.Helpers;
import org.bson.Document;
import nimrodpasha.cinema.dao.UsageCheck;
import nimrodpasha.cinema.utils.ResponseDocument;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static nimrodpasha.cinema.utils.Parameters.*;

/**
 * Created by Yuval on 13-Mar-17.
 * This class handles the band related HTTP requests
 */

@Path("/movies")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class BandResource {

    /**
     * @return all the bands
     */
    @GET
    public List<MovieDetails> getAllBands() {
//        Crud crud = new BandDao();
//        List<Document>documentList = crud.readAll();
//        if (documentList == null) {
//            return Response.status(Response.Status.NOT_FOUND).entity(INVALID_BAND_ID).build();
//        }
//        return Response.status(Response.Status.OK).entity(JSON.serialize(documentList)).build();

        //Response.status(Response.Status.OK).entity("Pasha kaki").build();
		List<MovieDetails> movies = new ArrayList<>();
		MovieDetails m1 = new MovieDetails();
		m1.Name = "shit";
		m1.Description = "Wtf is this shit";
		m1.ImageName = "acdc.jpg";

		MovieDetails m2 = new MovieDetails();
		m2.Name = "shitface";
		m2.Description = "Wtf is this shit";
		m2.ImageName = "Aerosmith.jpg";

		movies.add(m1);
		movies.add(m2);

        return movies;
    }


    /**
     * @param bandId to return
     * @return requested band
     */
    @GET
    @Path("/{bandId}")
    public Response getBandById(@PathParam("bandId") int bandId) {
        Crud crud = new BandDao();
        Document document = crud.read(String.valueOf(bandId));
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
        Crud crud = new BandDao();
        ResponseDocument responseDocument = new Helpers();
        //turn string into document
        Document document = Document.parse(band);
        String s = crud.insertValidation(document);
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
        Crud crud = new BandDao();
        Document document = Document.parse(band);
        if (!crud.update(document)) {
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
        Crud crud = new BandDao();
        UsageCheck usageCheck = new BandDao();
        if (crud.read(bandId) == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(JSON.serialize(responseDocument.docResponse(DOES_NOT_EXIST))).build();
        }
        if (usageCheck.isInUse(bandId)) {
            return Response.status(Response.Status.FORBIDDEN).entity(JSON.serialize(responseDocument.docResponse(RESOURCE_IS_IN_USE))).build();
        }
        if (!crud.drop(bandId)) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(JSON.serialize(responseDocument.docResponse(ERROR_IN_DELETION))).build();
        }
        return Response.status(Response.Status.OK).entity(JSON.serialize(responseDocument.docResponse(RESOURCE_HAS_BEEN_DELETED))).build();
    }
}
