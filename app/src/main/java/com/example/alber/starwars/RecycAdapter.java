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

public class RecycAdapter extends RecyclerView.Adapter<RecycAdapter.ViewHolder> {

    public int item_position=0;
    Context context;
    ArrayList<Starship> starshipArrayList;

    public RecycAdapter(Context context, ArrayList<Starship> starshipArrayList){
        this.context = context;
        this.starshipArrayList = starshipArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.starshiprcrview,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.SetData(starshipArrayList.get(position));
    }


    @Override
    public int getItemCount() {
        return starshipArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,model,manufacturer;
        public ViewHolder(final View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.starshipname);
            model = itemView.findViewById(R.id.model);
            manufacturer = itemView.findViewById(R.id.manufacturer);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item_position = getAdapterPosition();
                    ChangeFragment(new FilmsFragment(),v);
                }
            });
        }
        public void SetData(Starship starship){
            name.setText(starship.getName());
            model.setText(starship.getModel());
            manufacturer.setText(starship.getManufacturer());
        }
    }
    private void ChangeFragment(Fragment fragment,View view){
        Bundle b = new Bundle();
        ArrayList<String> filmarraylist = ConvertToArrayList();
        b.putStringArrayList("Films",filmarraylist);
        fragment.setArguments(b);
        FragmentTransaction fragmentTransaction = ((FragmentActivity) view.getContext()).getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment,fragment);

        fragmentTransaction.commit();
    }

    private ArrayList<String> ConvertToArrayList() {
        ArrayList<String> filmarraylist = new ArrayList<>();
        for(int i=0;i<starshipArrayList.get(item_position).getFilms().size();i++){
            filmarraylist.add(starshipArrayList.get(item_position).getFilms().get(i));
        }
        return filmarraylist;
    }
}
