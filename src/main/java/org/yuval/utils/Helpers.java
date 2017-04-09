package org.yuval.utils;

import org.bson.Document;
import org.bson.codecs.DocumentCodec;
import org.bson.codecs.EncoderContext;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriter;
import org.bson.json.JsonWriterSettings;
import org.yuval.dao.TheaterDao;
import org.yuval.objects.Row;
import org.yuval.objects.ShowInstance;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import static org.yuval.utils.Parameters.*;

/**
 * Created by Yuval on 13-Mar-17.
 */
public class Helpers {
    private static final int MAX_SHOWS_TO_ENTER = 15;
    private static final int MIN_SHOWS_TO_ENTER = 5;
    private static final int MAX_SHOW_PRICE = 1000;
    private static final int MIN_SHOW_PRICE = 100;
    private static final int MAX_SHOW_DAYS_DIFFERENCE = 25;
    private static final int MIN_SHOW_DAYS_DIFFERENCE = 2;
    private static final int RANDOM_SHOW_HOURS = 24;

    /**debug purposes
     * @param document to print
     */
    public static void printJson(Document document) {
        JsonWriter jsonWriter = new JsonWriter(new StringWriter(),
                new JsonWriterSettings(JsonMode.SHELL, true));
        new DocumentCodec().encode(jsonWriter, document,
                EncoderContext.builder()
                        .isEncodingCollectibleDocument(true)
                        .build());
        System.out.println(jsonWriter.getWriter());
        System.out.flush();
    }

    /**
     * this method is for init show instance
     * @return array list of shows instances
     */
    public static ArrayList<ShowInstance> fillShowInstanceArray() {

        ArrayList<ShowInstance> showInstances = new ArrayList<>();
        //insert show instances
        Calendar showDate = Calendar.getInstance();//now date
        int k = new Random().nextInt(MAX_SHOWS_TO_ENTER - MIN_SHOWS_TO_ENTER) + 1;
        for (int i = 0; i < k; i++) {
            ShowInstance showInstance = new ShowInstance();
            //set price
            showInstance.setPrice(new Random().nextInt(MAX_SHOW_PRICE - MIN_SHOW_PRICE) + 1);
            //set date for the show
            showDate.add(Calendar.DATE, new Random().nextInt((MAX_SHOW_DAYS_DIFFERENCE - MIN_SHOW_DAYS_DIFFERENCE) + 1));
            //set different hours
            showDate.add(Calendar.DATE, new Random().nextInt((RANDOM_SHOW_HOURS) + 1));
            //set minutes and seconds to zero
            showDate.set(Calendar.MILLISECOND, 0);
            showDate.set(Calendar.MINUTE, 0);
            showDate.set(Calendar.SECOND, 0);
            //set date
            showInstance.setDate(showDate.getTime());
            //set random theater
            Document theater = new TheaterDao().read(String.valueOf(new TheaterDao().randomId()));
            showInstance.setTheaterId((int)(theater.get(ID)));
            ArrayList <Row> rows = new ArrayList<>();
            for (int j = 1; j <=(int)theater.get(THEATER_ROWS) ; j++) {
                Integer arr []=new Integer[(int)theater.get(THEATER_COLUMNS)];
                rows.add(new Row(j,arr));
                for (int l = 0; l < arr.length; l++) {
                    arr[l]=0;
                }
            }
            showInstance.setSeats(rows);

            showInstances.add(showInstance);

        }
        return showInstances;
    }
}
