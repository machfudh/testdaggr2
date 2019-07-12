package com.machfudh.testdagger2.application;

import android.app.Application;

import com.machfudh.testdagger2.di.ApiComponent;
import com.machfudh.testdagger2.di.ApiModule;
import com.machfudh.testdagger2.di.DaggerApiComponent;
import com.machfudh.testdagger2.di.DaggerNetworkComponent;
import com.machfudh.testdagger2.di.NetworkComponent;
import com.machfudh.testdagger2.di.NetworkModule;
import com.machfudh.testdagger2.models.Constant;

public class MovieApplication extends Application {

    private ApiComponent mApiComponent;

    @Override
    public void onCreate() {
        resolveDependency();
        super.onCreate();
    }

    private void resolveDependency() {
         mApiComponent = DaggerApiComponent.builder()
                .networkComponent(getNetworkComponen())
                .build();

    }

    private NetworkComponent getNetworkComponen() {
        return DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule(Constant.BASE_URL))
                .build();
    }

    public ApiComponent getApiComponent() {
        return mApiComponent;
    }
}
