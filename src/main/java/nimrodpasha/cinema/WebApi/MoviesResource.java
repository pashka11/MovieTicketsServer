package nimrodpasha.cinema.WebApi;

import com.mongodb.util.JSON;
import nimrodpasha.cinema.dao.BandDao;
import nimrodpasha.cinema.dao.Crud;
import nimrodpasha.cinema.dao.UsageCheck;
import nimrodpasha.cinema.objects.MovieDetails;
import nimrodpasha.cinema.objects.Screening;
import nimrodpasha.cinema.utils.Helpers;
import nimrodpasha.cinema.utils.ResponseDocument;
import org.bson.Document;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static nimrodpasha.cinema.utils.Parameters.*;

/**
 * Created by Nimrod on 14-Jun-17..
 */


@Path("/movies")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class MoviesResource
{

    /**
     * @return all the bands


         .*/
    @GET


    public Response getAllBands() {
            Crud crud = new BandDao();
            List<Document> documentList = crud.readAll();

            if (documentList == null) {
                return Response.status(Response.Status.NOT_FOUND).entity(INVALID_BAND_ID).build();
            }
            return Response.status(Response.Status.OK).entity(JSON.serialize(documentList)).build();
        }




//@GET
//public List<MovieDetails> getAllBands(){
//    List<String> actors = Arrays.asList("Brad ArmPit", "Joshua Weinstein", "Pavel Poltzasky",  "Vin Patrol", "Sara Jassica Peter Parker");
//    List<String> images = Arrays.asList("acdc.jpg", "BlackSabbath.jpg", "guns_N_roses.jpg", "led_zeppelin.jpg", "Metallica.png");
//
//    List<MovieDetails> movies = new ArrayList<>();
//
//		for(int i = 0; i <5; i++)
//    {
//        MovieDetails movie = new MovieDetails();
//        movie.Name = images.get(i);
//        movie.Id = i;
//        movie.Description = "What is this shitting shit that going on between the shitting fuck number " + i;
//        movie.ImageName = images.get(i);
//        movie.Actors = actors;
//        movie.Director = "Mr Gas Fringe";
//        movie.Duration = (short) (120 * (i+1));
//        movie.Genres = "Horror, Not Horror, Comedy, Hentai";
//        movie.ReleaseDate = LocalDate.now();
//
//        movies.add(movie);
//    }
//
//        return movies;
//}
//


    @GET
    @Path("/{movieId}")
    public MovieDetails getMovieById(@PathParam("movieId") int movieId)  {

    	List<String> actors = Arrays.asList("Brad ArmPit, Joshua Weinstein, Pavel Poltzasky. Vin Patrol, Sara Jassica Peter Parker");
    	
        List<MovieDetails> movies = new ArrayList<>();
        MovieDetails m0 = new MovieDetails();
        m0.Name = "0";
        m0.Id = 0;
        m0.Description = "0";
        m0.ImageName = "0";
        m0.Actors = actors;

        MovieDetails m1 = new MovieDetails();
        m1.Name = "shit";
        m1.Id = 1;
        m1.Description = "Wtf is this shit";
        m1.ImageName = "acdc.jpg";

        MovieDetails m2 = new MovieDetails();
        m2.Name = "shitface";
        m2.Id = 2;
        m2.Description = "Wtf is this shit";
        m2.ImageName = "Aerosmith.jpg";

        movies.add(m1);
        movies.add(m2);

        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).Id == movieId)
                return movies.get(i);
        }

        return m0;
    }


    /**
     * @param bandId to return
     * @return requested band
     */
//    @GET
//    @Path("/{bandId}")
//    public Response getBandById(@PathParam("bandId") int bandId) {
//        Crud crud = new BandDao();
//        Document document = crud.read(String.valueOf(bandId));
//        if (document == null) {
//            return Response.status(Response.Status.NOT_FOUND).entity(INVALID_BAND_ID).build();
//        }
//        return Response.status(Response.Status.OK).entity(JSON.serialize(document)).build();
//    }





    /**
     * @param movieId
     * @return movie future screenings (month range)
     */

    @GET
    @Path("/{movieId}/screenings")
    public List<Screening> GetMovieScreenings(@PathParam("movieId") int movieId) {
//        Crud crud = new BandDao();
//        Document document = crud.read(String.valueOf(bandId));
//        if (document == null) {
//            return Response.status(Response.Status.NOT_FOUND).entity(INVALID_BAND_ID).build();
//        }
//        return Response.status(Response.Status.OK).entity(JSON.serialize(document)).build();

		List<Screening> screenings = new ArrayList<>(4);

		MovieDetails m2 = new MovieDetails();
		m2.Name = "shitface";
		m2.Id = movieId;
		m2.Description = "Wtf is this shit";
		m2.ImageName = "Aerosmith.jpg";

		for (int i = 0; i < 4; i++)
		{
			Screening scr = new Screening();
			scr.MovieId = m2.Id;
			scr.ScreeningTime = LocalDateTime.now().plusDays(i + 1);
			scr.HallId = i + 1;
			scr.Seats = new int[3][3];

			screenings.add(scr);
		}

		return screenings;
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


