package com.example.afinal.ui.module5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.afinal.R;

import java.util.ArrayList;
public class recyclerview  extends RecyclerView.Adapter<recyclerview.Holder>{

    private ArrayList<recyleview> contentholder = new ArrayList<>();

    public recyclerview(ArrayList<recyleview> content){
        this.contentholder = content;
    }

    public class Holder extends RecyclerView.ViewHolder{

        public TextView titletxt;
        public TextView misiontxt;


        public Holder(final View view){
            super(view);

            misiontxt = view.findViewById(R.id.txtlumission);
            titletxt = view.findViewById(R.id.txtTitle);

        }
    }


    @NonNull
    @Override
    public recyclerview.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewtype){
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_module5,parent, false);
        return  new Holder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerview.Holder holder, int position){
        String titleposition = contentholder.get(position).getTitle();
        String misionposition = contentholder.get(position).getMission();


        holder.misiontxt.setText(misionposition);
        holder.titletxt.setText(titleposition);
    }

    @Override
    public int getItemCount(){
        return contentholder.size();
    }
}
