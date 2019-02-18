package com.example.alber.starwars;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecycAdapter2 extends RecyclerView.Adapter<RecycAdapter2.ViewHolder> {
    LayoutInflater layoutInflater;
    ArrayList<Films> filmArrayList;
    public RecycAdapter2(Context context,ArrayList<Films> filmsArrayList){
        layoutInflater = LayoutInflater.from(context);
        this.filmArrayList = filmsArrayList;
    }
    @NonNull
    @Override
    public RecycAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.filmsrcrview,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycAdapter2.ViewHolder holder, int position) {
        holder.SetData(position);
    }

    @Override
    public int getItemCount() {
        return filmArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,episodeid,open_crawl;
        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            episodeid = itemView.findViewById(R.id.episodeid);
            open_crawl = itemView.findViewById(R.id.opening_crawl);
        }
        private void SetData(int position){
            title.setText(filmArrayList.get(position).getTitle());
            episodeid.setText(Integer.toString(filmArrayList.get(position).getEpisodeId()));
            open_crawl.setText(filmArrayList.get(position).getOpeningCrawl());
        }
    }
}
