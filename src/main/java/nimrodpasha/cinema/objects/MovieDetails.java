package nimrodpasha.cinema.objects;

import org.joda.time.LocalDate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nimrod on 15/06/2017.
 */
//
//public class MovieDetails implements Serializable
//{
//    public MovieDetails()
//    {
//        Actors = new ArrayList<>();
//    }
//
//    public MovieDetails(int id, String name, String description, String imageName, LocalDate releaseDate, String director, short duration, String genres, List<String> actors)
//    {
//        Id = id;
//        Name = name;
//        Description = description;
//        ImageName = imageName;
//        ReleaseDate = releaseDate;
//        Director = director;
//        Actors = actors;
//        Duration = duration;
//        Genres = genres;
//    }
//
//    public int Id;
//    public String Name;
//    public String Description;
//    public String ImageName;
//    public LocalDate ReleaseDate;
//    public String Director;
//    public short Duration;
//    public String Genres;
//    public List<String> Actors;
//}

public class MovieDetails implements Serializable {

    private String name, info, imageLink;
    int id;
    String str;
    private String director, actors, releasedate, genres, duration;
    private ArrayList<Screening>screenings = new ArrayList<Screening>();

    public MovieDetails() {}

    public MovieDetails(int id, String name, String info,String imageLink,String director,
                String actors ,String releasedate,String genres, String duration,ArrayList<Screening> screenings) {
        super();
        this.imageLink=imageLink;
        this.id = id;
        this.name = name;
        this.info = info;
        this.director = director;
        this.actors = actors;
        this.releasedate = releasedate;
        this.genres = genres ;
        this.duration = duration;
        this.screenings = screenings;
    }


    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String  getDirector (){ return director;}
    public String  getActors() {return actors;}
    public String  getReleasedate() {return releasedate;}
    public String  getGenres() {return genres;}
    public String  getDuration() {return duration;}
    public ArrayList<Screening> getScreenings() {
        return screenings;
    }

    public void  setDirector (String director ){  this.director = director;}
    public void  setActors(String actors) {this.actors =  actors;}
    public void  setReleasedate(String releasedate) {this.releasedate = releasedate;}
    public void  setGenres(String genres) {this.genres = genres;}
    public void  setDuration(String duration) {this.duration =  duration;}

    public void setScreenings(ArrayList<Screening> screenings) {
        this.screenings = screenings;
    }



}