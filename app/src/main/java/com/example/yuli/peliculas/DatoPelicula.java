package com.example.yuli.peliculas;

/**
 * Created by fundacion cuervo on 18/05/2018.
 */

public class DatoPelicula {

    private  int id;
    private String name;
    private  int idDrawable;
    private  String pathImg;
    private  String plot;
    private  String comments;
    private  double rating;

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

    public int getIdDrawable() {
        return idDrawable;
    }

    public void setIdDrawable(int idDrawable) {
        this.idDrawable = idDrawable;
    }

    public String getPathImg() {
        return pathImg;
    }

    public void setPathImg(String pathImg) {
        this.pathImg = pathImg;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
    public boolean checkImage() {
        if (getPathImg().equals("none"))
        {
            setIdDrawable(R.drawable.silencio);
            return true;
        } else
            return false;
        }
    }

