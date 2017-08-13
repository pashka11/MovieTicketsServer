package nimrod.cinema.purchase;

import javax.ws.rs.QueryParam;

/**
 * Created by Yuval on 17-Mar-17.
 * an filter bean that holds all the info necessary for a purchase
 */
public class PurchaseFilterBean {
    private @QueryParam("user")String user;
    private @QueryParam("showId")int showId;
    private @QueryParam("showInstanceID")String showInstanceID;
    private @QueryParam("row")int row;
    private @QueryParam("column")int column;



    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public String getShowInstanceID() {
        return showInstanceID;
    }

    public void setShowInstanceID(String showInstanceID) {
        this.showInstanceID = showInstanceID;
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
