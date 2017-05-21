package org.vege.model;

/**
 * Created by rustbell on 5/20/17.
 */

public class Vege {
    private Long id;
    private String foodimg;
    private Double foodprice;
    private String foodname;
    private String pricetype;
    private Long sellerid;

    public Vege() {

    }

    public Vege(Long id, String foodimg, Double foodprice, String foodname, String pricetype, Long sellerid) {
        this.id = id;
        this.foodimg = foodimg;
        this.foodprice = foodprice;
        this.foodname = foodname;
        this.pricetype = pricetype;
        this.sellerid = sellerid;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFoodimg() {
        return foodimg;
    }

    public void setFoodimg(String foodimg) {
        this.foodimg = foodimg;
    }

    public Double getFoodprice() {
        return foodprice;
    }

    public void setFoodprice(Double foodprice) {
        this.foodprice = foodprice;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getPricetype() {
        return pricetype;
    }

    public void setPricetype(String pricetype) {
        this.pricetype = pricetype;
    }

    public Long getSellerid() {
        return sellerid;
    }

    public void setSellerid(Long sellerid) {
        this.sellerid = sellerid;
    }
}