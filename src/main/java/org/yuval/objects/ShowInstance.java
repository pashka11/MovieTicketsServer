package org.yuval.objects;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Yuval on 14-Mar-17.
 * ShowInstance  object
 */
@XmlRootElement
public class ShowInstance {
    private Date date;
    private int theaterId,price;
    private ArrayList<Row> seats;


    public ShowInstance() {
    }

    public ShowInstance(Date date, int theaterId, int price, ArrayList<Row> seats) {
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
