package nimrod.cinema.initDB;

import org.bson.types.ObjectId;

import java.util.ArrayList;

/**
 * Created by Nimrod on 26-Jul-17.
 */
public class InitializeDB {
    public static void main(String[] args) {

        ArrayList<String> movieIds = new ArrayList<>();

        for (int i = 0; i < 10; i++)
        	movieIds.add(ObjectId.get().toString());

        InitMovies.AddMoviesToDB(movieIds);
        ArrayList<String> hallIds = InitHall.AddHallsToDB();
		ArrayList<String> screeningIds = InitScreening.AddScreeningsToDB(movieIds, hallIds);
		//ArrayList<String> purchasesIds = InitPurchases.AddPurchasesToDB(screeningIds,movieIds);
    }


}
