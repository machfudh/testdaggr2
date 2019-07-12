package com.machfudh.testdagger2.services;

import com.machfudh.testdagger2.models.callbacks.CallbakMovie;

import retrofit2.http.GET;
import rx.Observable;

public interface MovieService {

    @GET("/?s=batman&apikey=3fd87aeb")
    Observable<CallbakMovie> getMovie();
}
