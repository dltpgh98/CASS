package com.cookandroid.a0929.ui.slideshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class schedule_third_ViewModel_fr extends ViewModel {

    private MutableLiveData<String> mText;

    public schedule_third_ViewModel_fr() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}