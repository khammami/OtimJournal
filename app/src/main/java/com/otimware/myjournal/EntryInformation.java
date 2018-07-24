package com.otimware.myjournal;

import java.sql.Time;
import java.util.Date;

/**
 * Created by OtimDiBossman on 7/1/2018.
 */

public class EntryInformation {
    public String Title,Thoughts;
    public String date;
//constructor to write pojo

    public EntryInformation(String title,String thoughts,String date) {
        Title = title;
        Thoughts=thoughts;
        this.date=date;


    }
    //constructor to get DB data


    public EntryInformation(){

    }
    public String getTitle(){
        return Title;
    }
    public String getThoughts(){
        return Thoughts;
    }
    public String getDate(){
        return date;
    }

}
