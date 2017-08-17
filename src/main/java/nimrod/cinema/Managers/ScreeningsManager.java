package nimrod.cinema.Managers;

import nimrod.cinema.Services.SeatsSelectionTimeoutHandler;
import nimrod.cinema.Services.SeatsSelectionTimingService;
import nimrod.cinema.dao.DataAccessObject;
import nimrod.cinema.objects.*;
import nimrod.cinema.utils.Constants;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;

public class ScreeningsManager implements SeatsSelectionTimeoutHandler
{
	private DataAccessObject<Screening> _screeningsDao;
	private DataAccessObject<SeatsSelection> _seatSelectionDao;
	private DataAccessObject<MovieDetails> _movieDao;
	private DataAccessObject<Hall> _hallDao;

	public ScreeningsManager()
	{
		_screeningsDao = new DataAccessObject<>(Screening.class);
		_movieDao = new DataAccessObject<>(MovieDetails.class);
		_seatSelectionDao = new DataAccessObject<>(SeatsSelection.class);
		_hallDao = new DataAccessObject<>(Hall.class);
	}


	public synchronized String SaveScreeningSeats(String screeningId, ArrayList<Seat> selectedSeats)
	{
		// Getting the screening seats to validate seats and update them
		Screening screeningSeats = _screeningsDao.ReadField(screeningId, Constants.Screening.SEATS);

		if (screeningSeats == null)
			return null;

		// Valid and update at the same time
		ArrayList<Row> seats = screeningSeats.Seats;

		// Checking if any of the seats are already occupied and marking them as occupied if not
		for (Seat seat : selectedSeats)
			if (seats.get(seat.RowNumber).Seats.get(seat.SeatNumber) == SeatState.Occupied.getValue())
				return null;
			else
				seats.get(seat.RowNumber).Seats.set(seat.SeatNumber, SeatState.Occupied.getValue());

		SeatsSelection selection = new SeatsSelection(screeningId, selectedSeats, LocalDateTime.now());

		String selectionId = _seatSelectionDao.CreateOne(selection);

		if (selectionId == null)
			return null;

		// Update the screening seats
		if (!_screeningsDao.UpdateField(screeningId,
										Constants.Screening.SEATS,
										seats))
		{
			_seatSelectionDao.DeleteOne(selectionId);

			return null;
		}

		// TODO : Add 15 min timer to the selectionId with callback, then set the seats back to free!!!
		SeatsSelectionTimingService.AddSelection(selectionId, this);

		return selectionId;
	}

	@Override
	public synchronized boolean SeatsSelectionTimedOut(String selectionId)
	{
		SeatsSelection selection = _seatSelectionDao.DeleteOne(selectionId);

		if (selection == null)
			return false;

		Screening screening = _screeningsDao.ReadField(selection.ScreeningId, Constants.Screening.SEATS);

		if (screening == null)
			return false;

		selection.Seats.forEach(seat -> screening.Seats.get(seat.RowNumber).Seats.set(seat.SeatNumber, SeatState.Free.getValue()));

		if (!_screeningsDao.UpdateField(screening.Id, Constants.Screening.SEATS, screening.Seats))
			return false;

		return true;
	}

	public boolean ReleaseSaveScreeningSeats(String screeningsId, String selectionId)
	{
		return SeatsSelectionTimedOut(selectionId);
	}

	public Screening HandleNewScreening(Screening screening) {

		MovieDetails movie = _movieDao.ReadOne(screening.MovieId);
		if (movie == null)
			return null;

		Hall hall = _hallDao.ReadOne(screening.HallId);
		if (hall == null)
			return null;

		ArrayList<Row> seats = new ArrayList<>();

		for (int row = 0 ; row < hall.Row;row ++ ){
			seats.add(new Row(hall.Column));
		}

		screening.Seats = seats;

		String screeningId = _screeningsDao.CreateOne(screening);

		if(screeningId == null )
			return null;

		return screening;

	}


//
//	public synchronized String SaveScreeningSeats(String screeningsId, ArrayList<Seat> selectedSeats)
//	{
//		// Getting the screening seats to validate seats and update them
//		Document screeningSeatsDoc = _screeningsDao.ReadField(screeningsId, Constants.Screening.SEATS);
//
//		if (screeningSeatsDoc == null)
//			return null;
//
//		// Valid and update at the same time
//		ArrayList<ArrayList<Integer>> seats = ViewAndDataObjectConverter.DBDocToSeats(screeningSeatsDoc);
//
//		// Checking if any of the seats are already occupied and marking them as occupied if not
//		for (Seat seat : selectedSeats)
//			if (seats.get(seat.RowNumber).get(seat.SeatNumber) == SeatState.Occupied.getValue())
//				return null;
//			else
//				seats.get(seat.RowNumber).set(seat.SeatNumber, SeatState.Occupied.getValue());
//
//		// Update the screening seats
//		if (!_screeningsDao.UpdateField(screeningsId,
//									   Constants.Screening.SEATS,
//									   new Document(Constants.Screening.SEATS, seats)))
//			return null;
//
//		String selectionId = _seatSelectionDao.create(new SeatsSelection(screeningsId, selectedSeats, LocalDateTime.now()));
//
//		if (selectionId == null)
//			return null;
//
//		return selectionId;
//	}

	// Constants
	public enum SeatState
	{
		Free(0),
		Occupied(1),
		Chosen(2);

		private int value;

		SeatState(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}
}
