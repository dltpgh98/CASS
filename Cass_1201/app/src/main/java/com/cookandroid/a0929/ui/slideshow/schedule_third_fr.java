package com.cookandroid.a0929.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.cookandroid.a0929.R;

public class schedule_third_fr extends Fragment {

    private schedule_third_ViewModel_fr schedulethirdViewModelFr;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        schedulethirdViewModelFr =
                new ViewModelProvider(this).get(schedule_third_ViewModel_fr.class);
        View root = inflater.inflate(R.layout.schedule_third_fr, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        schedulethirdViewModelFr.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}