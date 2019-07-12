package com.machfudh.testdagger2.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.machfudh.testdagger2.R;
import com.machfudh.testdagger2.adapters.MovieAdapter;
import com.machfudh.testdagger2.application.MovieApplication;
import com.machfudh.testdagger2.base.MoviePresenter;
import com.machfudh.testdagger2.models.callbacks.CallbakMovie;
import com.machfudh.testdagger2.services.MovieService;
import com.machfudh.testdagger2.services.MovieViewInterface;

import javax.inject.Inject;

import rx.Observable;

public class MainActivity extends AppCompatActivity implements MovieViewInterface, MovieAdapter.MovieClickListener {

    @Inject
    MovieService service;

    private MoviePresenter presenter;

    private ProgressDialog dialog;

    private MovieAdapter adapter;

    private  RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((MovieApplication) getApplication())
                .getApiComponent()
                .inject(MainActivity.this);


        configViews();
        presenter = new MoviePresenter(MainActivity.this);
        presenter.onCreate();

    }

    private void configViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        recyclerView.setLayoutManager(new LinearLayoutManager( MainActivity.this, RecyclerView.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new MovieAdapter(this,getLayoutInflater());
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
        presenter.fecthMovie();
        dialog = new ProgressDialog(MainActivity.this);
        dialog.setIndeterminate(true);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setTitle("Downloads Dulu");
        dialog.setMessage("Mohon Di tunggu ...");
        dialog.show();

    }

    @Override
    public void onCompleted() {
        dialog.dismiss();
    }

    @Override
    public void onError(String message) {
        dialog.dismiss();
    }

    @Override
    public void onMovie(CallbakMovie callbakMovie) {
        System.out.println( " ======================= total  :"+ callbakMovie.totalResults);
        System.out.println( " ======================= hasil  : "+ callbakMovie.Search.get(1).getTitle());

        adapter.addMovie(callbakMovie.Search);

    }

    @Override
    public Observable<CallbakMovie> getMovie() {
        return service.getMovie();
    }

    @Override
    public void onClick(int position) {
        Toast.makeText(getApplicationContext(),"Position "+position,Toast.LENGTH_SHORT);
    }
}
