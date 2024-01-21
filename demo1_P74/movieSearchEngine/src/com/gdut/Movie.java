package com.gdut;

public class Movie {
    private int id;
    private String title;
    private String type;
    private double score;
    private String director;
    private String actors;
    private int length;
    private double price;

    public Movie() {
    }

    public Movie(int id, String title, String type, double score, String director, String actors, int length, double price) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.score = score;
        this.director = director;
        this.actors = actors;
        this.length = length;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
