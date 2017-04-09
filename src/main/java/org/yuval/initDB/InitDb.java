package org.yuval.initDB;

/**
 * Created by Yuval on 26-Mar-17.
 * this class is for initialize the whole DB
 */
public class InitDb {
    public static void main(String[] args) {
        String [] argumentss = new String[]{""};
        new InitBand().main(argumentss);
        new InitTheater().main(argumentss);
        new InitUser().main(argumentss);
        new InitShow().main(argumentss);
    }
}
