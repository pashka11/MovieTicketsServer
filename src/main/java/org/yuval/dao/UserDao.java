package org.yuval.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.yuval.objects.User;
import org.yuval.utils.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static org.yuval.dao.MongoCollection.getMongoCollection;
import static org.yuval.utils.Parameters.*;

/**
 * Created by Yuval on 26-Mar-17.
 */
public class UserDao implements Crud,TicketUpdate {

    private MongoCollection<Document> coll;
    /**
     *  constructor
     */
    public UserDao() {
        this.coll = getMongoCollection(Parameters.USERS_COLLECTION);
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
            Document doc = new Document(ID, cur.getUserName())
                    .append(USER_PASSWORD, encryptionInterface.encrypt(cur.getPassword()))
                    .append(USER_IS_ADMIN, cur.isAdmin())
                    .append(USER_TICKETS, Arrays.asList());

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
        Document document = coll.find(eq(ID, id)).first();
        return document;
    }
    /**
     * @return all documents in this collection in a arrayList
     */
    @Override
    public List<Document> readAll() {
        //return only the names
        Bson projection = fields(include(ID));
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
        Bson filter = Filters.eq(ID, id);
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
            if (document.get(ID) == null || document.get(ID).toString().trim().equals("")){
                return status.invalid_parameter.toString() + " " + ID;
            }
            //check if id exists and if so reject
            if (read(document.get(ID).toString()) != null) {
                return status.duplicate_id.toString() + " " + document.get(ID).toString();
            }
            if (document.get(USER_PASSWORD) == null || document.get(USER_PASSWORD).toString().trim().equals("")){
                return status.invalid_parameter.toString() + " " + USER_PASSWORD;
            }
            //check for admin status
            if (document.get(USER_IS_ADMIN)!=null&&document.get(USER_IS_ADMIN).toString().equals("true")){
                document.put(USER_IS_ADMIN,true);
            }else{//user is not an admin
                document.put(USER_IS_ADMIN,false);
            }
            String s = document.get(USER_PASSWORD).toString();
            EncryptionInterface encryptionInterface = new Encryption();

            document.put(USER_PASSWORD, encryptionInterface.encrypt(s));

            document.append(USER_TICKETS, Arrays.asList());

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
        BasicDBObject query = new BasicDBObject(ID, user);
        BasicDBObject instanceArr = new BasicDBObject();
        RowColumnNameInterface rowColumnNameInterface = new RowColumnNameHandler();
        Bson filter = Filters.eq(ID, user);
        Document document = coll.find(filter).first();
        ArrayList<Document> documentArrayList = (ArrayList<Document>) document.get(USER_TICKETS);
//        show instance with this ID exists
        for (int i = 0; i < documentArrayList.size(); i++) {
            if (documentArrayList.get(i).get(USER_SHOW_INSTANCE_ID).toString().equals(showInstanceID)) {

                instanceArr.put("$addToSet", new BasicDBObject(USER_TICKETS + "." + i + "." + USER_TICKETS_FOR_INSTANCE, rowColumnNameInterface.rowNumberToName(row) +
                        " " + rowColumnNameInterface.columnNumberToName(column)));
                coll.updateOne(query, instanceArr);
                return;
            }
        }
//        show instance with this ID does not exists

        DBObject object = new BasicDBObject(USER_TICKETS, new BasicDBObject()
                .append(USER_SHOW_INSTANCE_ID, showInstanceID)
                .append(USER_TICKETS_SHOW_ID, showId)
                .append(USER_TICKETS_FOR_INSTANCE, Arrays.asList(rowColumnNameInterface.rowNumberToName(row) +
                        " " + rowColumnNameInterface.columnNumberToName(column))));
        DBObject updateQuery = new BasicDBObject("$push", object);
        coll.updateOne(query, (Bson) updateQuery);
    }
}
