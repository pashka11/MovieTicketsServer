package nimrod.cinema.Managers;

import nimrod.cinema.dao.DataAccessObject;
import nimrod.cinema.objects.MovieDetails;
import nimrod.cinema.objects.Screening;
import nimrod.cinema.utils.Constants;
import org.bson.types.ObjectId;

public class MoviesManager
{
	private DataAccessObject<Screening> _screeningsDao;
	private DataAccessObject<MovieDetails> _movieDao;

	public MoviesManager()
	{
		_screeningsDao = new DataAccessObject<>(Screening.class);
		_movieDao = new DataAccessObject<>(MovieDetails.class);

	}

	public MovieDetails HandleNewMovie(MovieDetails movie)
	{
		// If we have no id, allocate a new one
		if ( movie.Id == null || movie.Id.isEmpty())
			movie.Id = ObjectId.get().toString();

		String movieId = _movieDao.CreateOne(movie);
		if (movieId == null)
			return null;

		return movie;
	}

	public boolean RemoveMovie(String  Id)
	{
		return ( _screeningsDao.DeleteByField(Constants.Screening.MOVIE_ID,Id) &&
				(_movieDao.DeleteOne(Id) != null));
	}
}