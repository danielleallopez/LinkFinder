package com.dleal.linkfinder.repository.responses;

/**
 * Created by Daniel Leal on 29/04/16.
 */
public abstract class BaseResponse {
    protected ErrorBundle errorBundle;

    public ErrorBundle getErrorBundle() {
        return errorBundle;
    }

    public void setErrorBundle(ErrorBundle errorBundle) {
        this.errorBundle = errorBundle;
    }
}
