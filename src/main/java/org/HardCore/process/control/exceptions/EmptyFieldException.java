package org.HardCore.process.control.exceptions;

public class EmptyFieldException extends Exception {
    private String reason = null;


    public EmptyFieldException( String reason ) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
