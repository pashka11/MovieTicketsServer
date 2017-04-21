package org.yuval.initDB;

/**
 * Created by Yuval on 26-Mar-17.
 * this class is for initialize the whole DB
 */
public class InitDb {
    public static void main(String[] args) {
        String [] arguments = new String[]{""};
        new InitBand().main(arguments);
        new InitTheater().main(arguments);
        new InitUser().main(arguments);
        new InitShow().main(arguments);
    }
}
