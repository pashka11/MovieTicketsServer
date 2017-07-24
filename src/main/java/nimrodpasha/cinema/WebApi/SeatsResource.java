package nimrodpasha.cinema.WebApi;

import com.mongodb.util.JSON;
import nimrodpasha.cinema.dao.Crud;
import nimrodpasha.cinema.dao.ScreeningsDao;
import nimrodpasha.cinema.dao.ShowInstanceDao;
import nimrodpasha.cinema.utils.Parameters;
import org.bson.Document;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import nimrodpasha.cinema.objects.Seat;
import static nimrodpasha.cinema.utils.Parameters.*;
/**
 * Created by Yuval on 18-Mar-17.
 * This class handles the seat related HTTP requests
 */
@Path("/seats")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SeatsResource {

//    @GET
//    @Path("/{showInstanceId}")
//    public String getMovieInstanceSeats(@PathParam("showInstanceId") String showInstanceId) {
//
//        return ("row 1: " +" 1 "+" 2 "+" 3 "+" 4 " +"\n" + "row 2: "+ " 1 " + " # " + " # " + " 4 " + "\n");
//
//    }



    /**
     * @param movieInstanceId to return
     * @return requested show instance seats
     */
    @GET
    @Path("/{movieInstanceId}")
    public Response getShowInstanceSeats(@PathParam("movieInstanceId") String movieInstanceId) {
        Crud crud =new ScreeningsDao();
        Document document = crud.read(movieInstanceId);
        if (document!=null && document.get(Parameters.MOVIE_INSTANCE_SEATS)!=null){
            return Response.status(Response.Status.OK).entity(JSON.serialize(document.get(Parameters.MOVIE_INSTANCE_SEATS))).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(Parameters.INVALID_MOVIE_SCREENING_ID).build();
    }



//    /**
//     * @param showInstanceId to return
//     * @return requested show instance seats
//     */
//    @GET
//    @Path("/{showInstanceId}")
//    public Response getShowInstanceSeats(@PathParam("showInstanceId") String showInstanceId) {
//        Crud crud =new ShowInstanceDao();
//        Document document = crud.read(showInstanceId);
//        if (document!=null && document.get(Parameters.SHOW_INSTANCE_SEATS)!=null){
//            return Response.status(Response.Status.OK).entity(JSON.serialize(document.get(Parameters.SHOW_INSTANCE_SEATS))).build();
//        }
//        return Response.status(Response.Status.BAD_REQUEST).entity(Parameters.INVALID_SHOW_INSTANCE_ID).build();
//    }
}
