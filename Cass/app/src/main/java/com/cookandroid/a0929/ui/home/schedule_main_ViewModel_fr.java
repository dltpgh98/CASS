package com.cookandroid.a0929.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class schedule_main_ViewModel_fr extends ViewModel {

    private MutableLiveData<String> mText;

    public schedule_main_ViewModel_fr() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}