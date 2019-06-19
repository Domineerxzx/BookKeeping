package com.domineer.triplebro.bookkeeping.beans;

import java.io.Serializable;

public class AccountTypeInfo implements Serializable {

    private int _id;
    private String AccountTypeName;

    public AccountTypeInfo() {
    }

    public AccountTypeInfo(int _id, String accountTypeName) {
        this._id = _id;
        AccountTypeName = accountTypeName;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getAccountTypeName() {
        return AccountTypeName;
    }

    public void setAccountTypeName(String accountTypeName) {
        AccountTypeName = accountTypeName;
    }

    @Override
    public String toString() {
        return "AccountTypeInfo{" +
                "_id=" + _id +
                ", AccountTypeName=" + AccountTypeName +
                '}';
    }
}
