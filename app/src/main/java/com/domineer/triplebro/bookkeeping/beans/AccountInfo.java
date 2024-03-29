package com.domineer.triplebro.bookkeeping.beans;

import java.io.Serializable;

public class AccountInfo implements Serializable {

    private int _id;
    private int accountTypeId;
    private String accountName;
    private String accountTime;
    private String accountContent;
    private String accountMoney;
    private int isCollection;
    private int userId;

    public AccountInfo() {
    }

    public AccountInfo(int _id, int accountTypeId, String accountName, String accountTime, String accountContent, String accountMoney, int isCollection, int userId) {
        this._id = _id;
        this.accountTypeId = accountTypeId;
        this.accountName = accountName;
        this.accountTime = accountTime;
        this.accountContent = accountContent;
        this.accountMoney = accountMoney;
        this.isCollection = isCollection;
        this.userId = userId;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(int accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountTime() {
        return accountTime;
    }

    public void setAccountTime(String accountTime) {
        this.accountTime = accountTime;
    }

    public String getAccountContent() {
        return accountContent;
    }

    public void setAccountContent(String accountContent) {
        this.accountContent = accountContent;
    }

    public String getAccountMoney() {
        return accountMoney;
    }

    public void setAccountMoney(String accountMoney) {
        this.accountMoney = accountMoney;
    }

    public int getIsCollection() {
        return isCollection;
    }

    public void setIsCollection(int isCollection) {
        this.isCollection = isCollection;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "AccountInfo{" +
                "_id=" + _id +
                ", accountTypeId=" + accountTypeId +
                ", accountName='" + accountName + '\'' +
                ", accountTime='" + accountTime + '\'' +
                ", accountContent='" + accountContent + '\'' +
                ", accountMoney='" + accountMoney + '\'' +
                ", isCollection=" + isCollection +
                ", userId=" + userId +
                '}';
    }
}
