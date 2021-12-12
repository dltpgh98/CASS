package com.cookandroid.a0929.Adapter;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.cookandroid.a0929.Log_sign_up;

public class MemberAdapter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        int user_code = intent.getIntExtra("user_code", 0);
        int group_code = intent.getIntExtra("group_code", 0);
        System.out.println("어댑터"+user_code+ " "+ group_code);

        Intent putintent = new Intent(MemberAdapter.this, Log_sign_up.class);
        putintent.putExtra("user_code", user_code);
        putintent.putExtra("group_code", group_code);
        startActivity(putintent);

    }

}
