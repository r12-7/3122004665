package org;

public class ShortStringException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 5090031576453436845L;

    public ShortStringException() {
        super();
    }

    public ShortStringException(String message) {
        super(message);
    }

    public ShortStringException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShortStringException(Throwable cause) {
        super(cause);
    }

}


