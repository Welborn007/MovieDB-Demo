package com.sample.mvvmarchitecture;

import android.app.Activity;

import java.util.Map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.sample.mvvmarchitecture.Genres.GenresPOJO;
import com.sample.mvvmarchitecture.MovieDetail.MovieDetailPOJO;
import com.sample.mvvmarchitecture.Movies.MoviePOJO;

public class AppViewModel extends ViewModel {

    private LiveData<GenresPOJO> profile;
    private LiveData<MoviePOJO> moviePOJOLiveData;
    private LiveData<MovieDetailPOJO> movieDetailPOJOLiveData;
    private RetrofitRepository retrofitRepository = new RetrofitRepository();

    public LiveData<GenresPOJO> getGenresData(Activity activity) {
        if(profile == null){
            profile = retrofitRepository.getGenresData(activity);
        }
        return profile;
    }

    public LiveData<MoviePOJO> getMoviePOJOLiveData(Activity activity,Map<String, String> params) {
        if(moviePOJOLiveData == null){
            moviePOJOLiveData = retrofitRepository.getMoviesData(activity,params);
        }
        return moviePOJOLiveData;
    }

    public LiveData<MovieDetailPOJO> getMovieDetailLiveData(String movieID,Activity activity) {
        if(movieDetailPOJOLiveData == null){
            movieDetailPOJOLiveData = retrofitRepository.getMovieDetails(movieID,activity);
        }
        return movieDetailPOJOLiveData;
    }

    public void clearLiveData()
    {
        moviePOJOLiveData = null;
    }
}

