package nimrodpasha.cinema.dao;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import nimrodpasha.cinema.utils.Constants;
import org.bson.Document;

/**
 * Created by Nimrod on 13-Mar-17.
 */
public class MongoCollection {

    /**
     * @param collectionName is the requested collection name
     * @return the collection
     */
    public static  com.mongodb.client.MongoCollection<Document> getMongoCollection(String collectionName)
    {
        SingeltonMongoClient singeltonMongoClient = SingeltonMongoClient.getInstance();
        MongoClient client = singeltonMongoClient.getClient();
        MongoDatabase db = client.getDatabase(Constants.DB.TICKET_DATABASE);
        com.mongodb.client.MongoCollection<Document> collection = db.getCollection(collectionName);

        return collection;
    }
}
