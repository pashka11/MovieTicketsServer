package org.yuval.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.yuval.utils.Parameters;
import org.yuval.utils.RowColumnNameHandler;
import org.yuval.utils.RowColumnNameInterface;

import java.util.ArrayList;
import java.util.Arrays;

import static org.yuval.utils.Parameters.*;

/**
 * Created by Yuval on 20-Mar-17.
 */
public interface MongoSet {
    /**
     * this method handles the adding ticket to a user
     *
     * @param row            of the seat
     * @param column         of the seat
     * @param showInstanceID of the ticket
     * @param user           to add ticket to
     * @param showId
     */
    public static void setTicket(int row, int column, String showInstanceID, String user, int showId) {
        com.mongodb.client.MongoCollection<Document> coll = MongoCollection.getMongoCollection(Parameters.USERS_COLLECTION);
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

