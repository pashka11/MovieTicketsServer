//package nimrodpasha.cinema.dao;
//
//import com.mongodb.BasicDBObject;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoCursor;
//import com.mongodb.client.model.Filters;
//import com.mongodb.client.model.Updates;
//import nimrod.cinema.utils.*;
//import org.bson.Document;
//import org.bson.conversions.Bson;
//import org.bson.types.ObjectId;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static com.mongodb.client.model.Filters.eq;
//
///**
// * Created by Yuval on 26-Mar-17.
// */
//public class ShowInstanceDao implements Crud, Seat, UsageCheck {
//
//
//    private MongoCollection<Document> coll;
//
//    /**
//     * constructor
//     */
//    public ShowInstanceDao() {
//
//        this.coll = nimrod.cinema.dao.MongoCollection.getMongoCollection(Parameters.SHOW_COLLECTION);
//    }
//
//    /**
//     * getter
//     *
//     * @return this.coll
//     */
//    public MongoCollection<Document> getColl() {
//        return coll;
//    }
//
//    /**
//     * setter
//     *
//     * @param coll
//     */
//    public void setColl(MongoCollection<Document> coll) {
//        this.coll = coll;
//    }
//
//    /**
//     * the insertValidation method handles the creation of an object
//     * @param obj
//     * @return false
//     */
//    @Override
//    public boolean create(Object obj) {
//        return false;
//    }
//    /**
//     * get a single document from the DB
//     * @param id of the DB object
//     * @return single document
//     */
//    @Override
//    public Document read(String id) {
//        Document showInstance = null;
//
//        MongoCursor<Document> cursor = coll.find().iterator();
//        try {
//            while (cursor.hasNext()) {
//                Document doc = cursor.next();
//                List<Document> showInstanceList = (List<Document>) doc.get(Parameters.SHOW_INSTANCE);
//                for (Document cur : showInstanceList) {
//                    if (cur.get(Parameters.ID).toString().equals(id)) {
//                        showInstance = cur;
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        } finally {
//            cursor.close();
//        }
//        return showInstance;
//    }
//    /**
//     * @return all documents in this collection in a arrayList
//     */
//    //@Override
//    public List<Document> readAll() {
//
//        return coll.find().into(new ArrayList<Document>());
//    }
//    /**
//     * @param document contain fields to update
//     * @return true if update was successful ,false otherwise
//     */
//    @Override
//    public boolean update(Document document) {
//        return false;
//    }
//    /**
//     * @param id of an object to delete
//     * @return true if deletion was successful ,false otherwise
//     */
//    @Override
//    public boolean delete(String id) {
//        try {
//            if (read(id) == null) {
//                return false;
//            }
//            //get the show id for the instance
//            Bson filter = Filters.eq(Parameters.SHOW_INSTANCE + "." + Parameters.ID, new ObjectId(id));
//            Document document = coll.find().filter(filter).first();
//            //pull the instance from the array that is located in the show document
//            BasicDBObject match = new BasicDBObject(Parameters.ID, document.get(Parameters.ID));
//            BasicDBObject condition = new BasicDBObject(Parameters.ID, new ObjectId(id));
//            BasicDBObject find = new BasicDBObject(Parameters.SHOW_INSTANCE, condition);
//            BasicDBObject update = new BasicDBObject("$pull", find);
//            coll.updateOne(match, update);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
//    /**
//     *
//     * @return false
//     */
//    @Override
//    public boolean deleteAll() {
//        return false;
//    }
//    /**
//     * @param document to insert
//     * @return status
//     */
//    @Override
//    public String insertValidation(Document document) {
//        int showId = 0;
//        try {
//            //check if show id is a valid ID
//            showId = Integer.valueOf(document.get(Parameters.ID).toString());
//            if (showId <= 0) {
//                return status.invalid_parameter.toString() + " " + Parameters.ID;
//            }
//            if (new ShowDao().read(String.valueOf(showId)) == null) {
//                return status.invalid_parameter.toString() + " " + Parameters.ID;
//            }
//            //we generate id for the document
//            document.append(Parameters.ID, new ObjectId());
//
//            if (document.get(Parameters.SHOW_INSTANCE_DATE) == null || document.get(Parameters.SHOW_INSTANCE_DATE).toString().trim().equals("")) {
//                return status.invalid_parameter.toString() + " " + Parameters.SHOW_INSTANCE_DATE;
//            }
//            if (Integer.valueOf(document.get(Parameters.SHOW_INSTANCE_PRICE).toString()) <= 0) {
//                return status.invalid_parameter.toString() + " " + Parameters.SHOW_INSTANCE_PRICE;
//            }
//            //check theater id is valid
//            if (Integer.valueOf(document.get(Parameters.SHOW_INSTANCE_THEATER_ID).toString()) <= 0) {
//                return status.invalid_parameter.toString() + " " + Parameters.SHOW_INSTANCE_THEATER_ID;
//            }
//            Document theaterDocument = new TheaterDao().read(document.get(Parameters.SHOW_INSTANCE_THEATER_ID).toString());
//            if (theaterDocument == null) {
//                return status.invalid_parameter.toString() + " " + Parameters.SHOW_INSTANCE_THEATER_ID;
//            }
//            //fill the row column according to the theater document
//            Document seatDocument = new Document();
//            int rows = Integer.valueOf(theaterDocument.get(Parameters.THEATER_ROWS).toString());
//            int columns = Integer.valueOf(theaterDocument.get(Parameters.THEATER_COLUMNS).toString());
//            for (int i = 0; i < rows; i++) {
//                Integer[] arr = new Integer[columns];
//                for (int j = 0; j < columns; j++) {
//                    arr[j] = 0;
//                }
//                RowColumnNameInterface rowColumnNameInterface = new RowColumnNameHandler();
//                seatDocument.append(rowColumnNameInterface.rowNumberToName(i), Arrays.asList(arr));
//            }
//            document.append(Parameters.SHOW_INSTANCE_SEATS, Arrays.asList(seatDocument));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return status.invalid_document.toString();
//        }
//        coll.updateOne(eq(Parameters.ID, showId), Updates.addToSet(Parameters.SHOW_INSTANCE, document));
//        return status.OK.toString();
//    }
//
//    /**
//     * set the seat located in the input row,column the seat status
//     *
//     * @param row
//     * @param column
//     * @param seatStatus
//     * @param showInstanceId
//     * @param showId
//     */
//    @Override
//    public void changeSeatStatus(int row, int column, int seatStatus, String showInstanceId, int showId) {
//        BasicDBObject query = new BasicDBObject(Parameters.ID, showId);
//        query.put(Parameters.SHOW_INSTANCE + "." + Parameters.ID, new ObjectId(showInstanceId));
//        RowColumnNameInterface rowColumnNameInterface = new RowColumnNameHandler();
//        BasicDBObject setQuery = new BasicDBObject("$set", new BasicDBObject(Parameters.SHOW_INSTANCE + ".$." + Parameters.SHOW_INSTANCE_SEATS + ".0." + rowColumnNameInterface.rowNumberToName(row) + "." + column, seatStatus));
//        coll.updateOne(query, setQuery);
//    }
//
//    /**
//     * @param row
//     * @param column
//     * @param showInstanceId
//     * @return the status of the requested seat
//     */
//    @Override
//    public int getSeat(int row, int column, String showInstanceId) {
//        SeatsInterface seatsInterface = new SeatsHandler();
//        Integer[][] seatsArray = seatsInterface.getSeatsFromShowInstanceDoc(new ShowInstanceDao().read(showInstanceId));
//        return seatsArray[row][column];
//    }
//
//    /**
//     * @param id of the document
//     * @return true if this any of the seats is taken
//     */
//    @Override
//    public boolean isInUse(String id) {
//        //get the correct seat array
//        SeatsInterface seatsInterface = new SeatsHandler();
//        Integer seatsArr[][] = seatsInterface.getSeatsFromShowInstanceDoc(read(id));
//        //check if all seats are free
//        for (int i = 0; i < seatsArr.length; i++) {
//            for (int j = 0; j < seatsArr[0].length; j++) {
//                if (seatsArr[i][j] != 0) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//}
