package org.yuval.dao;

import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.yuval.objects.Band;
import org.yuval.utils.Parameters;

import java.util.ArrayList;
import java.util.List;

import static org.yuval.dao.MongoCollection.getMongoCollection;
import static org.yuval.utils.Parameters.*;

/**
 * Created by Yuval on 26-Mar-17.
 */
public class BandDao implements Crud, RandomId,UsageCheck {

    private com.mongodb.client.MongoCollection<Document> coll;
    /**
     *  constructor
     */
    public BandDao() {

        this.coll= getMongoCollection(Parameters.BANDS_COLLECTION);
    }

    /**
     * this method creates an object and insert it to the DB
     * @param obj is the document to insert
     * @return true if created false otherwise
     */
    @Override
    public boolean create(Object obj) {
        try {
            Band band = (Band) obj;
            Document doc = new Document(ID, band.getId())
                    .append(BAND_NAME, band.getName())
                    .append(BAND_DESCRIPTION, band.getInfo())
                    .append(IMAGE_LINK, band.getImageLink());
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
        Bson filter = Filters.eq(ID, Integer.valueOf(id));
        return coll.find(filter).first();
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
            //check if id exists
            if (read(document.get(ID).toString()) == null) {
                return false;
            }
            if (DaoUtils.checkAndSet(coll,BAND_NAME,document)){
                updated =true;
            }
            if (DaoUtils.checkAndSet(coll,IMAGE_LINK,document)){
                updated=true;
            }
            if (DaoUtils.checkAndSet(coll,BAND_DESCRIPTION,document)){
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
     * @param document to insert
     * @return status
     */
    @Override
    public String insertValidation(Document document) {
        //put auto increment id
        document.append(ID,new DaoUtils().getNextSequence(coll));
        //check for  correctness of fields
        try {
            if (document.get(BAND_NAME) == null || document.get(BAND_NAME).toString().trim().equals(""))
                return status.INVALID_PARAMETER.toString() + " " + BAND_NAME;

            if (document.get(BAND_DESCRIPTION) == null || document.get(BAND_DESCRIPTION).toString().trim().equals(""))
                return status.INVALID_PARAMETER.toString() + " " + BAND_DESCRIPTION;
        }catch (Exception e){
            e.printStackTrace();
            return status.INVALID_DOCUMENT.toString();
        }
        coll.insertOne(document);
        return status.OK.toString();
    }


    /**
     * @return random id of one of the documents in this collection
     */
    @Override
    public int randomId() {
        return new DaoUtils().randomId(coll);
    }

    /**
     * @param id of the document
     * @return true if this band is playing in any shows
     */
    @Override
    public boolean isInUse(String id) {
        ShowDao showDao = new ShowDao();
        com.mongodb.client.MongoCollection<Document> mongoCollection =  showDao.getColl();
        Bson filter = Filters.eq(SHOW_BAND_ID,Integer.valueOf(id)) ;
        Document document  = mongoCollection.find().filter(filter).first();
        if (document==null) {
            return false;
        }
        return true;
    }
}
