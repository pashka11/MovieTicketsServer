package nimrodpasha.cinema.initDB;

import nimrodpasha.cinema.dao.BandDao;
import nimrodpasha.cinema.dao.RandomId;
//import nimrodpasha.cinema.utils.Help;
import nimrodpasha.cinema.dao.Crud;
import nimrodpasha.cinema.dao.MovieDao;
import nimrodpasha.cinema.objects.MovieDetails;
import nimrodpasha.cinema.utils.FillScreeningInstanceArrayInterface;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;


import java.util.ArrayList;
import java.util.Date;

import static nimrodpasha.cinema.utils.Parameters.IMAGE_FOLDER;

public class InitMovies {


    //this is an insert of default movies
    public static void main(String[]args){
        ArrayList<MovieDetails> movies =new ArrayList<>();

        //RandomId randomId = new BandDao();  //TODO maybe remove
        //FillScreeningInstanceArrayInterface fillScreeningInstanceArrayInterface = new Help();

        movies.add(new MovieDetails(1, "Fast and Furious 8", "After Dom is recruited by a mysterious villainess hacker," +
                " he turns against his family and most importantly," +
                " goes on the wrong side of the law again." +
                " Now, the crew must unite to bring home the man who made them a family and stop her from unleashing chaos on a grand scale.",
                                    "fast8.jpg", "F. Gary Gray", "Vin Diesel, Jason Statham, Dwayne Johnson, Michelle Rodriguez, Tyrese Gibson", new LocalDate(2017, 5, 6), "Action, Thriller, Crime", 136));




        movies.add(new MovieDetails(2,"McLaren","The story of Bruce McLaren, the New Zealander who founded the McLaren Motor Racing team. " +
                "A man who showed the world that a man of humble beginnings could take on the elite of motor racing and win." +
                " Now, the crew must unite to bring home the man who made them a family and stop her from unleashing chaos on a grand scale.",
                "Maclaren.jpg","Roger Donaldson"," Mario Andretti, Alastair Caldwell, Dwayne Cameron",new LocalDate(2017, 5, 6),"Documentary, Action,  Drama",92));

//
//        movies.add(new MovieDetails(1,"Fast and Furious 8","After Dom is recruited by a mysterious villainess hacker," +
//                " he turns against his family and most importantly," +
//                " goes on the wrong side of the law again." +
//                " Now, the crew must unite to bring home the man who made them a family and stop her from unleashing chaos on a grand scale.",
//                IMAGE_FOLDER+"fast8.jpg","F. Gary Gray","Vin Diesel, Jason Statham, Dwayne Johnson, Michelle Rodriguez, Tyrese Gibson","2017","Action, Thriller, Crime","136 min", fillScreeningInstanceArrayInterface.fillScreeningInstanceArray()));
//
//
//
//
//        movies.add(new MovieDetails(2,"McLaren","The story of Bruce McLaren, the New Zealander who founded the McLaren Motor Racing team. " +
//                "A man who showed the world that a man of humble beginnings could take on the elite of motor racing and win." +
//                " Now, the crew must unite to bring home the man who made them a family and stop her from unleashing chaos on a grand scale.",
//                IMAGE_FOLDER+"Maclaren.jpg","Roger Donaldson"," Mario Andretti, Alastair Caldwell, Dwayne Cameron","2017","Documentary, Action,  Drama","92 min", fillScreeningInstanceArrayInterface.fillScreeningInstanceArray()));
//
//
//
//        movies.add(new MovieDetails(3,"Pink Panther","The Pink Panther diamond is stolen once again from Lugash and the authorities call in Chief Inspector Clouseau from France." +
//                " His plane disappears en-route. This time, famous French TV reporter Marie Jouvet sets out to solve the mystery and starts to interview everybody connected to Clouseau. " +
//                "Each interviewee Dreyfus, Sir Charles & Lady Lytton (an ex-wife of Clouseau), George Lytton, Hercule Lajoy (assistant in A Shot In The Dark), " +
//                "and Cato tell of their run-ins with Clouseau. She is also kidnapped by mobster Bruno Langlois who doesn't want Clouseau found but she continues and finds Clouseau Sr.," +
//                " Clouseau's father. Is Clouseau alive or is he dead? Each interview has not-yet-seen or famous clips from the previous movies (since Peter Sellers has sadly passed away) " +
//                "as Marie continues to get a honest view or impression of the great French detective",
//                IMAGE_FOLDER+"PimkPanter.jpg", "Blake Edwards","Julie Andrews ,Denise Crosby,Claudia Cardinale ","1982", "Comedy, Crime, Mystery","62 min", fillScreeningInstanceArrayInterface.fillScreeningInstanceArray()));
//
//
//
//        movies.add(new MovieDetails(4,"Kung Fu Panda 3","When Po's long-lost panda father suddenly reappears, the reunited duo travels to a secret panda paradise to meet scores of hilarious new panda characters." +
//                " But when the supernatural villain Kai begins to sweep across China defeating all the kung fu masters, Po must do the impossible-learn to train a village full of his fun-loving, " +
//                "clumsy brethren to become the ultimate band of Kung Fu Pandas",
//                IMAGE_FOLDER+"Panda.jpg","Jennifer Yuh","Angelina Jolie as Tigress,Seth Rogen as Mantis,Bryan Cranston as Li,Kate Hudson as Mei Mei","2017","Action,Animation,Comedy,Family,Fantasy","88 min", fillScreeningInstanceArrayInterface.fillScreeningInstanceArray()));
//
//
//
//        movies.add(new MovieDetails(5,"The LEGO Batman Movie","There are big changes brewing in Gotham City, and if he wants to save the city from The Joker's hostile takeover," +
//                " Batman may have to drop the lone vigilante thing, try to work with others and maybe," +
//                " just maybe, learn to lighten up.",
//                IMAGE_FOLDER+"Lego.jpg",
//
//                "Chris McKay","Rosario Dawson as Batgirl,Channing Tatum as Superman,Ralph Fiennes as Alfred Pennyworth,Zoë Kravitz as Catwoman","2017","Action,Animation,Family,Fantasy","76 min", fillScreeningInstanceArrayInterface.fillScreeningInstanceArray()));
//
//
//        movies.add(new MovieDetails(6,"Kong: Skull Island","A washed up monster chaser convinces the U.S. Government to fund a trip to an unexplored island in the South Pacific. " +
//                "Under the guise of geological research, the team travels to Skull Island. Upon arrival, the group discover that their mission may be complicated by the wildlife which inhabits the island." +
//                " The beautiful vistas and deadly creatures create a visually stunning experience that is sure to keep your attention." +
//                " just maybe, learn to lighten up.",
//                IMAGE_FOLDER+"KONG.jpg","Jordan Vogt-Roberts","Brie Larson as Mason Weaver,Tom Hiddleston as James Conrad,Samuel L. Jackson as Preston Packard","2017","Action,Adventure,Fantasy,Sci-Fi","102 min", fillScreeningInstanceArrayInterface.fillScreeningInstanceArray()));
//
//
//        movies.add(new MovieDetails(7,"CHIPS","Jon Baker (Shepard) and Frank Ponch Poncherello (Peña) have just joined the California Highway Patrol (CHP) in Los Angeles, but for very different reasons." +
//                " Baker is a beaten-up former pro motorbiker trying to put his life and marriage back together. Poncherello is a cocky undercover Federal agent investigating a multi-million dollar heist that may be an inside-job inside the CHP." +
//                " The inexperienced rookie and the hardened pro are teamed together, but clash more than click, so kick-starting a real partnership is easier said than done. " +
//                "But with Baker's unique bike skills and Ponch's street savvy it might just work...if they don't drive each other crazy first.",
//                IMAGE_FOLDER+"CHIPS.jpg","Dax Shepard","Kristen Bell as Karen,Rosa Salazar as Ava,Vincent D'Onofrio as Vic Brown","2017","Action,Comedy,Crime","84 min", fillScreeningInstanceArrayInterface.fillScreeningInstanceArray()));
//
//
//        movies.add(new MovieDetails(8,"The Mask","Stanley Ipkiss (Jim Carrey) is a bank clerk that is an incredibly nice man. Unfortunately," +
//                " he is too nice for his own good and is a pushover when it comes to confrontations. After one of the worst days of his life, he finds a mask that depicts Loki, " +
//                "the Norse night god of mischief. Now, when he puts it on, he becomes his inner, self: a cartoon romantic wild man. However, a small time crime boss, Dorian Tyrel (Peter Greene), " +
//                "comes across this character dubbed The Mask by the media. After Ipkiss's alter ego indirectly kills his friend in crime," +
//                " Tyrel now wants this green-faced goon destroyed.",
//                IMAGE_FOLDER+"MASK.jpg","Chuck Russell","Jim Carrey as Stanley Ipkiss/The Mask,Cameron Diaz as Tina Carlyle,Amy Yasbeck as Peggy Brandt,Joely Fisher as Maggie","1994","Action,Comedy,CrimeAction,Family,Fantasy", 88, fillScreeningInstanceArrayInterface.fillScreeningInstanceArray()));

        Crud crud = new MovieDao();
        crud.dropAll();
        movies.parallelStream()
                .forEach(movie -> crud.create(movie));
    }


    }