package nimrod.cinema.initDB;

import nimrod.cinema.dao.CRUD;
import nimrod.cinema.dao.DataAccessObject;
import nimrod.cinema.objects.Hall;

import java.util.ArrayList;
import java.util.Random;

public class InitHall
{
    public static ArrayList<String> AddHallsToDB()
    {
        ArrayList<Hall> halls = new ArrayList<>();

        Random rnd = new Random();

        ArrayList<String> hallIds = new ArrayList<>();

        for (int i = 1; i <= 10; i++)
        {
            hallIds.add(String.valueOf(i));
            halls.add(new Hall(String.valueOf(i),
                               rnd.nextInt(5) + 7,
                               rnd.nextInt(5) + 7));
        }

		CRUD<Hall> crud = new DataAccessObject<>(Hall.class);
		crud.DeleteAll();
		crud.CreateMany(halls);

        return hallIds;
    }
}
