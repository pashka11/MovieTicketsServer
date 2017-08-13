package nimrod.cinema.initDB;

import nimrod.cinema.Managers.ScreeningsManager;
import nimrod.cinema.dao.DataAccessObject;
import nimrod.cinema.dao.SingleMongoClient;
import nimrod.cinema.objects.MovieDetails;
import nimrod.cinema.objects.Row;
import nimrod.cinema.objects.Screening;
import nimrod.cinema.utils.Constants;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Nimrod on 19-Mar-17.
 */
public class Test {
    public static void main(String[] args) {

        final Morphia morphia = new Morphia();

        // tell Morphia where to find your classes
        // can be called multiple times with different packages or classes
        morphia.mapPackage("nimrod.cinema.objects");

        // create the Datastore connecting to the default port on the local host
        final Datastore datastore =
                morphia.createDatastore(SingleMongoClient.getInstance().getClient(),
                                        Constants.DB.TICKET_DATABASE);
        datastore.ensureIndexes();

        //region new movie
        MovieDetails movie = new MovieDetails("The Mask", "Stanley Ipkiss (Jim Carrey) is a bank clerk that is an incredibly nice man. Unfortunately," +
                " he is too nice for his own good and is a pushover when it comes to confrontations. After one of the worst days of his life, he finds a mask that depicts Loki, " +
                "the Norse night god of mischief. Now, when he puts it on, he becomes his inner, self: a cartoon romantic wild man. However, a small time crime boss, Dorian Tyrel (Peter Greene), " +
                "comes across this character dubbed The Mask by the media. After Ipkiss's alter ego indirectly kills his friend in crime," +
                " Tyrel now wants this green-faced goon destroyed.",
                                              "MASK.jpg", "Chuck Russell", "Jim Carrey as Stanley Ipkiss/The Mask,Cameron Diaz as Tina Carlyle,Amy Yasbeck as Peggy Brandt,Joely Fisher as Maggie", new LocalDate(1994, 2, 1), "Action,Comedy,CrimeAction,Family,Fantasy", 88);




        //endregion


        DataAccessObject<Screening> _screeningDao = new DataAccessObject<>(Screening.class);

        // Clearing the db first
        datastore.delete(datastore.createQuery(MovieDetails.class));

        // Saving a new entity and getting the result saved id
        Object id = datastore.save(movie).getId();

        ArrayList<Row> seats = new ArrayList<>();

        for (int i = 0; i < 11; i++)
            seats.add(new Row (new ArrayList<>(Arrays.asList(0, 0, 1, 1, 0, 1, 1, 1, 1))));

        Screening screening = new Screening(id.toString(), LocalDateTime.now(), "1", 1, seats);

        datastore.save(screening);

        Screening screeningSeats = _screeningDao.ReadField(screening.Id, Constants.Screening.SEATS);

        if (screeningSeats == null)
            System.out.println("nullll");

        // Valid and update at the same time
        ArrayList<Row> seats1 = screeningSeats.Seats;

        seats1.get(0).Seats.set(0, ScreeningsManager.SeatState.Chosen.getValue());
        seats1.get(0).Seats.set(1, ScreeningsManager.SeatState.Chosen.getValue());
        seats1.get(0).Seats.set(2, ScreeningsManager.SeatState.Chosen.getValue());

        //_screeningDao.UpdateField
        // Update the screening seats
        if (!_screeningDao.UpdateField(screening.Id,
                                        Constants.Screening.SEATS,
                                       seats1))
            System.out.println("nullll");

        Screening scr = _screeningDao.ReadOne(screening.Id);
    }
}
