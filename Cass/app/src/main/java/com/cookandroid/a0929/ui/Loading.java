package com.cookandroid.a0929.ui;


import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.cookandroid.a0929.Log_in;
import com.cookandroid.a0929.R;


public class Loading extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);
        Loadingstart();
    }
    private void Loadingstart(){
        Handler handler=new Handler();

        handler.postDelayed(new Runnable(){
            public void run(){
                Intent intent=new Intent(getApplicationContext(), Log_in.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}
