package com.domineer.triplebro.bookkeeping.beans;

import java.io.Serializable;

/**
 * @author Domineer
 * @data 2019/6/15,12:54
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class AccountTypeInfo implements Serializable {

    private int _id;
    private int AccountTypeName;

    public AccountTypeInfo() {
    }

    public AccountTypeInfo(int _id, int accountTypeName) {
        this._id = _id;
        AccountTypeName = accountTypeName;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getAccountTypeName() {
        return AccountTypeName;
    }

    public void setAccountTypeName(int accountTypeName) {
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
