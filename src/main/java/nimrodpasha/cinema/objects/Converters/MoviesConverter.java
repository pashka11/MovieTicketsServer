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

		return movie;
	}
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

