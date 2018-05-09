package com.uhcl.bmo.assignment5;

/**
 * Created by BMo on 4/17/2018.
 */

public class Book {
    String title;
    String author;
    String subject;
    String due;

    public void setTitle(String newTitle){
        this.title = newTitle;
    }

    public void setAuthor(String newAuthor){
        this.author = newAuthor;
    }

    public void setSubject(String newSubject){
        this.subject = newSubject;
    }

    public void setDue(String newDue){
        this.due = newDue;
    }

    public String getTitle(){
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getSubject() {
        return this.subject;
    }

    public String getDue() {
        return this.due;
    }
}
