package nimrodpasha.cinema.initDB;

import nimrodpasha.cinema.dao.Crud;

import nimrodpasha.cinema.objects.Halls;

import nimrodpasha.cinema.utils.Parameters;
import nimrodpasha.cinema.dao.HallDao;
import java.util.ArrayList;

public class InitHall {

    public static void main(String[]args) {

        ArrayList<Halls> halls = new ArrayList<>();

        halls.add(new Halls(1, 10, 10));
        halls.add(new Halls(2,  7, 11));
        halls.add(new Halls(3,  8, 5 ));
        halls.add(new Halls(4,  9, 10 ));
        halls.add(new Halls(5,  10, 6 ));
        halls.add(new Halls(6,  5, 5));
        halls.add(new Halls(7, 11, 4 ));
        halls.add(new Halls(8,  4, 8));
        halls.add(new Halls(9,  4, 5));
        halls.add(new Halls(10,  5, 5 ));
        halls.add(new Halls(11,  10, 10));
        halls.add(new Halls(12,  12, 7 ));

        Crud crud = new HallDao();
        crud.dropAll();
        halls.parallelStream()
                .forEach(hall -> crud.create(hall));


    }


}
