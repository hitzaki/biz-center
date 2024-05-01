package com.git.hitzaki.base.exception;

/**
 * 中台通用异常
 * @author hitzaki
 */
public class BizCenterException extends RuntimeException {

    private String errMessage;

    public BizCenterException() {
        super();
    }

    public BizCenterException(String message) {
        super(message);
        this.errMessage = message;
    }

    public String getErrMessage(){
        return errMessage;
    }

    public static void cast(String errMessage){
        throw new BizCenterException(errMessage);
    }
    public static void cast(CommonError commonError){
        throw new BizCenterException(commonError.getErrMessage());
    }
}
