package org.yuval.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Random;

import static org.yuval.utils.Parameters.ID;

/**
 * Utilities for data access objects
 *
 */
//this class is for collections with increment id
public class DaoUtils {

    /**
     * @param collection
     * @return the next free ID
     */
    public int getNextSequence(MongoCollection collection) {
        int i =0;
        Document document = new Document(ID,i);
        while(document!=null){
            i++;
            Bson  filter = Filters.eq(ID,i);
            document = (Document) collection.find(filter).first();
        }
        return i;
    }

    /**
     * @param coll
     * @return an random ID from this collection
     */
    public int randomId(MongoCollection coll){
        ArrayList<Integer> allId = new ArrayList<>();
        MongoCursor<Document> cursor = coll.find().iterator();
        try {
            while (cursor.hasNext()) {
                Document cur = cursor.next();
                allId .add((int) cur.get(ID));
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            cursor.close();
        }
        return allId .get(new Random().nextInt(allId.size()));
    }

    /**
     * @param coll
     * @param parameter to update
     * @param document input document that contain fields to update
     * @return true if value has been changed
     */
    public static boolean checkAndSet(com.mongodb.client.MongoCollection<Document> coll, String parameter,Document document){
        if (document.get(parameter) != null && !(document.get(parameter).toString().trim().equals(""))) {
            BasicDBObject query = new BasicDBObject(ID, document.get(ID));
            BasicDBObject setQuery = new BasicDBObject("$set", new BasicDBObject(parameter, document.get(parameter)));
            coll.updateOne(query, setQuery);
            return true;
        }
        return false;
    }
}
