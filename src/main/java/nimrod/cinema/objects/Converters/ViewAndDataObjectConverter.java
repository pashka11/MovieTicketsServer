package nimrod.cinema.objects.Converters;

import nimrod.cinema.objects.PurchaseDetails;
import nimrod.cinema.objects.PurchaseRequest;

public class ViewAndDataObjectConverter
{
//	public static MovieDetails DBDocToMovieDetails(Document doc)
//	{
//		MovieDetails movie = new MovieDetails();
//
//		try
//		{
//			movie.Id = doc.getString(Constants.Movie.ID);
//			movie.Description = doc.getString(Constants.Movie.DESCRIPTION);
//			movie.Duration = doc.getInteger(Constants.Movie.DURATION);
//			movie.Name = doc.getString(Constants.Movie.NAME);
//			movie.Genres = doc.getString(Constants.Movie.GENRES);
//			movie.ReleaseDate = LocalDate.parse(doc.getString(Constants.Movie.RELEASEDATE));
//			movie.Director = doc.getString(Constants.Movie.DIRECTOR);
//			movie.Actors = doc.getString(Constants.Movie.ACTORS);
//			movie.ImageName = doc.getString(Constants.Movie.IMAGE_NAME);
//
//			return movie;
//		}
//		catch (Exception e)
//		{
//			return null;
//		}
//
//	}
//
//	public static Screening DBDocToScreening(Document doc)
//	{
//		try
//		{
//			Screening screenings = new Screening();
//
//			screenings.HallId = doc.getInteger(Constants.Screening.HALL_ID);
//			screenings.Time = ISODateTimeFormat.dateHourMinute().parseLocalDateTime(doc.getString(Constants.Screening.SCREENINGS_TIME));
////			screenings.Seats = (ArrayList<ArrayList<Integer>>) doc.get(Constants.Screening.SEATS);
//			screenings.Price = doc.getInteger(Constants.Screening.PRICE);
//			screenings.MovieId = doc.getString(Constants.Screening.MOVIE_ID);
//			screenings.Id = doc.getString(Constants.Screening.ID);
//
//			return screenings;
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//			return null;
//		}
//	}
//
//	public static ArrayList<ArrayList<Integer>> DBDocToSeats(Document seatsDoc)
//	{
//		return (ArrayList<ArrayList<Integer>>) seatsDoc.get(Constants.Screening.SEATS);
//	}

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
//
//	public static SeatsSelection DBDocToSeatsSelection(Document seatsDoc)
//	{
//		Object obj = seatsDoc.get(Constants.SeatsSelection.SEATS);
//
//		return null;
//	}
//
//	public static PurchaseDetails DBDocToPurchaseDetails(Document doc)
//	{
//		try
//		{
//			PurchaseDetails purchaseDetails = new PurchaseDetails();
//
//			purchaseDetails.PurchaseTime = ISODateTimeFormat.dateTimeNoMillis().parseLocalDateTime(doc.getString(Constants.Purchase.PURCHASE_TIME));
//			purchaseDetails.MovieId = doc.getString(Constants.Purchase.MOVIE_ID);
//					//.append(Constants.Purchase.PRICE, purchase.)
//			//purchaseDetails.Seats = doc.getString(Constants.Purchase.SEATS);
//			purchaseDetails.GivenName = doc.getString(Constants.Purchase.GIVEN_NAME);
//			purchaseDetails.LastName = doc.getString(Constants.Purchase.LAST_NAME);
//			purchaseDetails.Email = doc.getString(Constants.Purchase.EMAIL);
//			purchaseDetails.PhoneNumber = doc.getString(Constants.Purchase.PHONE);
//			purchaseDetails.ScreeningId = doc.getString(Constants.Purchase.SCREENING_ID);
//
//			return purchaseDetails;
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//			return null;
//		}
//	}
////
////	public static Document SeatsToDBDoc(ArrayList<ArrayList<Integer>> seats)
////	{
////		Document doc = new Document()
////	}
}


