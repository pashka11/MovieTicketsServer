package nimrodpasha.cinema.initDB;

/**
 * Created by Yuval on 26-Mar-17.
 * this class is for initialize the whole DB
 */
public class InitDb {
    public static void main(String[] args) {
        String [] arguments = new String[]{""};
        new InitMovies();
        InitMovies.main(arguments);
        new InitScreening();
        InitScreening.main(arguments);
        new InitHall();
        InitHall.main(arguments);

    }
}
