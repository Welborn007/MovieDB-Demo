package com.sample.mvvmarchitecture.MovieDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import com.sample.mvvmarchitecture.AppViewModel;
import com.sample.mvvmarchitecture.R;
import com.sample.mvvmarchitecture.databinding.ActivityMovieDetailBinding;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.bumptech.glide.Glide;

public class MovieDetailActivity extends AppCompatActivity {

    ActivityMovieDetailBinding activityMovieDetailBinding;
    private AppViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMovieDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Movie Details");

        //setUpRecyclerView();

        //get viewModel using ViewModelProviders and then tech data
        viewModel = ViewModelProviders.of(this).get(AppViewModel.class);

        //livedata
        viewModel.getMovieDetailLiveData(getIntent().getStringExtra("movieID"),this).observe(this, genre -> {

            LiveData<MovieDetailPOJO> movieDetailPOJO = viewModel.getMovieDetailLiveData(getIntent().getStringExtra("movieID"),this);

            Glide.with(this).load("https://image.tmdb.org/t/p/w500" + movieDetailPOJO.getValue().getBackdropPath()).into(activityMovieDetailBinding.backdropPath);

            activityMovieDetailBinding.Title.setText(movieDetailPOJO.getValue().getTitle());
            activityMovieDetailBinding.tagLine.setText(movieDetailPOJO.getValue().getTagline());
            activityMovieDetailBinding.overview.setText(movieDetailPOJO.getValue().getOverview());
            activityMovieDetailBinding.rating.setText(String.valueOf(movieDetailPOJO.getValue().getVoteAverage()));
            activityMovieDetailBinding.status.setText(movieDetailPOJO.getValue().getStatus());
            activityMovieDetailBinding.releaseDate.setText(movieDetailPOJO.getValue().getReleaseDate());

            activityMovieDetailBinding.playYoutube.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + movieDetailPOJO.getValue().getVideos().getResults().get(0).getKey())));
                }
            });
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
