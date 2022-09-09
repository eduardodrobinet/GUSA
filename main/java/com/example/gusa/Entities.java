package com.example.gusa;

import java.util.Date;

public class Entities {
    private Integer id;
    private String ref;
    private String date;

    public Entities(Integer id, String ref, String date){
        this.id = id;
        this.ref = ref;
        this.date = date;
    }
    public Entities(){

    }
    public void setId(Integer id){
        this.id = id;
    }
    public String getRef(){
        return ref;
    }
    public void setRef(String ref){
        this.ref = ref;
    }
    public String getdate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }
}
