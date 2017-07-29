package nimrodpasha.cinema.objects.Converters;

import nimrodpasha.cinema.objects.MovieDetails;
import nimrodpasha.cinema.objects.Screening;
import nimrodpasha.cinema.utils.Constants;
import org.bson.Document;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.ISODateTimeFormat;

import java.util.ArrayList;

public class ViewAndDataObjectConverter
{
	public final static MovieDetails DBDocToMovieDetails(Document doc)
	{
		MovieDetails movie = new MovieDetails();

		movie.Id = doc.getInteger(Constants.Movie.ID);
		movie.Description = doc.getString(Constants.Movie.DESCRIPTION);
		movie.Duration = doc.getInteger(Constants.Movie.DURATION);
		movie.Name = doc.getString(Constants.Movie.NAME);
		movie.Genres = doc.getString(Constants.Movie.GENRES);
		movie.ReleaseDate = LocalDate.parse(doc.getString(Constants.Movie.RELEASEDATE));
		movie.Director = doc.getString(Constants.Movie.DIRECTOR);
		movie.Actors = doc.getString(Constants.Movie.ACTORS);
		movie.ImageName = doc.getString(Constants.Movie.IMAGE_NAME);

		return movie;
	}

	public final static Screening DBDocToScreening(Document doc)
	{
		Screening screenings = new Screening();

		screenings.HallId = doc.getInteger(Constants.Halls.HALL_ID);
		screenings.Time = ISODateTimeFormat.dateHourMinute().parseLocalDateTime(doc.getString(Constants.Screening.SCREENINGS_TIME));
		screenings.Seats = (ArrayList<ArrayList<Integer>>) doc.get(Constants.Screening.SEATS);
		screenings.Price = doc.getInteger(Constants.Screening.PRICE);
		screenings.MovieId = doc.getInteger(Constants.Screening.MOVIE_ID);
		screenings.Id = doc.getInteger(Constants.Screening.ID);

		return screenings;
	}
}


