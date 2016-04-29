package com.dleal.linkfinder.repository;

import com.dleal.linkfinder.repository.responses.BaseResponse;

/**
 * Created by Daniel Leal on 29/04/16.
 */
public class DataRepository {

    public interface Callback {
        void onResponseAvailable(BaseResponse response);
    }
}
