package com.rongcheng.erp.exception;

public class OrderOutNumberException extends RuntimeException{

    /**
     * 序列号
     */
    private static final long serialVersionUID = 7743937921386880378L;

    public OrderOutNumberException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public OrderOutNumberException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

    public OrderOutNumberException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public OrderOutNumberException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public OrderOutNumberException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    
}
