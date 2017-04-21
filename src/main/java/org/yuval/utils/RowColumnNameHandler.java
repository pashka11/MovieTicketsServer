package org.yuval.utils;

import static org.yuval.utils.Parameters.COLUMN_NUMBER;
import static org.yuval.utils.Parameters.ROW_NUMBER;

/**
 * Created by Yuval on 20-Mar-17.
 * This class handles convection between seat position and name
 */
public class RowColumnNameHandler implements RowColumnNameInterface{

    public int nameToNumber(String s){
        return Integer.valueOf(s.replace(ROW_NUMBER+" ",""))-1;
    }
    public String rowNumberToName(int num){
        return ROW_NUMBER+" "+String.valueOf(num+1);
    }
    public String columnNumberToName(int num){
        return COLUMN_NUMBER+" "+String.valueOf(num+1);
    }


}
