package org.yuval.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.yuval.objects.Row;
import org.yuval.objects.Show;
import org.yuval.utils.Parameters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.*;
import static org.yuval.dao.MongoCollection.getMongoCollection;
import static org.yuval.utils.Parameters.*;

/**
 * Created by Yuval on 26-Mar-17.
 */
public class ShowDao implements Crud, ShowInstancesByShowIdNoSeatsQuery, UsageCheck {

    private MongoCollection<Document> coll;
    /**
     *  constructor
     */
    public ShowDao() {
        this.coll = getMongoCollection(Parameters.SHOW_COLLECTION);
    }

    /**
     * getter
     * @return this.coll
     */
    public MongoCollection<Document> getColl() {
        return this.coll;
    }

    /**
     * setter
     * @param coll
     */
    public void setColl(MongoCollection<Document> coll) {
        this.coll = coll;
    }
    /**
     * this method creates an object and insert it to the DB
     * @param obj is the document to insert
     * @return true if created false otherwise
     */
    @Override
    public boolean create(Object obj) {
        try {
            Show cur = (Show) obj;
            Document doc = new Document(ID, cur.getShowId())
                    .append(SHOW_NAME, cur.getName())
                    .append(SHOW_DESCRIPTION, cur.getDescription())
                    .append(SHOW_BAND_ID, cur.getBandId())
                    .append(IMAGE_LINK, cur.getImageLink())
                    .append(SHOW_INSTANCE, Arrays.asList());

            coll.insertOne(doc);

            for (int i = 0; i < cur.getShowInstances().size(); i++) {//TODO move it to showInstanceDao

                Document seatDocument = new Document();
                for (Row row : cur.getShowInstances().get(i).getSeats()) {
                    seatDocument
                            .append(ROW_NUMBER + " " + row.getRowNumber(), Arrays.asList(row.getSeats()));
                }
                Document instance = new Document()
                        .append(ID, new ObjectId())
                        .append(SHOW_INSTANCE_DATE, cur.getShowInstances().get(i).getDate())
                        .append(SHOW_INSTANCE_PRICE, cur.getShowInstances().get(i).getPrice())
                        .append(SHOW_INSTANCE_THEATER_ID, cur.getShowInstances().get(i).getTheaterId())
                        .append(SHOW_INSTANCE_SEATS, Arrays.asList(seatDocument));
                coll.updateOne(eq(ID, cur.getShowId()), Updates.addToSet(SHOW_INSTANCE, instance));

            }
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
        MongoCollection<Document> coll = getMongoCollection(SHOW_COLLECTION);
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
            //check if id exists
            if (read(document.get(ID).toString()) == null) {
                return false;
            }
            if (DaoUtils.checkAndSet(coll, SHOW_DESCRIPTION, document)) {
                updated = true;
            }
            if (DaoUtils.checkAndSet(coll, SHOW_NAME, document)) {
                updated = true;
            }
            if (DaoUtils.checkAndSet(coll, IMAGE_LINK, document)) {
                updated = true;
            }
            //if band parameter was inserted check if valid and update
            if (document.get(SHOW_BAND_ID) != null && new BandDao().read(document.get(SHOW_BAND_ID).toString()) != null) {
                if (DaoUtils.checkAndSet(coll, SHOW_BAND_ID, document)) {
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
        document.append(ID, new DaoUtils().getNextSequence(coll));
        //check for  correctness of fields
        try {
            if (document.get(SHOW_NAME) == null || document.get(SHOW_NAME).toString().trim().equals(""))
                return status.INVALID_PARAMETER.toString() + " " + SHOW_NAME;
            if (document.get(SHOW_DESCRIPTION) == null || document.get(SHOW_DESCRIPTION).toString().trim().equals(""))
                return status.INVALID_PARAMETER.toString() + " " + SHOW_DESCRIPTION;
            if (Integer.valueOf(document.get(SHOW_BAND_ID).toString()) <= 0)
                return status.INVALID_PARAMETER.toString() + " " + SHOW_BAND_ID;
            //check if band id exists
            if (new BandDao().read(document.get(SHOW_BAND_ID).toString()) == null) {
                return status.INVALID_PARAMETER.toString() + " " + SHOW_BAND_ID;
            }
            //insert empty instances array
            document.append(SHOW_INSTANCE, Arrays.asList());

        } catch (Exception e) {
            e.printStackTrace();
            return status.INVALID_DOCUMENT.toString();
        }
        coll.insertOne(document);
        return status.OK.toString();
    }


    /**
     * @param showId
     * @return all of the instances for the show - no info for the seats
     */
    @Override
    public Document showInstancesForShowNoSeats(int showId) {
        Bson filter = Filters.eq(ID, showId);
        Bson projection = fields(include(SHOW_INSTANCE), excludeId());
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
        List<Document> showInstanceList = (List<Document>) document.get(SHOW_INSTANCE);
        for (Document cur : showInstanceList) {
            if (showInstanceDao.isInUse(cur.get(ID).toString())) {
                //the instance is in use so we return false
                return true;
            }
        }
        return false;
    }
}
