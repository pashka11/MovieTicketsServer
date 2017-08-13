
package nimrod.cinema.Managers;

import nimrod.cinema.Services.SeatsSelectionTimingService;
import nimrod.cinema.dao.DataAccessObject;
import nimrod.cinema.objects.Converters.ViewAndDataObjectConverter;
import nimrod.cinema.objects.*;
import org.joda.time.LocalDateTime;


public class PurchasesManager
{
	private DataAccessObject<MovieDetails> _movieDao;
	private DataAccessObject<Screening> _screeningDao;
	private DataAccessObject<SeatsSelection> _seatsSelectionDao;
	private DataAccessObject<PurchaseDetails> _purchaseDao;

	public PurchasesManager()
	{
		_movieDao = new DataAccessObject<>(MovieDetails.class);
		_screeningDao = new DataAccessObject<>(Screening.class);
		_seatsSelectionDao = new DataAccessObject<>(SeatsSelection.class);
		_purchaseDao = new DataAccessObject<>(PurchaseDetails.class);
	}

	public PurchaseDetails HandleNewPurchase(PurchaseRequest request)
	{
		// Get screening
		Screening screening = _screeningDao.ReadOne(request.ScreeningId);

		if (screening == null)
			return null;

		// find and delete the selection from out DB if it exists (we dont need the seats themselves as the seats are already
		SeatsSelection seatsSelection = _seatsSelectionDao.DeleteOne(request.SeatsSelectionId);

		// Check that the selection exists
		if (seatsSelection == null)
			return null;

		if (seatsSelection.SelectionTime.plusMinutes(15).isBefore(LocalDateTime.now()))
		{
			// TODO: free the seats
			return null;
		}

		PurchaseDetails purchase = ViewAndDataObjectConverter.PurchaseRequestToPurchaseDetails(request);

		if (purchase == null)
			return null;

		purchase.PurchaseTime = LocalDateTime.now();
		purchase.MovieId = screening.MovieId;
		purchase.Seats = seatsSelection.Seats;

		if (!SeatsSelectionTimingService.StopTimerSelection(request.SeatsSelectionId))
			return null;

		String resultId = _purchaseDao.CreateOne(purchase);

		if (resultId == null || resultId.isEmpty())
			return null;

		return purchase;
	}

	public PurchaseDetails GetPurchaseDetails(String purchaseId)
	{
		return _purchaseDao.ReadOne(purchaseId);
	}

	public boolean RemovePurchase(String id)
	{
		return _purchaseDao.DeleteOne(id) != null;
	}
}

//package nimrod.cinema.Managers;
//
//import nimrod.cinema.dao.MovieDao;
//import nimrod.cinema.dao.PurchaseDao;
//import nimrod.cinema.dao.ScreeningsDao;
//import nimrod.cinema.dao.SeatsSelectionDao;
//import nimrod.cinema.objects.Converters.ViewAndDataObjectConverter;
//import nimrod.cinema.objects.PurchaseDetails;
//import nimrod.cinema.objects.PurchaseRequest;
//import nimrod.cinema.objects.Screening;
//import nimrod.cinema.objects.SeatsSelection;
//import org.bson.Document;
//import org.joda.time.LocalDateTime;
//
//import java.util.Objects;
//
//
//public class PurchasesManager
//{
//	private MovieDao _movieDao;
//	private ScreeningsDao _screeningDao;
//	private SeatsSelectionDao _seatsSelectionDao;
//	private PurchaseDao _purchaseDao;
//
//	public PurchasesManager()
//	{
//		_movieDao = new MovieDao();
//		_screeningDao = new ScreeningsDao();
//		_seatsSelectionDao = new SeatsSelectionDao();
//		_purchaseDao = new PurchaseDao();
//	}
//
//	public PurchaseDetails HandleNewPurchase(PurchaseRequest request)
//	{
//		// Get screening
//		Document screeningDoc = _screeningDao.read(request.ScreeningId);
//
//		if (screeningDoc == null)
//			return null;
//
//		Screening screening = ViewAndDataObjectConverter.DBDocToScreening(screeningDoc);
//
//		// find and delete the selection from out DB if it exists (we dont need the seats themselves as the seats are already
//		Document seatsDoc = _seatsSelectionDao.delete(request.SeatsSelectionId);
//
//		// Check that the selection exists
//		if (seatsDoc == null)
//			return null;
//
//		SeatsSelection seatsSelection = ViewAndDataObjectConverter.DBDocToSeatsSelection(seatsDoc);
//
//		if (seatsSelection != null && seatsSelection.SelectionTime.plusMinutes(15).isBefore(LocalDateTime.now()))
//			return null;
//
//		PurchaseDetails purchase = ViewAndDataObjectConverter.PurchaseRequestToPurchaseDetails(request);
//
//		if (purchase == null)
//			return null;
//
//		purchase.PurchaseTime = LocalDateTime.now();
//		purchase.MovieId = screening.MovieId;
//		purchase.Seats = seatsSelection.Seats;
//
//		String resultId = _purchaseDao.create(purchase);
//
//		if (Objects.equals(resultId, ""))
//			return null;
//
//		purchase.Id = resultId;
//		return purchase;
//	}
//
//	public PurchaseDetails GetPurchaseDetails(String purchaseId)
//	{
//		Document doc = _purchaseDao.read(purchaseId);
//
//		if (doc == null)
//			return null;
//
//		return ViewAndDataObjectConverter.DBDocToPurchaseDetails(doc);
//	}
//}
//
