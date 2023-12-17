package com.example.afinal.ui.module5;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.afinal.ui.module5.recyclerview;
import  com.example.afinal.ui.module5.recyleview;
import com.example.afinal.R;

import com.example.afinal.databinding. FragmentAssessmentmodule5Binding;

import java.util.ArrayList;

import yuku.ambilwarna.AmbilWarnaDialog;

public class Module5Fragment extends Fragment {

    private FragmentAssessmentmodule5Binding binding;
    RecyclerView holder;
    private Button colorchangebg,colorchagetxt;
    int standardcolor;
    private ConstraintLayout constraintLayoutlayout;
    ArrayList<recyleview>  arrayrecle;
    private SharedPreferences sharedPreferences;

     TextView textViewTitle,textViewMision;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Module5ViewModel galleryViewModel =
                new ViewModelProvider(this).get(Module5ViewModel.class);

        binding = FragmentAssessmentmodule5Binding.inflate(inflater, container, false);
        View root = binding.getRoot();
       arrayrecle = populateData();


       colorchangebg = root.findViewById(R.id.changebg);
       colorchagetxt = root.findViewById(R.id.chngecolortxt);
       textViewTitle = root.findViewById(R.id.name);
       textViewMision = root.findViewById(R.id.section) ;
       constraintLayoutlayout = binding.layout;


        Drawable bgDrawable = root.getBackground();
        if (bgDrawable instanceof ColorDrawable) {
            // Cast to ColorDrawable
            ColorDrawable colorDrawable = (ColorDrawable) bgDrawable;

            // Get the color as an integer
            standardcolor = colorDrawable.getColor();

            // Now 'backgroundColor' holds the integer representation of the background color
        }

        colorchangebg.setOnClickListener(v -> {
            OpenColorPicker("BGColor");
        });
        colorchagetxt.setOnClickListener(v -> {
            OpenColorPicker("TextColor");
        });

        holder = binding.rvmodule5;

        setAdapter();
        int textColor = textViewTitle.getCurrentTextColor();
        sharedPreferences = getContext().getSharedPreferences("", Context.MODE_PRIVATE);
        if(loadColor() != standardcolor){
            constraintLayoutlayout.setBackgroundColor(loadColor());
        }
        if(loadTextColor() != textColor){
            textViewTitle.setTextColor(loadTextColor());
            textViewMision.setTextColor(loadTextColor());
        }


        return root;
    }
    private void setAdapter() {
        recyclerview adapter = new recyclerview(arrayrecle);
        RecyclerView.LayoutManager mng = new LinearLayoutManager(holder.getContext());
        holder.setLayoutManager(mng);
        holder.setItemAnimator(new DefaultItemAnimator());
        holder.setAdapter(adapter);
    }
    private ArrayList<recyleview> populateData(){
        ArrayList<recyleview> dataholder = new ArrayList<>();

        dataholder.add(new recyleview(" Laguna University Vision",
                "Laguna University shall be a " +
                "socially responsive educational institution of choice providing holistically " +
                "developed individuals in the Asia-Pacific Region."));
        dataholder.add(new recyleview("Laguna University Mission", "Laguna University is committed" +
                " to produce academically prepared and technically skilled individuals who " +
                "are socially and morally upright."));
        return  dataholder;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void OpenColorPicker(String clrcpickeroption){
        AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog(getContext(), standardcolor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                standardcolor = color;
                if(clrcpickeroption.equals("BGColor")){
                   constraintLayoutlayout.setBackgroundColor(standardcolor);
                }
                else if(clrcpickeroption.equals("TextColor")){
                    textViewTitle.setTextColor(standardcolor);
                    textViewMision.setTextColor(standardcolor);
                }
                storeColor(standardcolor, clrcpickeroption);
            }
        });
        ambilWarnaDialog.show();
    }
    private void storeColor(int color, String Category){
        sharedPreferences = requireContext().getSharedPreferences("Final", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Category, color);
        editor.commit();
    }
    private  int loadColor(){
        SharedPreferences preferences = requireContext().getSharedPreferences("Final",Context.MODE_PRIVATE);
        int selectedColor =  preferences.getInt("BGColor", R.color.white);
        return  selectedColor;
    }
    private  int loadTextColor(){
        SharedPreferences preferences = requireContext().getSharedPreferences("Final",Context.MODE_PRIVATE);
        int selectedColor =  preferences.getInt("TextColor", R.color.white);
        return  selectedColor;
    }
}
