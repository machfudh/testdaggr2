package com.machfudh.testdagger2.base;

import com.machfudh.testdagger2.models.callbacks.CallbakMovie;
import com.machfudh.testdagger2.services.MovieViewInterface;

import rx.Observer;

public class MoviePresenter extends BasePresenter implements Observer<CallbakMovie> {

    private MovieViewInterface mViewInterface;

    public MoviePresenter(MovieViewInterface viewInterface) {
        mViewInterface = viewInterface;
    }

    @Override
    public void onCompleted() {
        mViewInterface.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        mViewInterface.onError(e.getMessage());
    }

    @Override
    public void onNext(CallbakMovie callbakMovie) {
        mViewInterface.onMovie(callbakMovie);
    }

    public void fecthMovie() {
        unSubscribeAll();
        subscribe(mViewInterface.getMovie(),MoviePresenter.this);
    }
}
