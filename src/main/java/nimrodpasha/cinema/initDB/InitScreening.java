package nimrodpasha.cinema.initDB;

import nimrodpasha.cinema.dao.Crud;
import nimrodpasha.cinema.dao.ScreeningsDao;
import nimrodpasha.cinema.objects.Screening;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Arrays;

public class InitScreening
{

    public final static int MAX_SCREENINGS = 10;
    public final static int SCREENINGS_PRICE = 44;
    public final static int SCREENINGID = 1000;
    public final static int MOVIEID = 8;

    public static void main(String[] args)
    {
        //int randomScreening = new Random().nextInt(MAX_SCREENINGS);
        //int randomId = new Random().nextInt(SCREENINGID);
        //int randomM = new Random().nextInt(MOVIEID);

        ArrayList<Screening> screenings = new ArrayList<>();

        ArrayList<ArrayList<Integer>> rows = new ArrayList<>();

        for (int i = 0; i < 11; i++)
            rows.add(new ArrayList<>(Arrays.asList(0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0)));

        for (int movie = 0; movie < 6; movie++)
		{
			for (int i = 0; i < 4; i++)
			{
				Screening scr = new Screening();
				scr.Time = LocalDateTime.now().plusDays(i + 1);
				scr.HallId = i + 1;
				scr.Seats = rows;
				scr.MovieId = movie + 1;

				screenings.add(scr);
			}
		}
        Crud crud = new ScreeningsDao();
        crud.dropAll();
        screenings.parallelStream().forEach(crud::create);
    }
}



