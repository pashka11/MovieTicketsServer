package org.yuval.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.yuval.utils.Parameters;
import org.yuval.utils.RowColumnNameHandler;

import java.util.ArrayList;
import java.util.Arrays;

import static org.yuval.utils.Parameters.*;

/**
 * Created by Yuval on 20-Mar-17.
 */
public class MongoSet {
    /**
     * this method handles the adding ticket to a user
     * @param row of the seat
     * @param column of the seat
     * @param showInstanceID of the ticket
     * @param user to add ticket to
     */
    public static void setTicket(int row, int column, String showInstanceID, String user) {
        com.mongodb.client.MongoCollection<Document> coll = MongoCollection.getMongoCollection(Parameters.USERS_COLLECTION);
        BasicDBObject query = new BasicDBObject(ID, user);
        BasicDBObject instanceArr = new BasicDBObject();

        Bson filter = Filters.eq(ID,user);
        Document document=coll.find(filter).first();
        ArrayList<Document> documentArrayList = (ArrayList<Document>) document.get(USER_TICKETS);
        for (int i = 0; i < documentArrayList.size(); i++) {
            if (documentArrayList.get(i).get(USER_SHOW_INSTANCE_ID).toString().equals(showInstanceID)){
                instanceArr.put("$addToSet", new BasicDBObject(USER_TICKETS + "."+i+"." + USER_TICKETS_FOR_INSTANCE, RowColumnNameHandler.rowNumberToName(row) + " " + RowColumnNameHandler.columnNumberToName(column)));
                coll.updateOne(query, instanceArr);
                return;
            }
        }


        DBObject object = new BasicDBObject(USER_TICKETS,new BasicDBObject(USER_SHOW_INSTANCE_ID,showInstanceID).append(USER_TICKETS_FOR_INSTANCE, Arrays.asList(RowColumnNameHandler.rowNumberToName(row) + " " + RowColumnNameHandler.columnNumberToName(column))));
        DBObject updateQuery = new BasicDBObject("$push",object);
        coll.updateOne(query, (Bson) updateQuery);
    }
}

