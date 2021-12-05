package com.cookandroid.a0929.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class schedule_second_ViewModel_fr extends ViewModel {

    private MutableLiveData<String> mText;

    public schedule_second_ViewModel_fr() {
        mText = new MutableLiveData<>();
        mText.setValue("팀 스케줄");
    }

    public LiveData<String> getText() {
        return mText;
    }
}