package nimrodpasha.cinema.WebApi;

import nimrodpasha.cinema.dao.Crud;
import nimrodpasha.cinema.dao.MovieDao;
import nimrodpasha.cinema.dao.ScreeningsDao;
import nimrodpasha.cinema.objects.Converters.ViewAndDataObjectConverter;
import nimrodpasha.cinema.objects.MovieDetails;
import nimrodpasha.cinema.objects.Screening;
import org.bson.Document;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nimrod on 14-Jun-17.....
 */


@Path("/movies")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class MoviesResource
{
    /**
     * @return all the movies
    .*/
    @GET
    public Response GetAllMoviesDetails()
    {
        Crud crud = new MovieDao();
        List<Document> movieDocs = crud.readAll();

        if (movieDocs != null && movieDocs.size() != 0)
        {
            List<MovieDetails> movies = movieDocs.parallelStream()
                    .map(x -> ViewAndDataObjectConverter.DBDocToMovieDetails(x))
                    .collect(Collectors.toList());

            return Response.ok(movies).build();
        }
        else
            return Response.serverError().build();
    }

    @GET
    @Path("/{movieId}/screenings")
    public Response GetMovieScreenings(@PathParam("movieId") int  movieId)
    {
//        ArrayList<Screening> screenings = new ArrayList<>();
//
//        ArrayList<ArrayList<Integer>> rows = new ArrayList<>();
//
//		for (int i = 0; i < 11; i++)
//            rows.add(new ArrayList<>(Arrays.asList(0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0)));
//
//		for (int i = 0; i < 4; i++)
//		{
//			Screening scr = new Screening();
//			scr.Time = LocalDateTime.now().plusDays(i + 1);
//			scr.HallId = i + 1;
//			scr.Seats = rows;
//
//			screenings.add(scr);
//		}
//
//		return Response.ok(screenings).build();

		ScreeningsDao dao = new ScreeningsDao();

		List<Document> screeningDocs = dao.GetScreeningsByMovieId(movieId);

		if (screeningDocs != null)
		{
			List<Screening> screenings =
					screeningDocs
							.stream()
							.map(ViewAndDataObjectConverter::DBDocToScreening)
							.collect(Collectors.toList());

			return Response.ok(screenings).build();
		}
		else
			return Response.serverError().build();
    }

    /**
     * @param band is a json format object to insert
     * @return insertion status message
     */
    @POST
    public Response insertBand(String band) {
//        Crud crud = new BandDao();
//        //turn string into document
//        Document document = Document.parse(band);
//        String s = crud.insertValidation(document);
//        //        there is a problem ,so we return info
//        if (!s.equals(Crud.status.OK.toString())) {
//            return Response.status(Response.Status.BAD_REQUEST).entity(JSON.serialize(responseDocument.docResponse(s))).build();
//        }
//        //        insertion went ok ,return OK status
//        return Response.status(Response.Status.OK).entity(JSON.serialize(responseDocument.docResponse(s))).build();
        return Response.ok().build();
    }

    /**
     * @param band is a json format object to update
     * @return update status message
     */
    @PUT
    public Response updateBand(String band) {
//        ResponseDocument responseDocument = new Helpers();
//        Crud crud = new BandDao();
//        Document document = Document.parse(band);
//        if (!crud.update(document)) {
//            return Response.status(Response.Status.CONFLICT).entity(JSON.serialize(responseDocument.docResponse(ERROR_IN_UPDATE_PROCESS))).build();
//        }
//        return Response.status(Response.Status.ACCEPTED).entity(JSON.serialize(responseDocument.docResponse(SUCCESSFULLY_UPDATED))).build();
        return Response.ok().build();
    }

    /**
     * @param bandId to delete
     * @return deletion status message
     */
    @DELETE
    @Path("/{movieId}")
    public Response deleteMovie(@PathParam("movieId") String bandId) {
//        ResponseDocument responseDocument = new Helpers();
//        Crud crud = new MovieDao();
//        UsageCheck usageCheck = new BandDao();
//
//        if (crud.read(bandId) == null)
//            return Response.status(Response.Status.NOT_FOUND).entity(responseDocument.docResponse(DOES_NOT_EXIST)).build();
//        else if (usageCheck.isInUse(bandId))
//            return Response.status(Response.Status.FORBIDDEN).entity(responseDocument.docResponse(RESOURCE_IS_IN_USE)).build();
//        else if (!crud.drop(bandId))
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseDocument.docResponse(ERROR_IN_DELETION)).build();
//
//        return Response.ok(responseDocument.docResponse(RESOURCE_HAS_BEEN_DELETED)).build();

        return Response.ok().build();
    }
}


