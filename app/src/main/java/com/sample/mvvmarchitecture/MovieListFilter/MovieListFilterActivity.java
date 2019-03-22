package com.sample.mvvmarchitecture.MovieListFilter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.sample.mvvmarchitecture.AppViewModel;
import com.sample.mvvmarchitecture.Utilities.Constants;
import com.sample.mvvmarchitecture.Genres.MovieRecycler_Adapter;
import com.sample.mvvmarchitecture.Movies.ResultsItem;
import com.sample.mvvmarchitecture.R;
import com.sample.mvvmarchitecture.databinding.ActivityMovieListFilterBinding;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieListFilterActivity extends AppCompatActivity {

    ActivityMovieListFilterBinding activityMovieListFilterBinding;
    private AppViewModel viewModel;
    String GenreID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMovieListFilterBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_list_filter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("GenreName"));

        //recycler view for items
        activityMovieListFilterBinding.moviesRecycler.setHasFixedSize(true);
        activityMovieListFilterBinding.moviesRecycler.setNestedScrollingEnabled(false);

        //LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.isAutoMeasureEnabled();
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        activityMovieListFilterBinding.moviesRecycler.setLayoutManager(gridLayoutManager);

        GenreID = getIntent().getStringExtra("GenreID");

        //get viewModel using ViewModelProviders and then tech data
        viewModel = ViewModelProviders.of(this).get(AppViewModel.class);

        String API_Key = Constants.API_Key;
        Map<String, String> params = new HashMap<String, String>();
        params.put("api_key",API_Key);
        params.put("with_genres", GenreID);

        setViewModel(params);
    }

    private void setViewModel(Map<String, String> params)
    {
        viewModel.getMoviePOJOLiveData(this,params).observe((LifecycleOwner) this, genre -> {
            List<ResultsItem> results = new ArrayList<ResultsItem>();

            if(!viewModel.getMoviePOJOLiveData(this,params).getValue().getResults().isEmpty())
            {
                int size = viewModel.getMoviePOJOLiveData(this,params).getValue().getResults().size();
                results.addAll(viewModel.getMoviePOJOLiveData(this,params).getValue().getResults().subList(0,size));
            }

            MovieRecycler_Adapter adapter = new MovieRecycler_Adapter(results, this,true);
            activityMovieListFilterBinding.moviesRecycler.setAdapter(adapter);

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_filter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        else if (id == R.id.most_popular) {
            getSupportActionBar().setTitle("Most Popular Movies");
            viewModel.clearLiveData();
            Log.i("Clicked","most_popular");
            String API_Key = Constants.API_Key;
            Map<String, String> params = new HashMap<String, String>();
            params.put("api_key",API_Key);
            params.put("with_genres", GenreID);
            params.put("sort_by", "popularity.asc");

            setViewModel(params);
        }
        else if (id == R.id.highest_rated) {
            getSupportActionBar().setTitle("Highest Rated Movies");
            viewModel.clearLiveData();
            Log.i("Clicked","highest_rated");
            String API_Key = Constants.API_Key;
            Map<String, String> params = new HashMap<String, String>();
            params.put("api_key",API_Key);
            params.put("with_genres", GenreID);
            params.put("sort_by", "vote_average.asc");

            setViewModel(params);
        }
        return super.onOptionsItemSelected(item);
    }
}
