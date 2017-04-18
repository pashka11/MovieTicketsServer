package org.yuval.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.yuval.objects.User;
import org.yuval.utils.Encryption;
import org.yuval.utils.EncryptionInterface;
import org.yuval.utils.Parameters;

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
public class UserDao implements Crud {

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

}
