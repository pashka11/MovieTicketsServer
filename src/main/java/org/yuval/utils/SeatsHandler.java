package org.yuval.utils;

import org.bson.Document;

import java.util.ArrayList;

import static org.yuval.utils.Parameters.SHOW_INSTANCE_SEATS;

/**
 * Created by Yuval on 18-Mar-17.
 *
 */
public class SeatsHandler {

    /**
     * @param document of show instance
     * @return an Integer array of seats
     */
    public static Integer[][] getSeatsFromShowInstanceDoc(Document document) {
        ArrayList<Document> seatDoc = (ArrayList<Document>) document.get(SHOW_INSTANCE_SEATS);
        Document doc = seatDoc.get(0);
        ArrayList<ArrayList<Integer[]>> tempArr = new ArrayList<>();

        for (Object g : doc.values()) {
            tempArr.add((ArrayList<Integer[]>) g);
        }
        Integer[][] arrayToReturn = new Integer[tempArr.size()][tempArr.get(0).size()];
        int i = 0;
        for (ArrayList<Integer[]> arr : tempArr) {

            for (int j = 0; j < arr.size(); j++) {
                arrayToReturn[i][j] = Integer.valueOf(String.valueOf(arr.get(j)));
            }
            i++;
        }
        return arrayToReturn;
    }


}
