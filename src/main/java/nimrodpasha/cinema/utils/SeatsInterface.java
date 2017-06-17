package nimrodpasha.cinema.utils;

import org.bson.Document;

public interface SeatsInterface {
    Integer[][] getSeatsFromShowInstanceDoc(Document document);
}
