package org.yuval.objects;

/**
 * Created by Yuval on 16-Mar-17.
 * seat object
 */
public class Seat {
    private int row,column;
    private boolean isSaved;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Seat(int row, int column, boolean isSaved) {
        this.row = row;
        this.column = column;
        this.isSaved = isSaved;
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

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }
}
