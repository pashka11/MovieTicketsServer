package org.yuval.objects;

/**
 * Created by Yuval on 13-Mar-17.
 * theater object
 */
public class Theater {

    private String name, location,imageLink;
    private int rows, columns, id;

    public Theater() {
    }

    public Theater(int id, String name, int rows, int columns, String location,String imageLink) {
        super();
        this.imageLink=imageLink;
        this.id = id;
        this.location = location;
        this.name = name;
        this.rows = rows;
        this.columns = columns;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }
}
