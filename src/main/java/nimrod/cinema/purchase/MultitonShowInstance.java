//package nimrodpasha.cinema.purchase;
//
//import nimrod.cinema.dao.ShowInstanceDao;
//import nimrod.cinema.dao.TheaterDao;
//import nimrod.cinema.dao.Crud;
//import nimrod.cinema.dao.Seat;
//import nimrod.cinema.utils.Parameters;
//import org.bson.Document;
//
//import java.util.concurrent.locks.ReentrantLock;
//
///**
// * Created by Yuval on 17-Mar-17.
// * only one show instance per id can exist
// */
//public class MultitonShowInstance {
//
//    private String showInstanceId;
//    private static ReentrantLock[][] locksArray;
//    private static String[][] reservationArray;
//
//    /**
//     * constructor
//     *
//     * @param showInstanceId to initialize
//     */
//    public MultitonShowInstance(String showInstanceId) {
//        this.showInstanceId = showInstanceId;
//        Crud showInstanceCrud = new ShowInstanceDao();
//        Crud theaterCrud = new TheaterDao();
//        Document showInstanceDocument =showInstanceCrud.read(showInstanceId);
//        int theaterId = (int) showInstanceDocument.get(Parameters.SHOW_INSTANCE_THEATER_ID);
//        Document theaterDocument = theaterCrud.read(String.valueOf(theaterId));
//        int rows = (int) theaterDocument.get(Parameters.THEATER_ROWS);
//        int columns = (int) theaterDocument.get(Parameters.THEATER_COLUMNS);
//        locksArray = new ReentrantLock[rows][columns];
//        reservationArray = new String[rows][columns];
//        //initialize the reservationArray so we wont get null
//        for (int i = 0; i < reservationArray.length; i++) {
//            for (int j = 0; j < reservationArray[i].length; j++) {
//                reservationArray[i][j] = "";
//            }
//        }
//        //initialize the locksArray so we wont get null
//        for (int i = 0; i < locksArray.length; i++) {
//            for (int j = 0; j < locksArray[i].length; j++) {
//                locksArray[i][j] = new ReentrantLock();
//            }
//        }
//
//    }
//
//    /**
//     * we lock the seat with the value of 2, if we get an approval we change it to 1 if we want to release we change it back to 0
//     *
//     * @param row
//     * @param column
//     * @param instanceId
//     * @param showId
//     * @param userId
//     * @return true if reservation was successful false otherwise
//     */
//    public Boolean reserveSeat(int row, int column, String instanceId, int showId, String userId) {
//        locksArray[row][column].lock();
//        boolean isFree = false;
//        Seat seat = new ShowInstanceDao();
//        try {
//            //if the seat in reserved or ordered
//            if (seat.getSeat(row, column, showInstanceId) != Parameters.SEAT_IS_FREE) {
//                isFree = false;
//            }
//            //reserve seat
//            else {
//                seat.changeSeatStatus(row, column, Parameters.SEAT_IS_RESERVED, showInstanceId, showId);
//                isFree = true;
//                reservationArray[row][column] = userId;
//            }
//        } catch (Exception e) {
//            System.out.println("error in row " + row + " column " + column + " method lockSeat");
//            e.printStackTrace();
//        } finally {
//            locksArray[row][column].unlock();
//            //a timer to release the seat
//            if (isFree) {
//                Thread thread= new PurchaseTimer(row, column, showId, instanceId);
//                thread.start();
//            }
//            return isFree;
//        }
//    }
//
//    /**
//     * release the seat after timeout
//     *
//     * @param row
//     * @param column
//     * @param showId
//     * @param showInstanceId
//     */
//    public void releaseSeatAfterTimeout(int row, int column, int showId, String showInstanceId) {
//        locksArray[row][column].lock();
//        try {
//            Seat seat= new ShowInstanceDao();
//            if (seat.getSeat(row, column, showInstanceId) == Parameters.SEAT_IS_RESERVED) {
//                //release seat
//                seat.changeSeatStatus(row, column, Parameters.SEAT_IS_FREE, showInstanceId, showId);
//                reservationArray[row][column] = "";
//            }
//        } catch (Exception e) {
//            System.out.println("error in row " + row + " column " + column + " method releaseSeat");
//            e.printStackTrace();
//        } finally {
//            locksArray[row][column].unlock();
//        }
//    }
//
//    /**
//     * release the seat
//     *
//     * @param row
//     * @param column
//     * @param showId
//     * @param userId
//     */
//    public void releaseSeat(int row, int column, int showId, String userId) {
//        locksArray[row][column].lock();
//        try {
//            Seat seat = new ShowInstanceDao();
//            //release seat
//            seat.changeSeatStatus(row, column, Parameters.SEAT_IS_FREE, showInstanceId, showId);
//            reservationArray[row][column] = "";
//        } catch (Exception e) {
//            System.out.println("error in row " + row + " column " + column + " method releaseSeat");
//            e.printStackTrace();
//        } finally {
//            locksArray[row][column].unlock();
//        }
//    }
//
//    /**
//     * approve the seat
//     *
//     * @param row
//     * @param column
//     * @param showId
//     * @param userId
//     * @return true if order was successful false otherwise
//     */
//    public Boolean approveSeat(int row, int column, int showId, String userId) {
//        locksArray[row][column].lock();
//        boolean isOrderOk = false;
//        try {
//            Seat seat = new ShowInstanceDao();
//            //seat is ordered
//            if (reservationArray[row][column].equals(userId)) {
//                seat.changeSeatStatus(row, column, Parameters.SEAT_IS_TAKEN, showInstanceId, showId);
//                isOrderOk = true;
//            }
//        } catch (Exception e) {
//            System.out.println("error in row " + row + " column " + column + " method approveSeat");
//            e.printStackTrace();
//        } finally {
//            locksArray[row][column].unlock();
//            return isOrderOk;
//        }
//    }
//
//}
