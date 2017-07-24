package nimrodpasha.cinema.objects;

/**
 * Created by Yuval on 13-Mar-17.
 * Band object
 */
public class Band {

    private String name, info,imageLink ;
    int id;
    private String director,actors, releasedate, genres,duration;


    public Band(){}

    public Band(int id, String name, String info,String imageLink,String director,
                String actors ,String releasedate,String genres, String duration) {
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


    public void  setDirector (String director ){  this.director = director;}
    public void  setActors(String actors) {this.actors =  actors;}
    public void  setReleasedate(String releasedate) {this.releasedate = releasedate;}
    public void  setGenres(String genres) {this.genres = genres;}
    public void  setDuration(String duration) {this.duration =  duration;}


}
