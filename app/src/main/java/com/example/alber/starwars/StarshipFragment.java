package com.example.alber.starwars;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StarshipFragment extends Fragment {

    private RecyclerView recyclerView;
    private String next_page;
    private RecycAdapter rrcAdapter;
    private ArrayList<Starship> starshipArrayList;
    private RestInterface restInterface;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentstarship,container,false);

        recyclerView = view.findViewById(R.id.starshiprcrview);
        starshipArrayList = new ArrayList<Starship>();
        restInterface = ApiClient.getRetrofit().create(RestInterface.class);
        Call<StarshipResult> call = restInterface.getStarship();
        call.enqueue(new Callback<StarshipResult>() {
            @Override
            public void onResponse(Call<StarshipResult> call, Response<StarshipResult> response) {
                StarshipResult starshipResult = response.body();
                for(int i=0;i<starshipResult.results.size();i++){
                    starshipArrayList.add(starshipResult.results.get(i));
                    rrcAdapter.notifyDataSetChanged();
                }
                next_page = starshipResult.next;
                next_page = next_page.replace("https://swapi.co/api/","");
            }
            @Override
            public void onFailure(Call<StarshipResult> call, Throwable t) {

            }
        });
        rrcAdapter = new RecycAdapter(getContext(),starshipArrayList);
        recyclerView.setAdapter(rrcAdapter);
        final LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);




        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(llm.findLastCompletelyVisibleItemPosition() == starshipArrayList.size() -1){
                    //bottom of list!
                    LoadMore();
                }
            }
        });


        return view;
    }
    private void LoadMore(){
        Call<StarshipResult> call = restInterface.getStarshipMore(next_page);
        call.enqueue(new Callback<StarshipResult>() {
            @Override
            public void onResponse(Call<StarshipResult> call, Response<StarshipResult> response) {
                try {
                    StarshipResult starshipResult = response.body();
                    for(int i=0;i<starshipResult.results.size();i++){
                        starshipArrayList.add(starshipResult.results.get(i));
                        rrcAdapter.notifyDataSetChanged();
                    }
                    next_page = starshipResult.next;
                    next_page = next_page.replace("https://swapi.co/api/","");
                }catch (Exception e){

                }

            }
            @Override
            public void onFailure(Call<StarshipResult> call, Throwable t) {

            }
        });
    }

}

