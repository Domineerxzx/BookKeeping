package com.domineer.triplebro.bookkeeping.beans;

import java.io.Serializable;

/**
 * @author Domineer
 * @data 2019/6/15,13:13
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class CollectionInfo implements Serializable {

    private int _id;
    private int accountId;
    private int collectionUserId;

    public CollectionInfo() {
    }

    public CollectionInfo(int _id, int accountId, int collectionUserId) {
        this._id = _id;
        this.accountId = accountId;
        this.collectionUserId = collectionUserId;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getCollectionUserId() {
        return collectionUserId;
    }

    public void setCollectionUserId(int collectionUserId) {
        this.collectionUserId = collectionUserId;
    }

    @Override
    public String toString() {
        return "CollectionInfo{" +
                "_id=" + _id +
                ", accountId=" + accountId +
                ", collectionUserId=" + collectionUserId +
                '}';
    }
}
