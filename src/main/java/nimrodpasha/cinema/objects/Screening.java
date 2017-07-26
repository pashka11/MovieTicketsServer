package nimrodpasha.cinema.objects;

import org.joda.time.LocalDateTime;

import java.util.ArrayList;

/**
 * Created by Nimrod on 15/06/2017.
 */

public class Screening
{
    public LocalDateTime ScreeningTime;
    public int HallId;
    public ArrayList<Row> Seats;
    public int Price;

    public Screening()
	{

    }
    public Screening(LocalDateTime screeningTime, int hallId, int price, ArrayList<Row> seats)
	{
        this.ScreeningTime = screeningTime;
        this.HallId = hallId;
        this.Price = price;
        this.Seats = seats;
    }
}


