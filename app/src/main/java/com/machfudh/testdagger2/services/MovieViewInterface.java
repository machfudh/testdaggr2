package com.machfudh.testdagger2.services;

import com.machfudh.testdagger2.models.callbacks.CallbakMovie;

import rx.Observable;

public interface MovieViewInterface {

    void onCompleted();

    void onError(String message);

    void onMovie(CallbakMovie callbakMovie);

    Observable<CallbakMovie> getMovie();
}
