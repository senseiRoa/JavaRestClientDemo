/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roa.test.client;

/**
 *
 * @author SenseiRoa
 */
public class Item {
    private String date;
    private String title;
    private String extra;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "Item{" + "date=" + date + ", title=" + title + ", extra=" + extra + '}';
    }
    
}
