package com.cookandroid.a0929;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FlingAnimation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            /*id pw 저장*/
        EditText et1 = (EditText)findViewById(R.id.login_id);
        String ett1;
        ett1 = et1.getText().toString();
        EditText et2 = (EditText)findViewById(R.id.login_pw);
        String ett2;
        ett2= et2.getText().toString();
        et2.getText().toString();
            /*-------------text button----------*/
        TextView tb1=findViewById(R.id.signup);
        TextView tb2=findViewById(R.id.findid);
        TextView tb3=findViewById(R.id.findpw);
        tb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,sign_up.class);
                startActivity(intent);
            }
        });
        tb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,find_id.class);
                startActivity(intent);
            }
        });
        tb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,find_pwd.class);
                startActivity(intent);
            }
        });
        Button btn = (Button) findViewById(R.id.btn_login);
        btn.setOnClickListener(new View.OnClickListener() {
            /*----------엑티비티연결--------------*/
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SettingsActivity.class);
                startActivity(intent);
            }
        });
    }
}