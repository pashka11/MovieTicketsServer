package org.yuval.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.yuval.objects.Theater;
import org.yuval.utils.Parameters;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static org.yuval.dao.MongoCollection.getMongoCollection;
import static org.yuval.utils.Parameters.*;

/**
 * Created by Yuval on 26-Mar-17.
 */
public class TheaterDao implements Crud, RandomId, UsageCheck {

    private MongoCollection<Document> coll;
    /**
     *  constructor
     */
    public TheaterDao() {
        this.coll = getMongoCollection(Parameters.THEATER_COLLECTION);
    }
    /**
     * this method creates an object and insert it to the DB
     * @param obj is the document to insert
     * @return true if created false otherwise
     */
    @Override
    public boolean create(Object obj) {
        try {
            Theater cur = (Theater) obj;
            Document doc = new Document(ID, cur.getId())
                    .append(THEATER_NAME, cur.getName())
                    .append(THEATER_COLUMNS, cur.getColumns())
                    .append(THEATER_ROWS, cur.getRows())
                    .append(THEATER_LOCATION, cur.getLocation())
                    .append(IMAGE_LINK, cur.getImageLink());
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
    @Override
    public Document read(String id) {
        Document document = coll.find(eq(ID, Integer.valueOf(id))).first();
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
            if (read(document.get(ID).toString()) == null) {
                return false;
            }
            if (checkAndSetInterface.checkAndSet(coll,THEATER_NAME,document)){
                updated=true;
            }
            if (checkAndSetInterface.checkAndSet(coll,IMAGE_LINK,document)){
                updated=true;
            }
            if (checkAndSetInterface.checkAndSet(coll,THEATER_LOCATION,document)){
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
    @Override
    public boolean drop(String id) {
        Bson filter = Filters.eq(ID, Integer.valueOf(id));
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
     * @return random id of one of the documents in this collection
     */
    @Override
    public int randomId() {
        IdInterface idInterface = new DaoUtils();
        return idInterface.randomId(coll);
    }
    /**
     * @param document to insert
     * @return status
     */
    @Override
    public String insertValidation(Document document) {
        //put auto increment id
        IdInterface idInterface = new DaoUtils();
        document.append(ID, idInterface.getNextSequence(coll));
        //check for  correctness of fields
        try {
            if (document.get(THEATER_NAME) == null || document.get(THEATER_NAME).toString().trim().equals(""))
                return status.invalid_parameter.toString() + " " + THEATER_NAME;
            if (Integer.valueOf(document.get(THEATER_COLUMNS).toString()) <= 0)
                return status.invalid_parameter.toString() + " " + THEATER_COLUMNS;
            if (Integer.valueOf(document.get(THEATER_ROWS).toString()) <= 0)
                return status.invalid_parameter.toString() + " " + THEATER_ROWS;
            if (document.get(THEATER_LOCATION) == null || document.get(THEATER_LOCATION).toString().trim().equals(""))
                return status.invalid_parameter.toString() + " " + ROW_NUMBER;
        } catch (Exception e) {
            e.printStackTrace();
            return status.invalid_document.toString();
        }
        coll.insertOne(document);
        return status.OK.toString();
    }
    /**
     * @param id of the document
     * @return true if this band is playing in any shows
     */
    @Override
    public boolean isInUse(String id) {
        //check in all show instance if this theater is in use
        ShowDao showDao = new ShowDao();
        com.mongodb.client.MongoCollection<Document> mongoCollection = showDao.getColl();
        Bson filter = Filters.eq(SHOW_INSTANCE + "." + SHOW_INSTANCE_THEATER_ID, Integer.valueOf(id));
        Document document = mongoCollection.find().filter(filter).first();
        if (document == null) {
            return false;
        }
        return true;
    }
}
