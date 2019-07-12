package com.machfudh.testdagger2.models.callbacks;

import com.machfudh.testdagger2.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class CallbakMovie {

    public List<Movie> Search = new ArrayList<>();
    public String totalResults;
    public String Response;

    public List<Movie> getSearch() {
        return Search;
    }

    public void setSearch(List<Movie> search) {
        Search = search;
    }
}
