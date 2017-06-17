package nimrodpasha.cinema.utils;

/**
 * Created by Yuval on 20-Mar-17.
 * This class handles convection between seat position and name
 */
public class RowColumnNameHandler implements RowColumnNameInterface{

    public int nameToNumber(String s){
        return Integer.valueOf(s.replace(Parameters.ROW_NUMBER+" ", ""))-1;
    }
    public String rowNumberToName(int num){
        return Parameters.ROW_NUMBER+" "+String.valueOf(num+1);
    }
    public String columnNumberToName(int num){
        return Parameters.COLUMN_NUMBER+" "+String.valueOf(num+1);
    }


}
