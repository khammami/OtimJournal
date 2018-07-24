package com.otimware.myjournal;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by OtimDiBossman on 7/1/2018.
 */
@Entity(tableName = "journalEntry")
public class DB_entity {

    @PrimaryKey(autoGenerate = true)
     private  int id;
     private  String Title;
     private  String Notes;
     private String date;

     //constructors for entity
    @Ignore
    public DB_entity(String Title,String Notes,String date){
        this.Title=Title;
        this.Notes=Notes;
        this.date=date;
    }
    public DB_entity(int id,String Title,String Notes,String date){
        this.Title=Title;
        this.Notes=Notes;
        this.date=date;
        this.id=id;
    }
    //methods for operating on table
    public  int getId(){
        return id;
    }
    public void setId(int ID){
        id=ID;
    }
    public String getTitle(){
        return Title;
    }
    public void setNotes(String Notes){
        this.Notes=Notes;

    }
    public String getNotes(){
        return  Notes;
    }
    public  String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date=date;
    }

}
