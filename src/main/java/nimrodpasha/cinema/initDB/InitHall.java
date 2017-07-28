package nimrodpasha.cinema.initDB;

import nimrodpasha.cinema.dao.Crud;

import nimrodpasha.cinema.objects.Hall;

import nimrodpasha.cinema.dao.HallDao;
import java.util.ArrayList;

public class InitHall {

    public static void main(String[]args) {

        ArrayList<Hall> halls = new ArrayList<>();

        halls.add(new Hall(1, 10, 10));
        halls.add(new Hall(2,  7, 11));
        halls.add(new Hall(3,  8, 5 ));
        halls.add(new Hall(4,  9, 10 ));
        halls.add(new Hall(5,  10, 6 ));
        halls.add(new Hall(6,  5, 5));
        halls.add(new Hall(7, 11, 4 ));
        halls.add(new Hall(8,  4, 8));
        halls.add(new Hall(9,  4, 5));
        halls.add(new Hall(10,  5, 5 ));
        halls.add(new Hall(11,  10, 10));
        halls.add(new Hall(12,  12, 7 ));

        Crud crud = new HallDao();
        crud.dropAll();
        halls.parallelStream()
                .forEach(hall -> crud.create(hall));


    }


}
