package com.machfudh.testdagger2.di;


import com.machfudh.testdagger2.services.MovieService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ApiModule {

    @Provides
    @CustomScope
    MovieService provMovieService(Retrofit retrofit){
        return  retrofit.create(MovieService.class);
    }

}
