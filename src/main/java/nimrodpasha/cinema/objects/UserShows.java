package nimrodpasha.cinema.objects;




import java.util.ArrayList;

/**
 * Created by Yuval on 16-Mar-17.
 * UserShows object
 */
public class UserShows {
    private int showId;
    private ArrayList<Ticket>tickets=new ArrayList<>();

    public UserShows(int showId, ArrayList<Ticket> tickets) {
        this.showId = showId;
        this.tickets = tickets;
    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }
}
