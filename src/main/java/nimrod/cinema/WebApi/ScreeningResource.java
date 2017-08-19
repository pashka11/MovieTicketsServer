package nimrod.cinema.WebApi;

import nimrod.cinema.Managers.ScreeningsManager;
import nimrod.cinema.dao.CRUD;
import nimrod.cinema.dao.DataAccessObject;
import nimrod.cinema.objects.Screening;
import nimrod.cinema.objects.Seat;
import nimrod.cinema.utils.Constants;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nimrod on 14-Mar-17.
 */
@Path("/screenings")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ScreeningResource
{
    /**
     * @return all the screenings
     */
    @GET
    public Response GetAllScreenings()
    {
		DataAccessObject<Screening> dao = new DataAccessObject<>(Screening.class);

        List<Screening> screenings = dao.ReadAll();

        if (screenings != null)
        {
//            List<Screening> screenings =
//                    screeningDocs
//                            .stream()
//                            .map(ViewAndDataObjectConverter::DBDocToScreening)
//                            .collect(Collectors.toList());

            return Response.ok(screenings).build();
        }
        else
            return Response.serverError().build();
    }

	/**
	 * @param screeningsId to get seats for
	 * @return screening seats
	 */
	@GET
	@Path("/{screeningsId}/seats")
	public Response GetScreeningSeats(@PathParam("screeningsId") String screeningsId)
	{
		DataAccessObject<Screening> dao = new DataAccessObject<>(Screening.class);

		//Document seatsDoc = dao.GetScreeningSeats(screeningsId);
		Screening screening = dao.ReadField(screeningsId, Constants.Screening.SEATS);

			if (screening != null)
				if (screening.Seats != null)
					return Response.ok(screening.Seats).build();

		return Response.serverError().build();
	}

	/**
	 * @param screeningsId to save seats for
	 * @return seats selection id
	 */
	@POST
	@Path("/{screeningsId}/seats/save")
	public Response SaveSeatsInScreening(@PathParam("screeningsId") String screeningsId, ArrayList<Seat> selectedSeats)
	{
		ScreeningsManager manager = new ScreeningsManager();

		String seatsSavedId = manager.SaveScreeningSeats(screeningsId, selectedSeats);

		if (seatsSavedId != null)
			return Response.status(Response.Status.CREATED).entity(seatsSavedId).build();

		return Response.serverError().build();
	}

	/**
	 * @param screeningsId to save seats for
	 * @return seats selection id
	 */
	@PUT
	@Path("/{screeningsId}/seats/save")
	public Response ReleaseSaveSeatsInScreening(@PathParam("screeningsId") String screeningsId, String selectionId)
	{
		ScreeningsManager manager = new ScreeningsManager();

		return manager.ReleaseSaveScreeningSeats(screeningsId, selectionId) ?
			Response.ok().build() : Response.serverError().build();
	}



	@POST
	public Response AddScreening(Screening screening)
	{
		ScreeningsManager manager = new ScreeningsManager();
		Screening scr = manager.HandleNewScreening(screening);

		if (scr != null)
			return Response.status(Response.Status.CREATED).entity(scr.Id).build();
		else
			return Response.serverError().build();

	}





	@DELETE
	@Path("/{screeningId}")
	public Response DeleteScreening(@PathParam("screeningId") String screeningid) {
		CRUD<Screening> crud = new DataAccessObject<>(Screening.class);

		if (crud.DeleteOne(screeningid) != null)
			return Response.ok().build();
		else
			return Response.serverError().build();
	}





    /**
     * @param showId is a json format object to insert
     * @return insertion status message
     */
//    @POST
//    public Response insertShow(String showId){
//        Crud crud = new ShowDao();
//        ResponseDocument responseDocument = new Helpers();
//        //turn string into document
//        Document document = Document.parse(showId);
//        String s = crud.insertValidation(document);
//        //        there is a problem ,so we return info
//        if (!s.equals(Crud.status.OK.toString())) {
//            return Response.status(Response.Status.BAD_REQUEST).entity(JSON.serialize(responseDocument.docResponse(s))).build();
//        }
//        //        insertion went ok ,return OK status
//        return Response.status(Response.Status.OK).entity(JSON.serialize(responseDocument.docResponse(s))).build();
//
//        return Response.ok().build();
//    }

    /**
     * @param show is a json format object to update
     * @return update status message
     */
    @PUT
    public Response updateShow(String show){
//        Crud crud = new ShowDao();
//        ResponseDocument responseDocument = new Helpers();
//        Document document = Document.parse(show);
//        if (!crud.update(document)){
//            return Response.status(Response.Status.CONFLICT).entity(JSON.serialize(responseDocument.docResponse(Parameters.ERROR_IN_UPDATE_PROCESS))).build();
//        }
//        return Response.status(Response.Status.ACCEPTED).entity(JSON.serialize(responseDocument.docResponse(Parameters.SUCCESSFULLY_UPDATED))).build();

        return Response.ok().build();
    }

    /**
     * @param showId is a json format object to delete
     * @return deletion status message
     */
//    @DELETE
//    @Path("/{showId}")
//    public Response deleteShow(@PathParam("showId")String showId){
//        Crud crud = new ShowDao();
//        UsageCheck usageCheck = new ShowDao();
//        ResponseDocument responseDocument = new Helpers();
//        if (crud.read(showId)==null){
//            return  Response.status(Response.Status.NOT_FOUND).entity(JSON.serialize(responseDocument.docResponse(Parameters.DOES_NOT_EXIST))).build();
//        }
//        if (usageCheck.isInUse(showId)){
//            return Response.status(Response.Status.FORBIDDEN).entity(JSON.serialize(responseDocument.docResponse(Parameters.RESOURCE_IS_IN_USE))).build();
//        }
//        if (!crud.delete(showId)){
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(JSON.serialize(responseDocument.docResponse(Parameters.ERROR_IN_DELETION))).build();
//        }
//        return Response.status(Response.Status.OK).entity(JSON.serialize(responseDocument.docResponse(Parameters.RESOURCE_HAS_BEEN_DELETED))).build();
//
//        return Response.ok().build();
//    }
}
