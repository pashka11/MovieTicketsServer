package nimrodpasha.cinema.dao;

/**
 * Created by Yuval on 26-Mar-17.
 */
public interface Seat {
    public void changeSeatStatus(int row, int column, int seatStatus, String showInstanceId,int showId);
    public int getSeat(int row, int column, String showInstanceId);
}
