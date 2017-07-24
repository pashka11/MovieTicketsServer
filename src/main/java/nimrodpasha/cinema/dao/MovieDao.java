package nimrodpasha.cinema.dao;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import com.sun.xml.internal.bind.v2.TODO;
import nimrodpasha.cinema.objects.Row;
import nimrodpasha.cinema.utils.Parameters;
import org.bson.Document;
import org.bson.conversions.Bson;
import nimrodpasha.cinema.objects.MovieDetails;
import org.bson.types.ObjectId;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static nimrodpasha.cinema.dao.MongoCollection.getMongoCollection;
import static nimrodpasha.cinema.utils.Parameters.*;


/**
 * data access object - movies collection
 */
public class MovieDao implements Crud, RandomId, UsageCheck {

    private com.mongodb.client.MongoCollection<Document> coll;

    /**
     * constructor
     */
    public MovieDao() {

        this.coll = getMongoCollection(Parameters.MOVIE_COLLECTION);
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
            Document doc = new Document(ID, movie.getId())
                    .append(BAND_NAME, movie.getName())
                    .append(BAND_DESCRIPTION, movie.getInfo())
                    .append(IMAGE_LINK, movie.getImageLink())
                    .append(DIRECTOR, movie.getDirector())
                    .append(DURATION, movie.getDuration())
                    .append(GENRES, movie.getGenres())
                    .append(RELEASEDATE, movie.getReleasedate())
                    .append(ACTORS, movie.getActors())
                    .append(Parameters.MOVIE_SCREENINGS, Arrays.asList());

            coll.insertOne(doc);



            for (int i = 0; i < movie.getScreenings().size(); i++) {//TODO move it to showInstanceDao

                Document seatDocument = new Document();
                for (Row row : movie.getScreenings().get(i).getSeats()) {
                    seatDocument
                            .append(Parameters.ROW_NUMBER + " " + row.getRowNumber(), Arrays.asList(row.getSeats()));
                }
                Document instance = new Document()
                        .append(Parameters.ID, new ObjectId())
                        .append(Parameters.MOVIE_INSTANCE_DATE, movie.getScreenings().get(i).getDate())
                        .append(Parameters.MOVIE_INSTANCE_PRICE, movie.getScreenings().get(i).getPrice())
                        .append(Parameters.MOVIE_INSTANCE_THEATER_ID, movie.getScreenings().get(i).getTheaterId())
                        .append(Parameters.MOVIE_INSTANCE_SEATS, Arrays.asList(seatDocument));
                coll.updateOne(eq(Parameters.ID, movie.getId()), Updates.addToSet(Parameters.MOVIE_SCREENINGS, instance));

            }



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
        return idInterface.randomId(coll);
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
        return coll.find().into(new ArrayList<Document>());
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
            if (checkAndSetInterface.checkAndSet(coll, Parameters.SHOW_DESCRIPTION, document)) {
                updated = true;
            }
            if (checkAndSetInterface.checkAndSet(coll, Parameters.SHOW_NAME, document)) {
                updated = true;
            }
            if (checkAndSetInterface.checkAndSet(coll, Parameters.IMAGE_LINK, document)) {
                updated = true;
            }
            //if band parameter was inserted check if valid and update
            if (document.get(Parameters.SHOW_BAND_ID) != null && new BandDao().read(document.get(Parameters.SHOW_BAND_ID).toString()) != null) {
                if (checkAndSetInterface.checkAndSet(coll, Parameters.SHOW_BAND_ID, document)) {
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
            coll.deleteOne(filter);
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
            coll.drop();
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
        document.append(Parameters.ID, idInterface.getNextSequence(coll));
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
        coll.insertOne(document);
        return status.OK.toString();
    }


    /**
     * @param showId
     * @return all of the instances for the show - no info for the seats
     */

    public Document showInstancesForShowNoSeats(int showId) {
        Bson filter = Filters.eq(Parameters.ID, showId);
        Bson projection = fields(Projections.include(Parameters.MOVIE_SCREENINGS), excludeId());
        return coll.find(filter).projection(projection).first();
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


