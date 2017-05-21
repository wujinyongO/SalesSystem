package org.vege.model;

/**
 * Created by rustbell on 5/20/17.
 */

public class Seller {
    private Long id;
    private String phonenumber;
    private String windowimg;
    private String password;
    private Long state;
    private String sellername;
    private String windowname;


    public Seller() {
    }

    public Seller(Long id, String phonenumber, String windowimg, String password, Long state, String sellername, String windowname) {

        this.id = id;
        this.phonenumber = phonenumber;
        this.windowimg = windowimg;
        this.password = password;
        this.state = state;
        this.sellername = sellername;
        this.windowname = windowname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getWindowimg() {
        return windowimg;
    }

    public void setWindowimg(String windowimg) {
        this.windowimg = windowimg;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public String getSellername() {
        return sellername;
    }

    public void setSellername(String sellername) {
        this.sellername = sellername;
    }

    public String getWindowname() {
        return windowname;
    }

    public void setWindowname(String windowname) {
        this.windowname = windowname;
    }
}
