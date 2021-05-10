package com.example.appseriespelis.Objetos;

import com.google.firebase.database.GenericTypeIndicator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PeliculaSerie implements Serializable {
    public String color;
    public String nombrepeli;
    public String status;
    public  String tipopeli;
    public ArrayList<Integer> genre;
    public String overview;
    public String img;
    public int id;

    public PeliculaSerie(){

    }

    public PeliculaSerie(String color, String nombrepeli, String status, String tipopeli, ArrayList<Integer> genre, String overview, String img, int id) {
        this.color = color;
        this.nombrepeli = nombrepeli;
        this.status = status;
        this.tipopeli = tipopeli;
        this.genre = genre;
        this.overview = overview;
        this.img = img;
        this.id = id;
       // this.genre = getGenreFronNum(genre);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public ArrayList<Integer> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<Integer> genre) {
        this.genre = genre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNombrepeli() {
        return nombrepeli;
    }

    public void setNombrepeli(String nombrepeli) {
        this.nombrepeli = nombrepeli;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTipopeli() {
        return tipopeli;
    }

    public void setTipopeli(String tipopeli) {
        this.tipopeli = tipopeli;
    }


    public String getGenreFromNum(Integer num){

        Map<Integer,String> mapa= new HashMap<>();
        mapa.put(28,"Action");
        mapa.put(12,"Adventure");
        mapa.put(16,"Animation");
        mapa.put(35,"Comedy");
        mapa.put(80,"Crime");
        mapa.put(99,"Documentary");
        mapa.put(18,"Drama");
        mapa.put(10751,"Family");
        mapa.put(14,"Fantasy");
        mapa.put(36,"History");
        mapa.put(27,"Horror");
        mapa.put(10402,"Music");
        mapa.put(9648,"Mystery");
        mapa.put(10749,"Romance");
        mapa.put(878,"Science Fiction");
        mapa.put(10770,"TV Movie");
        mapa.put(53,"Thriller");
        mapa.put(10752,"War");
        mapa.put(37,"Western");
        return mapa.get(num);
    }


}
