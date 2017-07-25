package nimrodpasha.cinema.objects;

import org.joda.time.LocalDateTime;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Nimrod on 15/06/2017.
 */

@XmlRootElement
public class Screening
{
    public Screening()
    {

    }

    public LocalDateTime ScreeningTime;
    public int MovieId;
    public int TheaterId;
    public int [][] Seats;
}

//
///**
// * Screening  object
// */
//
//
//    public Screening(Date date, int theaterId, int price, ArrayList<Row> seats) {
//        this.date = date;
//        this.theaterId = theaterId;
//        this.price = price;
//        this.seats = seats;
//    }
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


