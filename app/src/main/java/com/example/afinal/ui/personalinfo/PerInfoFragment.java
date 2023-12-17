package com.example.afinal.ui.personalinfo;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.afinal.R;
import com.example.afinal.databinding.FragmentHomeBinding;

public class PerInfoFragment extends Fragment {

    private FragmentHomeBinding binding;

    private TextView textViewname,textViewcourse,textViewstudentno;
    private ImageView imageViewprofile;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PersonalViewModel homeViewModel =
                new ViewModelProvider(this).get(PersonalViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        View rooting = binding.getRoot();

        textViewname = rooting.findViewById(R.id.txtname);
        textViewcourse= rooting.findViewById(R.id.txtviewCousesec);
        textViewstudentno = rooting.findViewById(R.id.txtstudentno);
        imageViewprofile = rooting.findViewById(R.id.imgvprofile);

        shadowing();





        return rooting;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private  void shadowing(){
        textViewcourse.setShadowLayer(5,20,20, Color.parseColor("#808080"));
        textViewname.setShadowLayer(5,20,20, Color.parseColor("#808080"));
        textViewstudentno.setShadowLayer(5,20,20, Color.parseColor("#808080"));
    }
    private void image(){
        imageViewprofile.setImageResource(R.drawable.profile);
    }
}