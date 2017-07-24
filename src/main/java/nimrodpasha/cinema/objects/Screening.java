package nimrodpasha.cinema.objects;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import org.joda.time.LocalDateTime;

/**
 * Created by Nimrod on 15/06/2017.
 */
//
//public class Screening
//{
//    public Screening()
//    {
//
//    }
//
//    public LocalDateTime ScreeningTime;
//    public int MovieId;
//    public int HallId;
//    public int [][] Seats;
//}


/**
 * Screening  object
 */
@XmlRootElement
public class Screening {
    private Date date;
    private int theaterId, price;
    private ArrayList<Row> seats;


    public Screening() {
    }

    public Screening(Date date, int theaterId, int price, ArrayList<Row> seats) {
        this.date = date;
        this.theaterId = theaterId;
        this.price = price;
        this.seats = seats;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ArrayList<Row> getSeats() {
        return seats;
    }

    public void setSeats(ArrayList<Row> seats) {
        this.seats = seats;
    }

}


