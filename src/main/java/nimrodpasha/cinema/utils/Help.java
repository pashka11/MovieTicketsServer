//package nimrodpasha.cinema.utils;
//
//import nimrodpasha.cinema.dao.DaoUtils;
//import nimrodpasha.cinema.dao.TheaterDao;
//import nimrodpasha.cinema.objects.Row;
//import nimrodpasha.cinema.objects.Screening;
//import org.bson.Document;
//import org.bson.codecs.DocumentCodec;
//import org.bson.codecs.EncoderContext;
//import org.bson.json.JsonMode;
//import org.bson.json.JsonWriter;
//import org.bson.json.JsonWriterSettings;
//import org.joda.time.LocalDateTime;
//
//import java.io.StringWriter;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Random;
//
//public class Help implements ResponseDocument,FillScreeningInstanceArrayInterface{
//
//    private static final int MAX_SCREENINGS_TO_ENTER = 10;
//    private static final int MIN_SCREENINGS_TO_ENTER = 2;
//    private static final int SCREENINGS_PRICE = 45;
//    private static final int MAX_SCREENINGS_DAYS_DIFFERENCE = 25;
//    private static final int MIN_SCREENINGS_DAYS_DIFFERENCE = 2;
//    private static final int RANDOM_SCREENINGS_HOURS = 24;
//
//    /**
//     * debug purposes
//     * @param document to print
//     */
//    public static void printJson(Document document) {
//        JsonWriter jsonWriter = new JsonWriter(new StringWriter(),
//                new JsonWriterSettings(JsonMode.SHELL, true));
//        new DocumentCodec().encode(jsonWriter, document,
//                EncoderContext.builder()
//                        .isEncodingCollectibleDocument(true)
//                        .build());
//        System.out.println(jsonWriter.getWriter());
//        System.out.flush();
//    }
//
//    /**
//     * this method is for init screening instance
//     * @return array list of screening instances
//     */
//    public ArrayList<Screening> fillScreeningInstanceArray() {
//
//        ArrayList<Screening> screeningsInstances = new ArrayList<>();
//        //insert screening instances
//        Calendar showDate = Calendar.getInstance();//now date
//        int k = new Random().nextInt(MAX_SCREENINGS_TO_ENTER - MIN_SCREENINGS_TO_ENTER) + 1;
//        for (int i = 0; i < k; i++) {
//            Screening screeningInstance = new Screening();
//            screeningInstance.Price = SCREENINGS_PRICE;
//            screeningInstance.Time = LocalDateTime.now();
//            //set random theater
//			HallDao hallDao = new HallDao();
//            Document theater = new TheaterDao().read(String.valueOf(DaoUtils.randomId(hallDao.getCollection())));
//            screeningInstance.setTheaterId((int)(theater.get(Parameters.ID)));
//            ArrayList <Row> rows = new ArrayList<>();
//            for (int j = 1; j <=(int)theater.get(Parameters.THEATER_ROWS) ; j++) {
//                Integer arr []=new Integer[(int)theater.get(Parameters.THEATER_COLUMNS)];
//                rows.add(new Row(j,arr));
//                for (int l = 0; l < arr.length; l++) {
//                    arr[l]=0;
//                }
//            }
//            screeningInstance.setSeats(rows);
//
//            screeningsInstances.add(screeningInstance);
//
//        }
//        return screeningsInstances;
//    }
//
//    /**
//     * @param value String to put in the doc
//     * @return and JSON document with that look like this
//     * {response : "value"}
//     */
//
//    public Document docResponse(String value) {
//        return new Document(Parameters.RESPONSE, value);
//    }
//
//
//}
