package nimrodpasha.cinema.utils;

public class Constants {

	public class Movie
	{
		public final static String ID = "id";
		public final static String DIRECTOR = "director";
		public final static String DURATION = "duration";
		public final static String GENRES  = "genres";
		public final static String DESCRIPTION = "description";
		public final static String RELEASEDATE = "releasedate";
		public final static String ACTORS  = "actors";
		public final static String NAME = "name";
		public static final String IMAGE_NAME = "imagename";
	}


	public class Halls {
		public final static String HALL_ID = "hallid";
		public static final String INVALID_HALL_ID = "invalid hall id";
		public final static String HALLS_COLLECTION = "halls";
		public final static String HALLS_COLUMNS = "columns";
		public final static String HALLS_ROWS = "rows";

	}


	public class Screening {
		public final static String SCREENINGS_TIME = "screening time";
		public final static String HALL_ID = "hall id";
		public final static String SEATS = "seats";
		public final static String PRICE = "price";
		public final static String SCREENINGSID = "screeningId";
		public final static String MOVIEID = "movieId";


		public static final String INVALID_MOVIE_DETAILS_ID = "invalid MovieDetails id";
		public final static String SCREENINGS = "screenings";


	}

	public class DB
	{
		public final static String TICKET_DATABASE = "tickets";
		public static final String HALL_COLLECTION = "halls";
		public final static String MOVIE_COLLECTION = "movies";
		public final static String SCREENINGS_COLLECTION = "screenings";
	}



	public static final String INVALID_MOVIEDETAILS_ID = "invalid MovieDetails id";

	public final static String MOVIE_SCREENINGS = "screenings";

	public final static String MOVIE_INSTANCE_DATE = "date";
	public final static String MOVIE_INSTANCE_PRICE = "price";
	public final static String MOVIE_INSTANCE_THEATER_ID = "theater_id";
	public final static String MOVIE_INSTANCE_SEATS = "seats";


	public final static String SCREENINGS = "_screenings";
	public static final String INVALID_MOVIE_SCREENING_ID = "invalid movie screening id";
	public static final String INVALID_MOVIE_ID = "invalid movie id";





	public final static String BAND_NAME = "name";
	public final static String BAND_DESCRIPTION = "description";
	public final static String THEATER_NAME = "name";
	public final static String THEATER_COLUMNS = "columns";
	public final static String THEATER_ROWS = "rows";
	public final static String THEATER_LOCATION = "location";
	public final static String ID = "_id";
	public final static String SHOW_NAME = "name";
	public final static String SHOW_DESCRIPTION = "description";
	public final static String SHOW_BAND_ID = "band_id";
	public final static String SHOW_INSTANCE = "instances";
	public final static String SHOW_INSTANCE_DATE = "date";
	public final static String SHOW_INSTANCE_PRICE = "price";
	public final static String SHOW_INSTANCE_THEATER_ID = "theater_id";
	public final static String SHOW_INSTANCE_SEATS = "seats";
	public static final String USERS_COLLECTION = "users";
	public static final String USER_PASSWORD = "password";
	public static final String USER_TICKETS = "tickets";
	public static final String USER_IS_ADMIN = "is_admin";
	public static final String USER_SHOW_INSTANCE_ID = "instance";
	public static final String USER_TICKETS_FOR_INSTANCE = "user_tickets";
	public static final String USER_TICKETS_SHOW_ID= "show_id";
	public static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	public static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
	public static final String SECURED_URL_LOGIN = "authenticate";
	public static final String SECURED_URL_PURCHASE = "purchase";
	public static final String USER_IS_AUTHENTICATED = "user is authenticated successfully";
	public static final String ADMIN_IS_AUTHENTICATED = "admin is authenticated successfully";
	public static final String AUTHENTICATION_FAILED = "authentication failed";
	public static final String USER_DOES_NOT_EXIST = "user does not exist";
	public static final String SHOW_DOES_NOT_EXIST = "show does not exist";
	public static final String SHOW_INSTANCE_DOES_NOT_EXIST = "show instance does not exist";
	public static final String THEATER_DOES_NOT_EXIST = "theater does not exist";
	public static final String THEATER_OUT_OF_BOUNDS = "out of bounds of the theater";
	public static final String PASSWORD_INCORRECT = "incorrect password";
	public static final String ROW_NUMBER = "row";
	public static final String COLUMN_NUMBER = "column";
	public static final String ROW_SEATS = "row_seats";
	public static final String SEAT_WAS_RESERVED = "seat was reserved";
	public static final String SEAT_RESERVASION_NOT_ACCEPTED = "seat reservation not accepted";
	public static final String SEAT_WAS_RELEASED = "seat was released";
	public static final String SEAT_WAS_PURCHASED = "seat was purchased";
	public static final String SEAT_WAS_NOT_PURCHASED = "seat was not purchased";
	public static final String IMAGE_FOLDER = "/images/";
	public static final String IMAGE_LINK = "image";
	public static final String DOES_NOT_EXIST = "WebApi does not exists";
	public static final String RESOURCE_IS_IN_USE = "WebApi is in use";
	public static final String RESOURCE_HAS_BEEN_DELETED = "WebApi has been deleted";
	public static final String ERROR_IN_DELETION = "error in deletion";
	public static final String ERROR_IN_UPDATE_PROCESS = "error in update process";
	public static final String SUCCESSFULLY_UPDATED = "successfully updated";
	public static final String INVALID_USER_ID = "invalid user id";
	public static final String INVALID_SHOW_ID = "invalid show id";
	public static final String INVALID_BAND_ID = "invalid band id";
	public static final String INVALID_SHOW_INSTANCE_ID = "invalid show instance id";
	public static final String INVALID_THEATER_ID = "invalid theater id";
	public static final String INVALID_ROW = "invalid row";
	public static final String INVALID_COLUMN = "invalid column";
	public static final String RESPONSE = "response";
	public static final int SEAT_IS_FREE = 0;
	public static final int SEAT_IS_TAKEN = 1;
	public static final int SEAT_IS_RESERVED = 2;
	public static final int PURCHASE_TIMER_TIME_TO_SLEEP = 1000 * 60 * 3;


}
