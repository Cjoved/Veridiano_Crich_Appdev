package com.example.afinal.ui.module8;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Module8ViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public Module8ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("THE GUESSING GAME");
    }

    public LiveData<String> getText() {
        return mText;
    }
}