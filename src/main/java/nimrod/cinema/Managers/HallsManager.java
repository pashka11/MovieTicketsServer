package nimrod.cinema.Managers;

import nimrod.cinema.dao.DataAccessObject;
import nimrod.cinema.objects.Hall;
import nimrod.cinema.objects.MovieDetails;
import nimrod.cinema.objects.Screening;
import nimrod.cinema.utils.Constants;

public class HallsManager {

	private DataAccessObject<Screening> _screeningsDao;
	private DataAccessObject<MovieDetails> _movieDao;
	private DataAccessObject<Hall> _hallDao;

	public HallsManager()
	{
		_screeningsDao = new DataAccessObject<>(Screening.class);
		_movieDao = new DataAccessObject<>(MovieDetails.class);
		_hallDao = new DataAccessObject<>(Hall.class);
	}

	public boolean RemoveHall(String Id)
	{
		return ((_hallDao.DeleteOne(Id) != null) &&
				_screeningsDao.DeleteByField(Constants.Screening.HALL_ID,Id));
	}

}