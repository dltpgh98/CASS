package com.cookandroid.a0929;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class List_page extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_page);

        Button wp_btn = (Button) findViewById(R.id.lp_plus_btn);
        wp_btn.setOnClickListener(new View.OnClickListener() {
            /*----------엑티비티연결--------------*/
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(List_page.this,Write_page.class);
                startActivity(intent);
            }
        });
        Button back_btn = (Button) findViewById(R.id.lp_back_TextBtn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            /*----------엑티비티--------------*/
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
}
