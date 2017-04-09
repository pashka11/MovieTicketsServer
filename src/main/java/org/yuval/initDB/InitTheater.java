package org.yuval.initDB;


import org.yuval.dao.TheaterDao;
import org.yuval.objects.Theater;

import java.util.ArrayList;

import static org.yuval.utils.Parameters.IMAGE_FOLDER;

/**
 * Created by Yuval on 13-Mar-17.
 * this class is for initialize the theater collection
 */
public class InitTheater {


    //this is an insert of default theaters
    public static void main(String[]args) {

        ArrayList<Theater> theaters= new ArrayList<>();

        theaters.add(new Theater(1,"The Düsseldorfer Schauspielhaus",10,10,"Düsseldorf, Germany",IMAGE_FOLDER+"theater_1.jpg"));
        theaters.add(new Theater(2,"Balboa Theatre",7,11,"San Diego",IMAGE_FOLDER+"theater_2.jpg"));
        theaters.add(new Theater(3,"National Noh Theatre",8,5,"Tokyo, Japan",IMAGE_FOLDER+"theater_3.jpg"));
        theaters.add(new Theater(4,"Salle Richelieu",9,10,"Paris, France",IMAGE_FOLDER+"theater_4.jpg"));
        theaters.add(new Theater(5,"Margravial Opera House",10,6,"Bayreuth, Germany",IMAGE_FOLDER+"theater_5.jpg"));
        theaters.add(new Theater(6,"CARNEGIE HALL",5,5,"NEW YORK CITY",IMAGE_FOLDER+"theater_6.jpg"));
        theaters.add(new Theater(7,"VIENNA MUSIKVEREIN",11,4,"VIENNA",IMAGE_FOLDER+"theater_7.png"));
        theaters.add(new Theater(8,"WALT DISNEY CONCERT HALL",4,8,"LOS ANGELES",IMAGE_FOLDER+"theater_8.jpg"));
        theaters.add(new Theater(9,"ROYAL ALBERT HALL",4,5,"LONDON",IMAGE_FOLDER+"theater_9.jpg"));
        theaters.add(new Theater(10,"MADISON SQUARE GARDEN",5,5,"NEW YORK CITY",IMAGE_FOLDER+"theater_10.jpg"));
        theaters.add(new Theater(11,"CONCERTGEBOUW",10,10,"AMSTERDAM",IMAGE_FOLDER+"theater_11.jpg"));
        theaters.add(new Theater(12,"BOSTON SYMPHONY HALL",12,7,"BOSTON",IMAGE_FOLDER+"theater_12.jpg"));

        TheaterDao theaterDao = new TheaterDao();
        theaterDao.dropAll();
        theaters.parallelStream()
                .forEach(theater -> theaterDao.create(theater));


    }
}
