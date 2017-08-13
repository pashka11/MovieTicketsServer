package nimrod.cinema.WebApi;

import nimrod.cinema.dao.CRUD;
import nimrod.cinema.dao.DataAccessObject;
import nimrod.cinema.objects.MovieDetails;
import nimrod.cinema.objects.Screening;
import nimrod.cinema.utils.Constants;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

/**
 * Created by Nimrod on 14-Jun-17.
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
        CRUD<MovieDetails> crud = new DataAccessObject<>(MovieDetails.class);
        List<MovieDetails> movies = crud.ReadAll();

        if (movies != null && movies.size() != 0)
        {
//            List<MovieDetails> movies = movieDocs.parallelStream()
//                    .map(ViewAndDataObjectConverter::DBDocToMovieDetails)
//                    .collect(Collectors.toList());

            return Response.ok(movies).build();
        }
        else
            return Response.serverError().build();
    }

    @GET
    @Path("/{movieId}/screenings")
    public Response GetMovieScreenings(@PathParam("movieId") String  movieId)
    {
		CRUD<Screening> crud = new DataAccessObject<>(Screening.class);

		List<Screening> screenings = crud.ReadByField(Constants.Screening.MOVIE_ID, movieId);

		if (screenings != null)
		{
//			List<Screening> screenings =
//					screeningDocs
//							.stream()
//							.map(ViewAndDataObjectConverter::DBDocToScreening)
//							.collect(Collectors.toList());

			return Response.ok(screenings).build();
		}
		else
			return Response.serverError().build();
    }

    /**
     * @param movie object to add to DB
     * @return movie created URL
     */
    @POST
    public Response AddMovie(MovieDetails movie)
	{
    	CRUD<MovieDetails> dao = new DataAccessObject<>(MovieDetails.class);

		String id = dao.CreateOne(movie);

		return id.isEmpty() ?
				Response.created(URI.create("/movies/" + id)).build() :
				Response.serverError().build();
    }


    /**
     * @param movieId of the movie to delete
     * @return deletion status message
     */
    @DELETE
    @Path("/{movieId}")
    public Response deleteMovie(@PathParam("movieId") String movieId) {
		CRUD<MovieDetails> crud = new DataAccessObject<>(MovieDetails.class);

		if (crud.DeleteOne(movieId) != null)
        	return Response.ok().build();
		else
			return Response.serverError().build();
    }
}


//
//        if (crud.read(bandId) == null)
//            return Response.status(Response.Status.NOT_FOUND).entity(responseDocument.docResponse(DOES_NOT_EXIST)).build();
//        else if (usageCheck.isInUse(bandId))
//            return Response.status(Response.Status.FORBIDDEN).entity(responseDocument.docResponse(RESOURCE_IS_IN_USE)).build();
//        else if (!crud.delete(bandId))
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseDocument.docResponse(ERROR_IN_DELETION)).build();
//
//        return Response.ok(responseDocument.docResponse(RESOURCE_HAS_BEEN_DELETED)).build();
