package nimrod.cinema.WebApi;

import nimrod.cinema.Managers.MoviesManager;
import nimrod.cinema.dao.CRUD;
import nimrod.cinema.dao.DataAccessObject;
import nimrod.cinema.objects.MovieDetails;
import nimrod.cinema.objects.Screening;
import nimrod.cinema.utils.Constants;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
            return Response.ok(movies).build();
        else
            return Response.serverError().build();
    }

    @GET
    @Path("/{movieId}/screenings")
    public Response GetMovieScreenings(@PathParam("movieId") String  movieId)
    {
        CRUD<Screening> crud = new DataAccessObject<>(Screening.class);

        List<Screening> screenings = crud.ReadByField(Constants.Screening.MOVIE_ID, movieId);

        return screenings != null ?
                Response.ok(screenings).build():
                Response.serverError().build();
    }

    /**
     * @param movie object to add to DB
     * @return movie created URL
     */
    @POST
    public Response AddMovie(MovieDetails movie)
    {
        MoviesManager moviesManager = new MoviesManager();

        MovieDetails newMovie = moviesManager.HandleNewMovie(movie);

        return newMovie != null ?
                Response.status(Response.Status.CREATED).entity(newMovie.Id).build() :
                Response.serverError().build();
    }

    /**
     * @param movieId of the movie to delete
     * @return deletion status message
     */
    @DELETE
    @Path("/{movieId}")
    public Response DeleteMovie(@PathParam("movieId") String movieId)
    {
        MoviesManager moviesManager = new MoviesManager();

        return moviesManager.RemoveMovie(movieId) ?
                Response.ok().build():
                Response.serverError().build();
    }
}
