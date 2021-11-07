package com.cookandroid.a0929;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Log_sign_up extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_sign);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Button btn = (Button) findViewById(R.id.log_sign_finish_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(find_pwd.this, MainActivity.class);
//                startActivity(intent);
                //판별했을때 true 인경우 값 php로 넘기고 종료
                finish();
            }
        });
    };
}
