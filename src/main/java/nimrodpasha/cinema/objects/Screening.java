package nimrodpasha.cinema.objects;

import org.joda.time.LocalDateTime;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Created by Nimrod on 15/06/2017.
 */

@XmlRootElement
public class Screening {
//    public Screening()
//    {
//
//    }
//
//    public LocalDateTime ScreeningTime;
//    public int MovieId;
//    public int TheaterId;
//    public int [][] Seats;
//}


    /**
     * Screening  object
     */
    public LocalDateTime ScreeningTime;
    public int HallId;
    public ArrayList<Row> Seats;
    public int Price;

    public Screening() {

    }
    public Screening(LocalDateTime screeningTime, int hallId, int price, ArrayList<Row> seats) {
        this.ScreeningTime = ScreeningTime;
        this.HallId = hallId;
        this.Price = price;
        this.Seats = seats;
    }
}
//
//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
//
//    public int getTheaterId() {
//        return theaterId;
//    }
//
//    public void setTheaterId(int theaterId) {
//        this.theaterId = theaterId;
//    }
//
//    public int getPrice() {
//        return price;
//    }
//
//    public void setPrice(int price) {
//        this.price = price;
//    }
//
//    public ArrayList<Row> getSeats() {
//        return seats;
//    }
//
//    public void setSeats(ArrayList<Row> seats) {
//        this.seats = seats;
//    }

//}


