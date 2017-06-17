package nimrodpasha.cinema.objects;

/**
 * Created by Yuval on 16-Mar-17.
 * seat object
 */
public class Seat {
    private int row,column;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
