package com.example.appseriespelis.Objetos;

import java.io.Serializable;

public class ListaFavs implements Serializable {
    public int id ;


    public ListaFavs(int id) {
        this.id = id ;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
