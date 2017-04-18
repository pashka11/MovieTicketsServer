package org.yuval.utils;

import org.bson.Document;

import java.util.ArrayList;

import static org.yuval.utils.Parameters.SHOW_INSTANCE_SEATS;

/**
 * Created by Yuval on 18-Mar-17.
 *
 */
public class SeatsHandler implements SeatsInterface {

    /**
     * @param document of show instance
     * @return an Integer array of seats
     */
    public Integer[][] getSeatsFromShowInstanceDoc(Document document) {
        ArrayList<Document> seatDoc = (ArrayList<Document>) document.get(SHOW_INSTANCE_SEATS);
        Document doc = seatDoc.get(0);
        ArrayList<ArrayList<Integer[]>> asd = new ArrayList<>();

        for (Object g : doc.values()) {
            asd.add((ArrayList<Integer[]>) g);
        }
        Integer[][] arrayToReturn = new Integer[asd.size()][asd.get(0).size()];
        int i = 0;
        for (ArrayList<Integer[]> arr : asd) {

            for (int j = 0; j < arr.size(); j++) {
                arrayToReturn[i][j] = Integer.valueOf(String.valueOf(arr.get(j)));
            }
            i++;
        }
        return arrayToReturn;
    }


}
