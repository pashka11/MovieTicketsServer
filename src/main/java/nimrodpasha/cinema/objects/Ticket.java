package nimrodpasha.cinema.objects;

import java.util.ArrayList;

/**
 * Created by Yuval on 16-Mar-17.
 * ticket object
 */
public class Ticket {

    private ArrayList<Seat>seats=new ArrayList();

    public Ticket(ArrayList<Seat> seats) {
        this.seats = seats;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }
}
