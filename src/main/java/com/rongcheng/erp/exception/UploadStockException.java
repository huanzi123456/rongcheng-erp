package com.rongcheng.erp.exception;

public class UploadStockException extends RuntimeException {

    private static final long serialVersionUID = -7035365739111652863L;

    public UploadStockException() {
    }

    public UploadStockException(String message) {
        super(message);
    }

    public UploadStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public UploadStockException(Throwable cause) {
        super(cause);
    }

    public UploadStockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
