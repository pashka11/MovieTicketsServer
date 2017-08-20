package nimrod.cinema.objects.Converters;

import nimrod.cinema.objects.PurchaseDetails;
import nimrod.cinema.objects.PurchaseRequest;

public class ViewAndDataObjectConverter
{
	public static PurchaseDetails PurchaseRequestToPurchaseDetails(PurchaseRequest request)
	{
		try
		{
			PurchaseDetails purchase = new PurchaseDetails();

			purchase.Email = request.Email;
			purchase.GivenName = request.GivenName;
			purchase.LastName = request.LastName;
			purchase.PhoneNumber = request.PhoneNumber;
			purchase.ScreeningId = request.ScreeningId;
			purchase.TotalPrice = request.TotalPrice;

			return purchase;
		}
		catch (Exception e)
		{
			e.printStackTrace();

			return null;
		}
	}
}


