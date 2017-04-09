package org.yuval.dao;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.yuval.utils.Parameters;

/**
 * Created by Yuval on 13-Mar-17.
 */
public class MongoCollection {

    /**
     * @param collectionName is the requested collection name
     * @return the collection
     */
    public static  com.mongodb.client.MongoCollection<Document> getMongoCollection(String collectionName){
        SingeltonMongoClient singeltonMongoClient = SingeltonMongoClient.getInstance();
        MongoClient client = singeltonMongoClient.getClient();
        MongoDatabase db = client.getDatabase(Parameters.TICKET_DATABASE);
        com.mongodb.client.MongoCollection<Document> coll=db.getCollection(collectionName);
        return coll;
    }
}
