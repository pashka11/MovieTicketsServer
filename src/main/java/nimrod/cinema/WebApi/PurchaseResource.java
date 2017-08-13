package nimrod.cinema.WebApi;

import nimrod.cinema.Managers.PurchasesManager;
import nimrod.cinema.objects.PurchaseDetails;
import nimrod.cinema.objects.PurchaseRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Nimrod on 14-Jun-17
 */


@Path("/purchases")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PurchaseResource
{
    /**
     * @return all the movies
    .*/
    @GET
	@Path("/{purchaseId}")
    public Response GetPurchaseDetails(@PathParam("purchaseId") String purchaseId)
    {
		PurchasesManager purchaseManager = new PurchasesManager();

		PurchaseDetails purchaseDetails = purchaseManager.GetPurchaseDetails(purchaseId);

		return purchaseDetails == null ?
				Response.serverError().build() :
				Response.ok(purchaseDetails).build();
    }

    /**
     * @param id of purchase to delete
     * @return deletion status message
     */
    @DELETE
    @Path("/{purchaseId}")
    public Response deletePurchase(@PathParam("purchaseId") String id) {
        PurchasesManager mngr = new PurchasesManager();

        mngr.RemovePurchase(id);
//
//        if (crud.read(bandId) == null)
//            return Response.status(Response.Status.NOT_FOUND).entity(responseDocument.docResponse(DOES_NOT_EXIST)).build();
//        else if (usageCheck.isInUse(bandId))
//            return Response.status(Response.Status.FORBIDDEN).entity(responseDocument.docResponse(RESOURCE_IS_IN_USE)).build();
//        else if (!crud.delete(bandId))
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseDocument.docResponse(ERROR_IN_DELETION)).build();
//
//        return Response.ok(responseDocument.docResponse(RESOURCE_HAS_BEEN_DELETED)).build();

        return Response.ok().build();
    }

    @POST
	public Response AddPurchase(PurchaseRequest request)
	{
		PurchasesManager mngr = new PurchasesManager();

		PurchaseDetails purchase = mngr.HandleNewPurchase(request);

		if (purchase != null)
			return Response.status(Response.Status.CREATED).entity(purchase.Id).build();
		else
			return Response.serverError().build();
	}
}


