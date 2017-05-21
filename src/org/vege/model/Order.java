package org.vege.model;

import java.sql.Timestamp;

public class Order {
    private Long id;
    private Long foodid;
    private Long userid;
    private Long foodnumber;
    private Long sellerid;
    private java.sql.Timestamp create_date;
    private String getfoodtime;
    private Long isfinish;
    private String foodcode;

    public Order(Long id, Long foodid, Long userid, Long foodnumber, Long sellerid, Timestamp create_date, String getfoodtime, Long isfinish, String foodcode) {
        this.id = id;
        this.foodid = foodid;
        this.userid = userid;
        this.foodnumber = foodnumber;
        this.sellerid = sellerid;
        this.create_date = create_date;
        this.getfoodtime = getfoodtime;
        this.isfinish = isfinish;
        this.foodcode = foodcode;
    }

    public Order() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFoodid() {
        return foodid;
    }

    public void setFoodid(Long foodid) {
        this.foodid = foodid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getFoodnumber() {
        return foodnumber;
    }

    public void setFoodnumber(Long foodnumber) {
        this.foodnumber = foodnumber;
    }

    public Long getSellerid() {
        return sellerid;
    }

    public void setSellerid(Long sellerid) {
        this.sellerid = sellerid;
    }

    public java.sql.Timestamp getCreate_date() {
        return create_date;
    }

    public void setCreate_date(java.sql.Timestamp create_date) {
        this.create_date = create_date;
    }

    public String getGetfoodtime() {
        return getfoodtime;
    }

    public void setGetfoodtime(String getfoodtime) {
        this.getfoodtime = getfoodtime;
    }

    public Long getIsfinish() {
        return isfinish;
    }

    public void setIsfinish(Long isfinish) {
        this.isfinish = isfinish;
    }

    public String getFoodcode() {
        return foodcode;
    }

    public void setFoodcode(String foodcode) {
        this.foodcode = foodcode;
    }
}
