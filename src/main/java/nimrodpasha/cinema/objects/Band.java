package nimrodpasha.cinema.objects;

/**
 * Created by Yuval on 13-Mar-17.
 * Band object
 */
public class Band {

    private String name, info,imageLink;
    int id;

    public Band(){}

    public Band(int id, String name, String info,String imageLink) {
        super();
        this.imageLink=imageLink;
        this.id = id;
        this.name = name;
        this.info = info;
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

}
