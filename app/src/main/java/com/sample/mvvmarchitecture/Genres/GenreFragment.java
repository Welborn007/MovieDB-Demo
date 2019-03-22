package com.sample.mvvmarchitecture.Genres;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.sample.mvvmarchitecture.AppViewModel;
import com.sample.mvvmarchitecture.Utilities.Constants;
import com.sample.mvvmarchitecture.MovieListFilter.MovieListFilterActivity;
import com.sample.mvvmarchitecture.Movies.ResultsItem;
import com.sample.mvvmarchitecture.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GenreFragment extends Fragment {

    TextView genreName,showAll;
    private AppViewModel viewModel;
    RecyclerView moviesRecycler;

    public static GenreFragment createInstance(GenresItem genresItem)
    {
        GenreFragment fragment = new GenreFragment();
        Bundle args = new Bundle();
        args.putParcelable("GENRE",genresItem);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.fragment_genre, container, false);

        genreName = (TextView) V.findViewById(R.id.genreName);
        showAll = (TextView) V.findViewById(R.id.showAll);
        moviesRecycler = (RecyclerView) V.findViewById(R.id.moviesRecycler);

        //recycler view for items
        moviesRecycler.setHasFixedSize(true);
        moviesRecycler.setNestedScrollingEnabled(false);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        moviesRecycler.setLayoutManager(linearLayoutManager1);

        GenresItem genresItem = getArguments().getParcelable("GENRE");
        genreName.setText(genresItem.getName());

        //get viewModel using ViewModelProviders and then tech data
        viewModel = ViewModelProviders.of(this).get(AppViewModel.class);

        String API_Key = Constants.API_Key;
        Map<String, String> params = new HashMap<String, String>();
        params.put("api_key",API_Key);
        params.put("with_genres", String.valueOf(genresItem.getId()));

        viewModel.getMoviePOJOLiveData(getActivity(),params).observe((LifecycleOwner) getActivity(), genre -> {
            List<ResultsItem> results = new ArrayList<ResultsItem>();

            if(viewModel.getMoviePOJOLiveData(getActivity(),params).getValue().getResults() != null)
            {
                if(!viewModel.getMoviePOJOLiveData(getActivity(),params).getValue().getResults().isEmpty())
                {
                    int size = viewModel.getMoviePOJOLiveData(getActivity(),params).getValue().getResults().size();
                    if(viewModel.getMoviePOJOLiveData(getActivity(),params).getValue().getResults().size() > 10)
                    {
                        results.addAll(viewModel.getMoviePOJOLiveData(getActivity(),params).getValue().getResults().subList(0,10));
                    }
                    else
                    {
                        results.addAll(viewModel.getMoviePOJOLiveData(getActivity(),params).getValue().getResults().subList(0,size));
                    }
                }


                MovieRecycler_Adapter adapter = new MovieRecycler_Adapter(results, getActivity(),false);
                moviesRecycler.setAdapter(adapter);
            }

        });

        showAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MovieListFilterActivity.class);
                intent.putExtra("GenreID",String.valueOf(genresItem.getId()));
                intent.putExtra("GenreName",String.valueOf(genresItem.getName()));
                startActivity(intent);
            }
        });

        return V;
    }

}
