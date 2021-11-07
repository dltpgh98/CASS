package com.cookandroid.a0929;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Log_pw extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_pw);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Button btn = (Button) findViewById(R.id.log_pw_finish_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(find_pwd.this, MainActivity.class);
//                startActivity(intent);
                //화면에 알람 화면 띄우는 코드
                finish();
            }
        });
    };
}
