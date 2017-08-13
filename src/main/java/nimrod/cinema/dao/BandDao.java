//package nimrodpasha.cinema.dao;
//
//import com.mongodb.client.model.Filters;
//import nimrod.cinema.objects.Band;
//import nimrod.cinema.utils.Parameters;
//import org.bson.Document;
//import org.bson.conversions.Bson;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static nimrod.cinema.dao.MongoCollection.getMongoCollection;
//import static nimrod.cinema.utils.Parameters.*;
//
///**
// * data access object - bands collection
// */
//public class BandDao implements Crud, RandomId, UsageCheck {
//
//    private com.mongodb.client.MongoCollection<Document> _collection;
//
//    /**
//     * constructor
//     */
//    public BandDao() {
//
//        this._collection = getMongoCollection(Parameters.BANDS_COLLECTION);
//    }
//
//    /**
//     * this method creates an object and insert it to the DB
//     *
//     * @param obj is the document to insert
//     * @return true if created false otherwise
//     */
//    @Override
//    public boolean create(Object obj) {
//        try {
//            Band band = (Band) obj;
//            Document doc = new Document(ID, band.getId())
//                    .append(BAND_NAME, band.getName())
//                    .append(BAND_DESCRIPTION, band.getInfo())
//                    .append(IMAGE_LINK, band.getImageLink())
//                    .append(DIRECTOR, band.getDirector())
//                    .append(DURATION, band.getDuration())
//                    .append(GENRES, band.getGenres())
//                    .append(RELEASEDATE, band.getReleasedate())
//                    .append(ACTORS, band.getActors());
//            _collection.insertOne(doc);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * get a single document from the DB
//     *
//     * @param id of the DB object
//     * @return single document
//     */
//    @Override
//    public Document read(String id) {
//        Bson filter = Filters.eq(ID, Integer.valueOf(id));
//        return _collection.find(filter).first();
//    }
//
//    /**
//     * @return all documents in this collection in a arrayList
//     */
//    @Override
//    public List<Document> readAll() {
//        return _collection.find().into(new ArrayList<>());
//    }
//
//    /**
//     * @param document contain fields to update
//     * @return true if update was successful ,false otherwise
//     */
//    @Override
//    public boolean update(Document document) {
//        boolean updated = false;
//        try {
//            CheckAndSetInterface checkAndSetInterface = new DaoUtils();
//            //check if id exists
//            if (read(document.get(ID).toString()) == null) {
//                return false;
//            }
//            if (checkAndSetInterface.checkAndSet(_collection, BAND_NAME, document)) {
//                updated = true;
//            }
//            if (checkAndSetInterface.checkAndSet(_collection, IMAGE_LINK, document)) {
//                updated = true;
//            }
//            if (checkAndSetInterface.checkAndSet(_collection, BAND_DESCRIPTION, document)) {
//                updated = true;
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//
//            return false;
//        }
//        return updated;
//    }
//
//    /**
//     * @param id of an object to delete
//     * @return true if deletion was successful ,false otherwise
//     */
//    @Override
//    public boolean delete(String id) {
//
//        Bson filter = Filters.eq(ID, Integer.valueOf(id));
//        try {
//            if (read(id) == null) {
//                return false;
//            }
//            _collection.deleteOne(filter);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * delete all documents in the current collection
//     *
//     * @return true if deletion was successful ,false otherwise
//     */
//    @Override
//    public boolean deleteAll() {
//        try {
//            _collection.delete();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * @param document to insert
//     * @return status
//     */
//    @Override
//    public String insertValidation(Document document) {
//        //put auto increment id
//        IdInterface idInterface = new DaoUtils();
//        document.append(ID, idInterface.getNextSequence(_collection));
//        //check for  correctness of fields
//        try {
//            if (document.get(BAND_NAME) == null || document.get(BAND_NAME).toString().trim().equals(""))
//                return status.invalid_parameter.toString() + " " + BAND_NAME;
//
//            if (document.get(BAND_DESCRIPTION) == null || document.get(BAND_DESCRIPTION).toString().trim().equals(""))
//                return status.invalid_parameter.toString() + " " + BAND_DESCRIPTION;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return status.invalid_document.toString();
//        }
//        _collection.insertOne(document);
//        return status.OK.toString();
//    }
//
//
//    /**
//     * @return random id of one of the documents in this collection
//     */
//    @Override
//    public int randomId() {
//        IdInterface idInterface = new DaoUtils();
//        return idInterface.randomId(_collection);
//    }
//
//    /**
//     * @param id of the document
//     * @return true if this band is playing in any shows
//     */
//    @Override
//    public boolean isInUse(String id) {
//        ShowDao showDao = new ShowDao();
//        com.mongodb.client.MongoCollection<Document> mongoCollection = showDao.getColl();
//        Bson filter = Filters.eq(SHOW_BAND_ID, Integer.valueOf(id));
//        Document document = mongoCollection.find().filter(filter).first();
//        return document != null;
//    }
//}
