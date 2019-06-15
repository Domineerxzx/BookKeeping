package com.domineer.triplebro.bookkeeping.beans;

import java.io.Serializable;

/**
 * @author Domineer
 * @data 2019/6/15,12:47
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class UserInfo implements Serializable {

    private int _id;
    private String telephone;
    private String password;
    private String userHead;
    private String nickname;

    public UserInfo() {
    }

    public UserInfo(int _id, String telephone, String password, String userHead, String nickname) {
        this._id = _id;
        this.telephone = telephone;
        this.password = password;
        this.userHead = userHead;
        this.nickname = nickname;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "_id=" + _id +
                ", telephone='" + telephone + '\'' +
                ", password='" + password + '\'' +
                ", userHead='" + userHead + '\'' +
                '}';
    }
}
