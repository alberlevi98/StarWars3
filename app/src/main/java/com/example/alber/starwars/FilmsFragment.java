package com.example.alber.starwars;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmsFragment extends Fragment {
    RecyclerView recyclerView;
    RestInterface restInterface;
    SharedPreferences sharedPreferences;
    RecycAdapter2 recycAdapter2;
    ArrayList<String> filmArrayList;
    Toolbar toolbar;
    ArrayList<Films> arrayList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentfilms,container,false);
        Bundle arguments = getArguments();
        filmArrayList = arguments.getStringArrayList("Films");



        arrayList = new ArrayList<>();


        recyclerView = view.findViewById(R.id.filmsrecyclerview);
        toolbar = view.findViewById(R.id.toolbar);
        ImageView backicon = view.findViewById(R.id.backicon);
        backicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentChange(new StarshipFragment());
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.drawable.backicon){
                    FragmentChange(new StarshipFragment());
                return true;
                }
                return true;
            }
        });
            ApiClient apiClient = new ApiClient();
        restInterface = apiClient.getRetrofit().create(RestInterface.class);

        for(int i=0;i<filmArrayList.size();i++){
            String link = filmArrayList.get(i);
            link = link.replaceAll("https://swapi.co/api/","");
            Call<Films> call = restInterface.getFilms(link);
            call.enqueue(new Callback<Films>() {
                @Override
                public void onResponse(Call<Films> call, Response<Films> response) {
                    Films films = response.body();
                    arrayList.add(films);
                    recycAdapter2.notifyDataSetChanged();


                }

                @Override
                public void onFailure(Call<Films> call, Throwable t) {

                }
            });
        }
        recycAdapter2 = new RecycAdapter2(getContext(),arrayList);
        recyclerView.setAdapter(recycAdapter2);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);


        return view;
    }

    private void FragmentChange(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment,fragment);
        ((GlobalValue) getActivity().getApplication()).setNumber(2);
        fragmentTransaction.addToBackStack("2");
        fragmentTransaction.commit();
    }
}
