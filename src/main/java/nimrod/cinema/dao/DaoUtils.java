//package nimrod.cinema.dao;
//
//import com.mongodb.BasicDBObject;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoCursor;
//import com.mongodb.client.model.Filters;
//import org.bson.Document;
//import org.bson.conversions.Bson;
//
//import java.util.ArrayList;
//import java.util.Random;
//
///**
// * Utilities for data access objects
// *
// */
////this class is for collections with increment id
//public class DaoUtils implements IdInterface,CheckAndSetInterface {
//
//    /**
//     * @param collection
//     * @return the next free ID
//     */
//    @Override
//    public int getNextSequence(MongoCollection collection) {
//        int i =0;
//        Document document = new Document(Parameters.ID, i);
//        while(document!=null){
//            i++;
//            Bson  filter = Filters.eq(Parameters.ID, i);
//            document = (Document) collection.find(filter).first();
//        }
//        return i;
//    }
//
//    /**
//     * @param coll
//     * @return an random ID from this collection
//     */
//    @Override
//    public int randomId(MongoCollection coll){
//        ArrayList<Integer> allId = new ArrayList<>();
//        MongoCursor<Document> cursor = coll.find().iterator();
//        try {
//            while (cursor.hasNext()) {
//                Document cur = cursor.next();
//                allId .add((int) cur.get(Parameters.ID));
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        } finally {
//            cursor.close();
//        }
//        return allId .get(new Random().nextInt(allId.size()));
//    }
//
//    /**
//     * @param coll
//     * @param parameter to update
//     * @param document input document that contain fields to update
//     * @return true if value has been changed
//     */
//    @Override
//    public boolean checkAndSet(com.mongodb.client.MongoCollection<Document> coll, String parameter,Document document){
//
//        if (document.get(parameter) != null && !(document.get(parameter).toString().trim().equals(""))) {
//            BasicDBObject query = new BasicDBObject(Parameters.ID, document.get(Parameters.ID));
//            BasicDBObject setQuery = new BasicDBObject("$set", new BasicDBObject(parameter, document.get(parameter)));
//            coll.updateOne(query, setQuery);
//            return true;
//        }
//        return false;
//    }
//}
