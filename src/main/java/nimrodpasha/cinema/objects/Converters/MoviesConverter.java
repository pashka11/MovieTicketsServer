package nimrodpasha.cinema.objects.Converters;

import nimrodpasha.cinema.objects.MovieDetails;
import nimrodpasha.cinema.utils.Constants;
import org.bson.Document;
import org.joda.time.LocalDate;

public class MoviesConverter
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


//	public LocalDateTime ScreeningTime;
//	public int HallId;
//	public ArrayList<Row> Seats;
//	public int Price;

//	public final static Screening DBDocToScreeningsDetails(Document doc)
//	{
//		Screening screenings = new Screening();
//
//
//		screenings.HallId=doc.getInteger(Constants.Halls.HALL_ID);
//		screenings.ScreeningTime=LocalDateTime.parse(doc.getString(Constants.Screening.SCREENINGS_TIME));
//		screenings.Seats=doc.getArray(Constants.Screening.SEATS);
//		screenings.Price=doc.getInteger(Constants.Screening.PRICE);
//
//
//
//		return screenings;
//	}
}

//		Document doc = new Document(, movie.Id)
//				.append(, movie.Name)
//				.append(BAND_DESCRIPTION, movie.Description)
//				.append(IMAGE_LINK, movie.ImageName)
//				.append(DIRECTOR, movie.Director)
//				.append(DURATION, movie.Duration)
//				.append(GENRES, movie.Genres)
//				.append(RELEASEDATE, movie.ReleaseDate)
//				.append(ACTORS, movie.Actors)
//				.append(Parameters.MOVIE_SCREENINGS, Arrays.asList());

