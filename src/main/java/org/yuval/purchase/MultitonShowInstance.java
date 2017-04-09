package org.yuval.purchase;

import org.bson.Document;
import org.yuval.dao.ShowInstanceDao;
import org.yuval.dao.TheaterDao;
import org.yuval.objects.Seat;

import java.util.concurrent.locks.ReentrantLock;

import static org.yuval.utils.Parameters.*;

/**
 * Created by Yuval on 17-Mar-17.
 * only one show instance per id can exist
 */
public class MultitonShowInstance {

    private String showInstanceId;
    private static ReentrantLock[][] locksArray;
    private static String[][] reservationArray;

    /**
     * constructor
     * @param showInstanceId to initialize
     */
    public MultitonShowInstance(String showInstanceId) {
        this.showInstanceId = showInstanceId;
        Document showInstanceDocument = new ShowInstanceDao().read(showInstanceId);
        int theaterId = (int) showInstanceDocument.get(SHOW_INSTANCE_THEATER_ID);
        Document theaterDocument = new TheaterDao().read(String.valueOf(theaterId));
        int rows = (int) theaterDocument.get(THEATER_ROWS);
        int columns = (int) theaterDocument.get(THEATER_COLUMNS);
        this.locksArray = new ReentrantLock[rows][columns];
        this.reservationArray = new String[rows][columns];
        //initialize the reservationArray so we wont get null
        for (int i = 0; i < reservationArray.length; i++) {
            for (int j = 0; j < reservationArray[i].length; j++) {
                reservationArray[i][j] = "";
            }
        }
        //initialize the locksArray so we wont get null
        for (int i = 0; i < locksArray.length; i++) {
            for (int j = 0; j < locksArray[i].length; j++) {
                locksArray[i][j] = new ReentrantLock();
            }
        }

    }

    /**
     * we lock the seat with the value of 2, if we get an approval we change it to 1 if we want to release we change it back to 0
     * @param row
     * @param column
     * @param instanceId
     * @param showId
     * @param userId
     * @return true if reservation was successful false otherwise
     */
    public Boolean reserveSeat(int row, int column, String instanceId, int showId, String userId) {
        locksArray[row][column].lock();
        boolean isFree = false;
        try {
            //if the seat in reserved or ordered
            if (new ShowInstanceDao().getSeat(row, column, showInstanceId) != SEAT_IS_FREE) {
                isFree = false;
            }
            //reserve seat
            else {
                new ShowInstanceDao().changeSeatStatus(row, column, SEAT_IS_RESERVED, showInstanceId, showId);
                isFree = true;
                reservationArray[row][column] = userId;
            }
        } catch (Exception e) {
            System.out.println("error in row " + row + " column " + column + " method lockSeat");
            e.printStackTrace();
        } finally {
            locksArray[row][column].unlock();
            //a timer to realese the seet
            if (isFree){

            }
            PurchaseTimer purchaseTimer = new PurchaseTimer(row, column, showId,instanceId);
            purchaseTimer.start();
//            .run(row, column, showId,instanceId);
            return isFree;
        }
    }

    /**
     * release the seat after timeout
     * @param row
     * @param column
     * @param showId
     * @param showInstanceId
     */
    public void releaseSeatAfterTimeout(int row, int column, int showId,String showInstanceId) {
        locksArray[row][column].lock();
        try {
            if (new ShowInstanceDao().getSeat(row, column, showInstanceId) == SEAT_IS_RESERVED) {
                //release seat
                new ShowInstanceDao().changeSeatStatus(row, column, SEAT_IS_FREE, showInstanceId, showId);
                reservationArray[row][column] = "";
            }
        } catch (Exception e) {
            System.out.println("error in row " + row + " column " + column + " method releaseSeat");
            e.printStackTrace();
        } finally {
            locksArray[row][column].unlock();
        }
    }

    /**
     * release the seat
     * @param row
     * @param column
     * @param showId
     * @param userId
     */
    public void releaseSeat(int row, int column,  int showId, String userId) {
        locksArray[row][column].lock();
        try {
            //release seat
            new ShowInstanceDao().changeSeatStatus(row, column, SEAT_IS_FREE, showInstanceId, showId);
            reservationArray[row][column] = "";
        } catch (Exception e) {
            System.out.println("error in row " + row + " column " + column + " method releaseSeat");
            e.printStackTrace();
        } finally {
            locksArray[row][column].unlock();
        }
    }

    /**
     * approve the seat
     * @param row
     * @param column
     * @param showId
     * @param userId
     * @return true if order was successful false otherwise
     */
    public Boolean approveSeat(int row, int column, int showId, String userId) {
        locksArray[row][column].lock();
        boolean isOrderOk = false;
        try {
            //seat is ordered
            if (reservationArray[row][column].equals(userId)) {
                new ShowInstanceDao().changeSeatStatus(row, column, SEAT_IS_TAKEN, showInstanceId, showId);
                isOrderOk = true;
            }
        } catch (Exception e) {
            System.out.println("error in row " + row + " column " + column + " method approveSeat");
            e.printStackTrace();
        } finally {
            locksArray[row][column].unlock();
            return isOrderOk;
        }
    }

    public Seat reserveUnmarkedSeat(String showInstanceID, int showId, String user) {
        Seat seat = new Seat(0,0,false);
//        get the seat matrix, try to save a seat
        Integer [][]seatArr = new Integer[reservationArray.length][reservationArray[0].length];
        for (int i = 0; i < seatArr.length; i++) {
            for (int j = 0; j < seatArr[0].length; j++) {
                if (reserveSeat(i , j, showInstanceID, showId, user)){
                    seat.setSaved(true);
                    seat.setRow(i);
                    seat.setColumn(j);
                    return seat;
                }
            }

        }

        return seat;
    }
}
