package nimrodpasha.cinema.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import nimrodpasha.cinema.objects.User;
import nimrodpasha.cinema.utils.*;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.fields;

/**
 * Created by Yuval on 26-Mar-17.
 */
public class UserDao implements Crud,TicketUpdate {

    private MongoCollection<Document> coll;
    /**
     *  constructor
     */
    public UserDao() {
        this.coll = nimrodpasha.cinema.dao.MongoCollection.getMongoCollection(Parameters.USERS_COLLECTION);
    }
    /**
     * this method creates an object and insert it to the DB
     * @param obj is the document to insert
     * @return true if created false otherwise
     */
    @Override
    public boolean create(Object obj) {
        try {
            User cur = (User) obj;
            EncryptionInterface encryptionInterface = new Encryption();
            Document doc = new Document(Parameters.ID, cur.getUserName())
                    .append(Parameters.USER_PASSWORD, encryptionInterface.encrypt(cur.getPassword()))
                    .append(Parameters.USER_IS_ADMIN, cur.isAdmin())
                    .append(Parameters.USER_TICKETS, Arrays.asList());

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
        Document document = coll.find(eq(Parameters.ID, id)).first();
        return document;
    }
    /**
     * @return all documents in this collection in a arrayList
     */
    @Override
    public List<Document> readAll() {
        //return only the names
        Bson projection = fields(Projections.include(Parameters.ID));
        return coll.find().projection(projection).into(new ArrayList<Document>());
    }
    /**
     * @param document contain fields to update
     * @return true if update was successful ,false otherwise
     */
    @Override
    public boolean update(Document document) {
        return false;
    }
    /**
     * @param id of an object to delete
     * @return true if deletion was successful ,false otherwise
     */
    @Override
    public boolean drop(String id) {
        Bson filter = Filters.eq(Parameters.ID, id);
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
        //check for  correctness of fields
        try {
            if (document.get(Parameters.ID) == null || document.get(Parameters.ID).toString().trim().equals("")){
                return status.invalid_parameter.toString() + " " + Parameters.ID;
            }
            //check if id exists and if so reject
            if (read(document.get(Parameters.ID).toString()) != null) {
                return status.duplicate_id.toString() + " " + document.get(Parameters.ID).toString();
            }
            if (document.get(Parameters.USER_PASSWORD) == null || document.get(Parameters.USER_PASSWORD).toString().trim().equals("")){
                return status.invalid_parameter.toString() + " " + Parameters.USER_PASSWORD;
            }
            //check for admin status
            if (document.get(Parameters.USER_IS_ADMIN)!=null&&document.get(Parameters.USER_IS_ADMIN).toString().equals("true")){
                document.put(Parameters.USER_IS_ADMIN, true);
            }else{//user is not an admin
                document.put(Parameters.USER_IS_ADMIN, false);
            }
            String s = document.get(Parameters.USER_PASSWORD).toString();
            EncryptionInterface encryptionInterface = new Encryption();

            document.put(Parameters.USER_PASSWORD, encryptionInterface.encrypt(s));

            document.append(Parameters.USER_TICKETS, Arrays.asList());

        } catch (Exception e) {
            e.printStackTrace();
            return status.invalid_document.toString();
        }
        coll.insertOne(document);
        return status.OK.toString();
    }
    /**
     * this method handles the adding ticket to a user
     *
     * @param row            of the seat
     * @param column         of the seat
     * @param showInstanceID of the ticket
     * @param user           to add ticket to
     * @param showId         the show ID
     */
    @Override
    public void setTicket(int row, int column, String showInstanceID, String user, int showId) {
        BasicDBObject query = new BasicDBObject(Parameters.ID, user);
        BasicDBObject instanceArr = new BasicDBObject();
        RowColumnNameInterface rowColumnNameInterface = new RowColumnNameHandler();
        Bson filter = Filters.eq(Parameters.ID, user);
        Document document = coll.find(filter).first();
        ArrayList<Document> documentArrayList = (ArrayList<Document>) document.get(Parameters.USER_TICKETS);
//        show instance with this ID exists
        for (int i = 0; i < documentArrayList.size(); i++) {
            if (documentArrayList.get(i).get(Parameters.USER_SHOW_INSTANCE_ID).toString().equals(showInstanceID)) {

                instanceArr.put("$addToSet", new BasicDBObject(Parameters.USER_TICKETS + "." + i + "." + Parameters.USER_TICKETS_FOR_INSTANCE, rowColumnNameInterface.rowNumberToName(row) +
                        " " + rowColumnNameInterface.columnNumberToName(column)));
                coll.updateOne(query, instanceArr);
                return;
            }
        }
//        show instance with this ID does not exists

        DBObject object = new BasicDBObject(Parameters.USER_TICKETS, new BasicDBObject()
                .append(Parameters.USER_SHOW_INSTANCE_ID, showInstanceID)
                .append(Parameters.USER_TICKETS_SHOW_ID, showId)
                .append(Parameters.USER_TICKETS_FOR_INSTANCE, Arrays.asList(rowColumnNameInterface.rowNumberToName(row) +
                        " " + rowColumnNameInterface.columnNumberToName(column))));
        DBObject updateQuery = new BasicDBObject("$push", object);
        coll.updateOne(query, (Bson) updateQuery);
    }
}
