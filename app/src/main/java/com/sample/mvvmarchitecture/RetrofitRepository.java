package com.sample.mvvmarchitecture;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;

import java.util.Map;
import java.util.concurrent.Executors;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.sample.mvvmarchitecture.Genres.GenresPOJO;
import com.sample.mvvmarchitecture.MovieDetail.MovieDetailPOJO;
import com.sample.mvvmarchitecture.Movies.MoviePOJO;
import com.sample.mvvmarchitecture.Utilities.CommonRealmPOJO;
import com.sample.mvvmarchitecture.Utilities.Constants;
import com.sample.mvvmarchitecture.Utilities.Utils;
import io.realm.Realm;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRepository {

    static {
        System.loadLibrary("keys");
    }

    public static native String getBaseURL();
    public static final String BASE_URL = getBaseURL();
    //Todo Movie DB API Key
    String API_Key = Constants.API_Key;
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofitClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    //execute call back com background thread
                    .callbackExecutor(Executors.newSingleThreadExecutor())
                    .build();
        }
        return retrofit;
    }


    public LiveData<GenresPOJO> getGenresData(Activity activity) {
        Realm realm = Realm.getDefaultInstance();
        final MutableLiveData<GenresPOJO> coupon = new MutableLiveData<GenresPOJO>();

        if(Utils.isNetworkAvailable(activity))
        {
            getRetrofitClient().create(Webservice.class).getGenres(API_Key,"en-US").enqueue(new Callback<GenresPOJO>() {
                @Override
                public void onResponse(Call<GenresPOJO> call, Response<GenresPOJO> response) {

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            GenresPOJO cpn = response.body();
                            coupon.setValue(cpn);

                            if(realm != null) {
                                if (!realm.isClosed()) {
                                    realm.beginTransaction();
                                    CommonRealmPOJO commonRealmPOJO = realm.createObject(CommonRealmPOJO.class);
                                    Gson gson = new Gson();
                                    String stringValue = gson.toJson(response.body());
                                    commonRealmPOJO.setData(stringValue);
                                    commonRealmPOJO.setJSON("getAllGenres");
                                    realm.commitTransaction();
                                    Log.i("Realm","dataSaved");
                                    realm.close();
                                }
                            }

                        }
                    });
                }

                @Override
                public void onFailure(Call<GenresPOJO> call, Throwable t) {
                    Log.e("", "Error Getting TOP COUPON Data Retrofit");
                }
            });
        }
        else
        {
            try
            {
                CommonRealmPOJO result = realm.where(CommonRealmPOJO.class).equalTo("JSON", "getAllGenres").findAll().last();

                if(result != null)
                {
                    if(result.getJSON().equalsIgnoreCase("getAllGenres"))
                    {
                        Gson gson = new Gson();
                        GenresPOJO cpn = gson.fromJson(result.getData(), GenresPOJO.class);
                        coupon.setValue(cpn);
                    }
                }
                realm.close();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return coupon;
    }

    public LiveData<MoviePOJO> getMoviesData(Activity activity,Map<String, String> params) {
        Realm realm = Realm.getDefaultInstance();
        final MutableLiveData<MoviePOJO> coupon = new MutableLiveData<MoviePOJO>();

        if(Utils.isNetworkAvailable(activity))
        {
            getRetrofitClient().create(Webservice.class).getMovies(params).enqueue(new Callback<MoviePOJO>() {
                @Override
                public void onResponse(Call<MoviePOJO> call, Response<MoviePOJO> response) {

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            MoviePOJO cpn = response.body();
                            coupon.setValue(cpn);

                            if(realm != null) {
                                if (!realm.isClosed()) {
                                    realm.beginTransaction();
                                    CommonRealmPOJO commonRealmPOJO = realm.createObject(CommonRealmPOJO.class);
                                    Gson gson = new Gson();
                                    String stringValue = gson.toJson(response.body());
                                    commonRealmPOJO.setData(stringValue);
                                    commonRealmPOJO.setJSON(params.get("with_genres"));
                                    realm.commitTransaction();
                                    Log.i("Realm","dataSaved");
                                    realm.close();
                                }
                            }
                        }
                    });
                }

                @Override
                public void onFailure(Call<MoviePOJO> call, Throwable t) {
                    Log.e("", "Error Getting TOP COUPON Data Retrofit");
                }
            });
        }
        else
        {
            try
            {
                CommonRealmPOJO result = realm.where(CommonRealmPOJO.class).equalTo("JSON", params.get("with_genres")).findAll().last();

                if(result != null)
                {
                    if(result.getJSON().equalsIgnoreCase(params.get("with_genres")))
                    {
                        Gson gson = new Gson();
                        MoviePOJO cpn = gson.fromJson(result.getData(), MoviePOJO.class);
                        coupon.setValue(cpn);
                    }
                }
                realm.close();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return coupon;
    }

    public LiveData<MovieDetailPOJO> getMovieDetails(String movideID,Activity activity) {

        Realm realm = Realm.getDefaultInstance();
        final MutableLiveData<MovieDetailPOJO> coupon = new MutableLiveData<MovieDetailPOJO>();

        if(Utils.isNetworkAvailable(activity))
        {
                getRetrofitClient().create(Webservice.class).getMovieDetail(movideID,API_Key,"en-US","videos").enqueue(new Callback<MovieDetailPOJO>() {
                    @Override
                    public void onResponse(Call<MovieDetailPOJO> call, Response<MovieDetailPOJO> response) {

                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                MovieDetailPOJO cpn = response.body();
                                coupon.setValue(cpn);

                                if(realm != null) {
                                    if (!realm.isClosed()) {
                                        realm.beginTransaction();
                                        CommonRealmPOJO commonRealmPOJO = realm.createObject(CommonRealmPOJO.class);
                                        Gson gson = new Gson();
                                        String stringValue = gson.toJson(response.body());
                                        commonRealmPOJO.setData(stringValue);
                                        commonRealmPOJO.setJSON(movideID);
                                        realm.commitTransaction();
                                        Log.i("Realm","dataSaved");
                                        realm.close();
                                    }
                                }
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<MovieDetailPOJO> call, Throwable t) {
                        Log.e("", "Error Getting TOP COUPON Data Retrofit");
                    }
                });
        }
        else
        {
            try
            {
                CommonRealmPOJO result = realm.where(CommonRealmPOJO.class).equalTo("JSON", movideID).findAll().last();

                if(result != null)
                {
                    if(result.getJSON().equalsIgnoreCase(movideID))
                    {
                        Gson gson = new Gson();
                        MovieDetailPOJO cpn = gson.fromJson(result.getData(), MovieDetailPOJO.class);
                        coupon.setValue(cpn);
                    }
                }
                realm.close();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return coupon;
    }

}
