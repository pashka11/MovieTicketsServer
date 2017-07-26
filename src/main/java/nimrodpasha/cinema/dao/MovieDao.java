package nimrodpasha.cinema.dao;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import nimrodpasha.cinema.objects.Row;
import nimrodpasha.cinema.utils.Constants;
import nimrodpasha.cinema.utils.Parameters;
import org.bson.Document;
import org.bson.conversions.Bson;
import nimrodpasha.cinema.objects.MovieDetails;
import nimrodpasha.cinema.objects.Screening;
import org.bson.types.ObjectId;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Instant;

import java.time.ZoneOffset;

import java.time.Month;



import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static nimrodpasha.cinema.dao.MongoCollection.getMongoCollection;
import static nimrodpasha.cinema.utils.Parameters.*;

/**
 * data access object - movies collection
 */
public class MovieDao implements Crud, RandomId, UsageCheck {


    private static final int MAX_SCREENINGS_TO_ENTER = 10;
    private static final int MIN_SCREENINGS_TO_ENTER = 2;
    private static final int SCREENINGS_PRICE = 45;
    private static final int MAX_SCREENINGS_DAYS_DIFFERENCE = 25;
    private static final int MIN_SCREENINGS_DAYS_DIFFERENCE = 2;
    private static final int RANDOM_SCREENINGS_HOURS = 24;

    private com.mongodb.client.MongoCollection<Document> _collection;

    private static final DateTimeFormatter DATE_FORMAT = ISODateTimeFormat.date();

    /**
     * constructor
     */
    public MovieDao() {

        this._collection = getMongoCollection(Parameters.MOVIE_COLLECTION);
    }

