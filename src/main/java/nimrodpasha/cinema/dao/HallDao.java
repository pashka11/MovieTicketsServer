package nimrodpasha.cinema.dao;

import com.mongodb.client.model.Filters;
import nimrodpasha.cinema.objects.Hall;
import nimrodpasha.cinema.utils.Constants;
import nimrodpasha.cinema.utils.Parameters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class HallDao implements Crud, UsageCheck {


    private com.mongodb.client.MongoCollection<Document> coll;
    /**
     *  constructor
     */
    public HallDao() {
        this.coll = nimrodpasha.cinema.dao.MongoCollection.getMongoCollection(Constants.Halls.HALLS_COLLECTION);
    }
    /**
     * this method creates an object and insert it to the DB
     * @param obj is the document to insert
     * @return true if created false otherwise
     */

    public boolean create(Object obj) {
        try {
            Hall cur = (Hall) obj;
            Document doc = new Document(Constants.Halls.HALL_ID, cur.HallId)
                    .append(Constants.Halls.HALLS_COLUMNS, cur.Column)
                    .append(Constants.Halls.HALLS_ROWS, cur.Row);

            coll.insertOne(doc);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /**
     * get a single document from the DB
     * @param id of the DB object
     * @return single document
     */

    public Document read(String id) {
        Document document = coll.find(eq(Constants.Halls.HALL_ID, Integer.valueOf(id))).first();
        return document;
    }
    /**
     * @return all documents in this collection in a arrayList
     */

    public List<Document> readAll() {

        return coll.find().into(new ArrayList<Document>());
    }
    /**
     * @param document contain fields to update
     * @return true if update was successful ,false otherwise
     */

    public boolean update(Document document) {
        boolean updated = false;
        try {
            CheckAndSetInterface checkAndSetInterface = new DaoUtils();
            //check if id exists
            if (read(document.get(Constants.Halls.HALL_ID).toString()) == null) {
                return false;
            }
            if (checkAndSetInterface.checkAndSet(coll, Parameters.THEATER_NAME, document)){
                updated=true;
            }
            if (checkAndSetInterface.checkAndSet(coll, Parameters.IMAGE_LINK, document)){
                updated=true;
            }
            if (checkAndSetInterface.checkAndSet(coll, Parameters.THEATER_LOCATION, document)){
                updated=true;
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

    public boolean drop(String id) {
        Bson filter = Filters.eq(Constants.Halls.HALL_ID, Integer.valueOf(id));
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
     * @return random id of one of the documents in this collection
     */

    public int randomId() {
        IdInterface idInterface = new DaoUtils();
        return idInterface.randomId(coll);
    }
    /**
     * @param document to insert
     * @return status
     */

    public String insertValidation(Document document) {
        //put auto increment id
        IdInterface idInterface = new DaoUtils();
        document.append(Constants.Halls.HALL_ID, idInterface.getNextSequence(coll));
        //check for  correctness of fields
        try {
            if (document.get(Parameters.THEATER_NAME) == null || document.get(Parameters.THEATER_NAME).toString().trim().equals(""))
                return Crud.status.invalid_parameter.toString() + " " + Parameters.THEATER_NAME;
            if (Integer.valueOf(document.get(Parameters.THEATER_COLUMNS).toString()) <= 0)
                return Crud.status.invalid_parameter.toString() + " " + Parameters.THEATER_COLUMNS;
            if (Integer.valueOf(document.get(Parameters.THEATER_ROWS).toString()) <= 0)
                return Crud.status.invalid_parameter.toString() + " " + Parameters.THEATER_ROWS;
            if (document.get(Parameters.THEATER_LOCATION) == null || document.get(Parameters.THEATER_LOCATION).toString().trim().equals(""))
                return Crud.status.invalid_parameter.toString() + " " + Parameters.ROW_NUMBER;
        } catch (Exception e) {
            e.printStackTrace();
            return Crud.status.invalid_document.toString();
        }
        coll.insertOne(document);
        return Crud.status.OK.toString();
    }
    /**
     * @param id of the document
     * @return true if this band is playing in any shows
     */

    public boolean isInUse(String id) {
        //check in all show instance if this theater is in use
        MovieDao showDao = new MovieDao();
//        com.mongodb.client.MongoCollection<Document> mongoCollection = showDao.getColl();
//        Bson filter = Filters.eq(Parameters.SHOW_INSTANCE + "." + Parameters.SHOW_INSTANCE_THEATER_ID, Integer.valueOf(id));
//        Document document = mongoCollection.find().filter(filter).first();
//        if (document == null) {
//            return false;
//        }
        return true;
    }



}
