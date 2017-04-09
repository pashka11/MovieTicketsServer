package org.yuval.objects;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Created by Yuval on 14-Mar-17.
 * show object
 */
public class Show {
    private String name,description,imageLink;
    private int bandId,showId;
    private ArrayList<ShowInstance>showInstances=new ArrayList<ShowInstance>();

    public Show() {
    }

    public Show(int showId, String name, String description, int bandId, ArrayList<ShowInstance> showInstances,String imageLink) {
        super();
        this.imageLink=imageLink;
        this.showId=showId;
        this.name = name;
        this.description = description;
        this.bandId = bandId;
        this.showInstances = showInstances;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBandId() {
        return bandId;
    }

    public void setBandId(int bandId) {
        this.bandId = bandId;
    }

    public ArrayList<ShowInstance> getShowInstances() {
        return showInstances;
    }

    public void setShowInstances(ArrayList<ShowInstance> showInstances) {
        this.showInstances = showInstances;
    }
}
