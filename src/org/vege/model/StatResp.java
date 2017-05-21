package org.vege.model;

/**
 * Created by rustbell on 5/20/17.
 */
public class StatResp {
    //Status: ok, error
    private String status;
    private String msg;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }


    public void setMsg(String msg) {
        this.msg = msg;
    }

    public StatResp() {
    }

    public StatResp(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
