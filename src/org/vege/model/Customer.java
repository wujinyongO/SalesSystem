package org.vege.model;

import java.sql.Timestamp;

public class Customer {

    private Long id;
    private String name;
    private String password;
    private String phonenumber;
    private String imageurl;
    private String address;
    private Long visit_made;
    private java.sql.Timestamp last_visit_time;

    public Customer() {
    }

    public Customer(Long id, String name, String password, String phonenumber, String imageurl, String address, Long visit_made, Timestamp last_visit_time) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phonenumber = phonenumber;
        this.imageurl = imageurl;
        this.address = address;
        this.visit_made = visit_made;
        this.last_visit_time = last_visit_time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getVisit_made() {
        return visit_made;
    }

    public void setVisit_made(Long visit_made) {
        this.visit_made = visit_made;
    }

    public java.sql.Timestamp getLast_visit_time() {
        return last_visit_time;
    }

    public void setLast_visit_time(java.sql.Timestamp last_visit_time) {
        this.last_visit_time = last_visit_time;
    }
}
