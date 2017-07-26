package nimrodpasha.cinema.objects;

/**
 * Created by Nimrod Gans on 25-Jul-17.
 * row object
 */
public class Row {

    public int RowNumber;
    public int[] Seats;

    public Row(int rowNumber, int[] seats) {
        this.RowNumber = rowNumber;
        this.Seats = seats;
    }
}