    /**
     * this method creates an object and insert it to the DB
     *
     * @param obj is the document to insert
     * @return true if created false otherwise
     */
    @Override
    public boolean create(Object obj) {
        try {
            MovieDetails movie = (MovieDetails) obj;
            Document doc = new Document(Constants.Movie.ID, movie.Id)
                    .append(Constants.Movie.NAME, movie.Name)
                    .append(Constants.Movie.DESCRIPTION, movie.Description)
                    .append(Constants.Movie.IMAGE_NAME, movie.ImageName)
                    .append(Constants.Movie.DIRECTOR, movie.Director)
                    .append(Constants.Movie.DURATION, movie.Duration)
                    .append(Constants.Movie.GENRES, movie.Genres)
                    .append(Constants.Movie.RELEASEDATE, DATE_FORMAT.print(movie.ReleaseDate))
                    .append(Constants.Movie.ACTORS, movie.Actors);
                    //.append(Parameters.MOVIE_SCREENINGS, Arrays.asList());

            _collection.insertOne(doc);


            ArrayList<Screening> screeningsInstances = new ArrayList<>();
            LocalDateTime screeningTime = LocalDateTime.now(); //TODO add random time
            int price=(SCREENINGS_PRICE );
            int r = new Random().nextInt(MAX_SCREENINGS_TO_ENTER - MIN_SCREENINGS_TO_ENTER) + 1;


            for (int i = 0; i < r; i++) {


                //set random theater
                RandomId randomId = new HallsDao();
                Document hall = new HallsDao().read(String.valueOf(randomId.randomId()));
                int hallId =((int)(hall.get(Constants.Halls.HALL_ID)));
                ArrayList <Row> rows = new ArrayList<>();
                for (int j = 1; j <=(int)hall.get(Constants.Halls.HALLS_ROWS) ; j++) {
                    int arr []=new int[(int)hall.get(Constants.Halls.HALLS_COLUMNS)];
                    rows.add(new Row(j,arr));
                    for (int l = 0; l < arr.length; l++) {
                        arr[l]=0;
                    }
                }
                Screening screeningInstance = new Screening(screeningTime,hallId,price,rows);


                screeningsInstances.add(screeningInstance);

            }





//            for (int i = 0; i < movie.getScreenings().size(); i++) {//TODO move it to showInstanceDao
//
//                Document seatDocument = new Document();
//                for (Row row : movie.getScreenings().get(i).getSeats()) {
//                    seatDocument
//                            .append(Parameters.ROW_NUMBER + " " + row.getRowNumber(), Arrays.asList(row.getSeats()));
//                }
//                Document instance = new Document()
//                        .append(Parameters.ID, new ObjectId())
//                        .append(Parameters.MOVIE_INSTANCE_DATE, movie.getScreenings().get(i).getDate())
//                        .append(Parameters.MOVIE_INSTANCE_PRICE, movie.getScreenings().get(i).getPrice())
//                        .append(Parameters.MOVIE_INSTANCE_THEATER_ID, movie.getScreenings().get(i).getTheaterId())
//                        .append(Parameters.MOVIE_INSTANCE_SEATS, Arrays.asList(seatDocument));
//                _collection.updateOne(eq(Parameters.ID, movie.getId()), Updates.addToSet(Parameters.MOVIE_SCREENINGS, instance));
//
//            }



        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * @return random id of one of the documents in this collection
     */
    @Override
    public int randomId() {
        IdInterface idInterface = new DaoUtils();
        return idInterface.randomId(_collection);
    }
    /**
     * get a single document from the DB
     * @param id of the DB object
     * @return single document
     */
    @Override
    public Document read(String id) {
        com.mongodb.client.MongoCollection<Document> coll = nimrodpasha.cinema.dao.MongoCollection.getMongoCollection(Parameters.MOVIE_COLLECTION);
        Document document = coll.find(eq(Parameters.ID, Integer.valueOf(id))).first();
        return document;
    }
    /**
     * @return all documents in this collection in a arrayList
     */
    @Override
    public List<Document> readAll() {
        return _collection.find().into(new ArrayList<Document>());
    }
    /**
     * @param document contain fields to update
     * @return true if update was successful ,false otherwise
     */
    @Override
    public boolean update(Document document) {
        boolean updated = false;
        try {
            CheckAndSetInterface checkAndSetInterface = new DaoUtils();
            //check if id exists
            if (read(document.get(Parameters.ID).toString()) == null) {
                return false;
            }
            if (checkAndSetInterface.checkAndSet(_collection, Parameters.SHOW_DESCRIPTION, document)) {
                updated = true;
            }
            if (checkAndSetInterface.checkAndSet(_collection, Parameters.SHOW_NAME, document)) {
                updated = true;
            }
            if (checkAndSetInterface.checkAndSet(_collection, Parameters.IMAGE_LINK, document)) {
                updated = true;
            }
            //if band parameter was inserted check if valid and update
            if (document.get(Parameters.SHOW_BAND_ID) != null && new BandDao().read(document.get(Parameters.SHOW_BAND_ID).toString()) != null) {
                if (checkAndSetInterface.checkAndSet(_collection, Parameters.SHOW_BAND_ID, document)) {
                    updated = true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return updated;
    }
    /**
     * @param id of an object to delete
     * @return true if deletion was successful ,false otherwise
     */
    @Override
    public boolean drop(String id) {
        Bson filter = Filters.eq(Parameters.ID, Integer.valueOf(id));
        try {
            if (read(id) == null) {
                return false;
            }
            _collection.deleteOne(filter);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /**
     * delete all documents in the current collection
     * @return true if deletion was successful ,false otherwise
     */
    @Override
    public boolean dropAll() {
        try {
            _collection.drop();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /**
     * @param document to insert
     * @return status
     */
    @Override
    public String insertValidation(Document document) {
        //put auto increment id
        IdInterface idInterface = new DaoUtils();
        document.append(Parameters.ID, idInterface.getNextSequence(_collection));
        //check for  correctness of fields
        try {
            if (document.get(Parameters.SHOW_NAME) == null || document.get(Parameters.SHOW_NAME).toString().trim().equals(""))
                return status.invalid_parameter.toString() + " " + Parameters.SHOW_NAME;
            if (document.get(Parameters.SHOW_DESCRIPTION) == null || document.get(Parameters.SHOW_DESCRIPTION).toString().trim().equals(""))
                return status.invalid_parameter.toString() + " " + Parameters.SHOW_DESCRIPTION;
            if (Integer.valueOf(document.get(Parameters.SHOW_BAND_ID).toString()) <= 0)
                return status.invalid_parameter.toString() + " " + Parameters.SHOW_BAND_ID;
            //check if band id exists
            if (new BandDao().read(document.get(Parameters.SHOW_BAND_ID).toString()) == null) {
                return status.invalid_parameter.toString() + " " + Parameters.SHOW_BAND_ID;
            }
            //insert empty instances array
            document.append(Parameters.SHOW_INSTANCE, Arrays.asList());

        } catch (Exception e) {
            e.printStackTrace();
            return status.invalid_document.toString();
        }
        _collection.insertOne(document);
        return status.OK.toString();
    }


    /***
     * @param showId
     * @return all of the instances for the show - no info for the seats
     */

    public Document showInstancesForShowNoSeats(int showId) {
        Bson filter = Filters.eq(Parameters.ID, showId);
        Bson projection = fields(Projections.include(Parameters.MOVIE_SCREENINGS), excludeId());
        return _collection.find(filter).projection(projection).first();
    }
    /**
     * @param id of the document
     * @return true if this show has any tickets purchased
     */
    @Override
    public boolean isInUse(String id) {
        //create an show instance doa instance
        ShowInstanceDao showInstanceDao = new ShowInstanceDao();
        //check if the instances inside the show document is in use
        Document document = read(id);
        List<Document> showInstanceList = (List<Document>) document.get(Parameters.MOVIE_SCREENINGS);
        for (Document cur : showInstanceList) {
            if (showInstanceDao.isInUse(cur.get(Parameters.ID).toString())) {
                //the instance is in use so we return false
                return true;
            }
        }
        return false;
    }
}


