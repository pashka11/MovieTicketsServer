package nimrodpasha.cinema.objects;

/**
 * Created by Yuval on 16-Mar-17.
 * row object
 */
public class Row {
    private int rowNumber;
    private Integer[]seats;

    public Row(int rowNumber, Integer[] seats) {
        this.rowNumber = rowNumber;
        this.seats = seats;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public Integer[] getSeats() {
        return seats;
    }

    public void setSeats(Integer[] seats) {
        this.seats = seats;
    }
}
