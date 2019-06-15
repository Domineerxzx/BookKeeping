package com.domineer.triplebro.bookkeeping.exception;

/**
 * @author Domineer
 * @data 2019/6/16,3:55
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class LoginOrRegisterException extends Exception {

    public int errorCode;

    public String errorMassage;

    public LoginOrRegisterException() {
    }

    public LoginOrRegisterException(int errorCode, String errorMassage) {
        this.errorCode = errorCode;
        this.errorMassage = errorMassage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMassage() {
        return errorMassage;
    }

    public void setErrorMassage(String errorMassage) {
        this.errorMassage = errorMassage;
    }
}
