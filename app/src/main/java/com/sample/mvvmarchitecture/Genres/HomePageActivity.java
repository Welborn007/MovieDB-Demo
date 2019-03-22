package com.sample.mvvmarchitecture.Genres;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import com.sample.mvvmarchitecture.AppViewModel;
import com.sample.mvvmarchitecture.R;
import com.sample.mvvmarchitecture.databinding.ActivityHomepageBinding;

public class HomePageActivity extends AppCompatActivity {

    ActivityHomepageBinding activityHomepageBinding;
    private AppViewModel viewModel;
    private List<GenresItem> genres = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityHomepageBinding = DataBindingUtil.setContentView(this, R.layout.activity_homepage);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("Movie Genre");

        //setUpRecyclerView();

        //get viewModel using ViewModelProviders and then tech data
        viewModel = ViewModelProviders.of(this).get(AppViewModel.class);

        //livedata
        viewModel.getGenresData(this).observe(this, genre -> {

            genres = viewModel.getGenresData(HomePageActivity.this).getValue().getGenres();

            for (Fragment fragment:getSupportFragmentManager().getFragments()) {
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            }

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            for (GenresItem genresItem : genres) {
                transaction.add(R.id.genreView, GenreFragment.createInstance(genresItem));
            }
            transaction.commit();

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
