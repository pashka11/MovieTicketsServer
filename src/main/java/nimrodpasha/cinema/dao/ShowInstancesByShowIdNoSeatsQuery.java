package nimrodpasha.cinema.dao;

import org.bson.Document;

/**
 * Created by Yuval on 26-Mar-17.
 */
public interface ShowInstancesByShowIdNoSeatsQuery {
    Document showInstancesForShowNoSeats(int showId);
}
