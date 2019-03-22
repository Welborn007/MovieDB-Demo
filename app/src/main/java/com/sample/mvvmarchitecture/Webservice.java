package com.sample.mvvmarchitecture;

import java.util.Map;

import com.sample.mvvmarchitecture.Genres.GenresPOJO;
import com.sample.mvvmarchitecture.MovieDetail.MovieDetailPOJO;
import com.sample.mvvmarchitecture.Movies.MoviePOJO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface Webservice {

    /**
     * @GET declares an HTTP GET request
     * @Path("user") annotation on the userId parameter marks it as a
     * replacement for the {user} placeholder com the @GET path
     */

    @GET("genre/movie/list?")
    Call<GenresPOJO> getGenres(@Query("api_key") String apiKey,@Query("language") String language);

    @GET("discover/movie?")
    Call<MoviePOJO> getMovies(@QueryMap Map<String, String> queryMap);

    @GET("movie/{movieId}?")
    Call<MovieDetailPOJO> getMovieDetail(@Path("movieId") String movieId, @Query("api_key") String apiKey,@Query("language") String language,@Query("append_to_response") String append_to_response);
}
