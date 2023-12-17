package com.example.afinal.ui.module8;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.afinal.R;
import com.example.afinal.databinding.FragmentAssesstmentmodule8Binding;

import java.util.Random;


public class Module8Fragment extends Fragment {

    private FragmentAssesstmentmodule8Binding binding;

    private EditText guessEditText;
    private Button submitButton;

    private  Button retryButton;

    private Button startButton;
    private CountDownTimer timer;
    private TextView timerTextView,rulestextview,notifytextview;

    private int randomNumber;
    long timelimit ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Module8ViewModel slideshowViewModel =
                new ViewModelProvider(this).get(Module8ViewModel.class);
        binding = FragmentAssesstmentmodule8Binding.inflate(inflater, container, false);

        View rootview = binding.getRoot();

        // Initialize UI elements
        guessEditText = rootview.findViewById(R.id.guessEditText);
        submitButton = rootview.findViewById(R.id.guessButton);
        retryButton = rootview.findViewById(R.id.retryButton);
        timerTextView = rootview.findViewById(R.id.timertextview);
        startButton = rootview.findViewById(R.id.startgamebutton);
        rulestextview = rootview.findViewById(R.id.rulestext);
        notifytextview = rootview.findViewById(R.id.notifytextview);

        menuchanges();
        randomno();


        timer = new CountDownTimer(31000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                    timelimit = millisUntilFinished/1000;


                    timerTextView.setText(String.format("Time left: %ds",timelimit));
            }

            @Override
            public void onFinish() {
                submitButton.setEnabled(false);
                guessEditText.setEnabled(false);
                notifytextview.setVisibility(View.VISIBLE);
                showToast("Time's up!");
                timerTextView.setTextColor(Color.parseColor("#FF0000"));//color red
                if(timerTextView.getCurrentTextColor() == Color.parseColor("#FF0000")) {
                    notifytextview.setText("BETTER LUCK NEXT TIME");
                    notifytextview.setTextColor(Color.parseColor("#FF0000"));
                }
                timer.cancel();
            }
        };
        // Start Button
        startButton.setOnClickListener(v -> {
            showToast("GAME START");
            timer.start();
            colorblue();
            guessEditText.setVisibility(View.VISIBLE);
            retryButton.setVisibility(View.VISIBLE);
            submitButton.setVisibility(View.VISIBLE);
            startbuttonhide();

        });
        // Restart Button
        retryButton.setOnClickListener(v -> {
            randomno();
            colorblue();
            startTimer();
            submitButton.setEnabled(true);
            guessEditText.setEnabled(true);
            guessEditText.setText("");
            showToast("Restart The Game");

        });

        // Sbmit Button
        submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        checkGuess();
                    }

                });


            return rootview;

            }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void checkGuess() {
        String guessText = guessEditText.getText().toString();
        if(invalidinputnumber(guessText)) {


            if (!guessText.isEmpty()) {
                int userGuess = Integer.parseInt(guessText);


                if (userGuess == randomNumber) {

                    showToast("You Got It!");
                    if(timelimit<=15){

                        guessinghide();
                        notifytextview.setText("YOu GOT IT ON TIME");
                        notifytextview.setTextColor(Color.parseColor("#A9A9A9"));
                    }else if (timelimit >15 && timelimit <= 26){
                        guessinghide();
                        notifytextview.setText("WELLDONE");
                        notifytextview.setTextColor(Color.parseColor("#00FF00"));
                    } else if (timelimit >26 && timelimit <=30) {
                        guessinghide();
                        notifytextview.setText(("GREAT JOB"));
                        notifytextview.setTextColor(Color.parseColor("#00FF00"));
                    }
                    timer.cancel();
                    timerTextView.setTextColor(Color.parseColor("#00FF00")); //color green

                } else if (userGuess < randomNumber) {
                    notifytextview.setVisibility(View.VISIBLE);
                    notifytextview.setText(("TRY HIGHER"));
                    notifytextview.setTextColor(Color.parseColor("#FF0000"));//color red
                    showToast("Try Again: Try Higher");
                } else {
                    notifytextview.setVisibility(View.VISIBLE);
                    notifytextview.setTextColor(Color.parseColor("#FF0000"));//color red
                    notifytextview.setText(("TRY LOWER"));
                    showToast("Try Again: Try Lower");
                }
            } else {
                showToast("Please enter a guess");
            }
        }
        else {
            showToast("INVALID INPUT!");
        }
    }
    // changing menus design
    private void menuchanges(){
        timerTextView.setText("");
        rulestextview.setText("Guess The no. 1 to 100");
        startButton.setText("Start Game");
        retryButton.setText("Restart Game");
        retryButton.setVisibility(View.INVISIBLE);
        submitButton.setVisibility(View.INVISIBLE);
        guessEditText.setVisibility(View.INVISIBLE);
        notifytextview.setVisibility(View.INVISIBLE);
    }
    //  Creating Random numbers
    private void randomno() {
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;
    }

    // Creating A toast message

    private void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private  boolean invalidinputnumber(String input) {
        try {
            int numinput = Integer.parseInt(input);
            return input.length() <= 3 && numinput <= 100;
        } catch (NumberFormatException e) {
            return false;
        }

    }
    // too hide the start button
    private void startbuttonhide(){
        startButton.setVisibility(View.INVISIBLE);
    }
    // changing the color of text in blue
    private void colorblue(){
        timerTextView.setTextColor(Color.parseColor("#ADD8E6"));//color blue
    }

    // to start the timer and cancel if the timer is exists to prevent timeloop
    private void startTimer() {
        timer.cancel();
        timer.start();
    }

    private void guessinghide(){
        notifytextview.setVisibility(View.VISIBLE);
        submitButton.setEnabled(false);
        guessEditText.setEnabled(false);
    }
}
