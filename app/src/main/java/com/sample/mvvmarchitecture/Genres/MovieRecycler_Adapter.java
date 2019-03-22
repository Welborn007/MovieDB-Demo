package com.sample.mvvmarchitecture.Genres;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import com.sample.mvvmarchitecture.MovieDetail.MovieDetailActivity;
import com.sample.mvvmarchitecture.Movies.ResultsItem;
import com.sample.mvvmarchitecture.R;

public class MovieRecycler_Adapter extends RecyclerView.Adapter<MovieRecycler_Adapter.RecyclerViewHolder>
{

    private ArrayList<ResultsItem> items;
    private List<ResultsItem> tourModelListReView;

    private Context ctx;
    boolean isGridbool;
    //public int width;

    public MovieRecycler_Adapter(List<ResultsItem> tourModelList_arg, Context context,boolean isGrid)
    {
        tourModelListReView = new ArrayList<>(tourModelList_arg);
        items = new ArrayList<>(tourModelList_arg);
        ctx = context;
        isGridbool = isGrid;
        //width = widthSize;
    }

    public ArrayList<ResultsItem> getDataSet(){
        return items;
    }

    public void setDataSet(ArrayList<ResultsItem> newDataSet){
        this.items = newDataSet;
        notifyDataSetChanged();
    }

    @Override
    public MovieRecycler_Adapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if(isGridbool)
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.filter_movie_row_layout,parent,false);
        }
        else
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_row_layout,parent,false);
        }

        MovieRecycler_Adapter.RecyclerViewHolder recyclerViewHolder = new MovieRecycler_Adapter.RecyclerViewHolder(view);

        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(final MovieRecycler_Adapter.RecyclerViewHolder holder, int position) {
        try {

            Glide.with(ctx).load("https://image.tmdb.org/t/p/w500" + tourModelListReView.get(position).getPosterPath()).into(holder.thumbnailImgV);
            holder.MovieTitleTv.setText(tourModelListReView.get(position).getTitle());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ctx, MovieDetailActivity.class);
                    intent.putExtra("movieID",String.valueOf(tourModelListReView.get(position).getId()));
                    ctx.startActivity(intent);
                }
            });

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {

        return tourModelListReView.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        ImageView thumbnailImgV;
        TextView MovieTitleTv;

        public RecyclerViewHolder(View view)
        {
            super(view);

            MovieTitleTv = (TextView) view.findViewById(R.id.MovieTitleTv);
            thumbnailImgV = (ImageView) view.findViewById(R.id.thumbnailImgV);
        }
    }

    public void animateTo(List<ResultsItem> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(List<ResultsItem> newModels) {
        for (int i = tourModelListReView.size() - 1; i >= 0; i--) {
            final ResultsItem model = tourModelListReView.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<ResultsItem> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final ResultsItem model = newModels.get(i);
            if (!tourModelListReView.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<ResultsItem> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final ResultsItem model = newModels.get(toPosition);
            final int fromPosition = tourModelListReView.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public ResultsItem removeItem(int position) {
        final ResultsItem model = tourModelListReView.remove(position);
        this.setDataSet((ArrayList<ResultsItem>) tourModelListReView);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, ResultsItem model) {
        tourModelListReView.add(position, model);
        this.setDataSet((ArrayList<ResultsItem>) tourModelListReView);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final ResultsItem model = tourModelListReView.remove(fromPosition);
        tourModelListReView.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }
}