package nimrodpasha.cinema.initDB;

/**
 * Created by Yuval on 26-Mar-17.
 * this class is for initialize the whole DB
 */
public class InitDb {
    public static void main(String[] args) {
        String [] arguments = new String[]{""};
        new InitBand();
        InitBand.main(arguments);
        new InitTheater();
        InitTheater.main(arguments);
        new InitUser();
        InitUser.main(arguments);
        new InitShow();
        InitShow.main(arguments);
    }
}
