
package nimrod.cinema.Managers;

import nimrod.cinema.Services.SeatsSelectionTimingService;
import nimrod.cinema.dao.DataAccessObject;
import nimrod.cinema.objects.Converters.ViewAndDataObjectConverter;
import nimrod.cinema.objects.*;
import nimrod.cinema.utils.EmailService;
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


		MovieDetails movie = _movieDao.ReadOne(screening.MovieId);

		EmailService.GetInstance().SendMail(purchase,movie,screening);

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