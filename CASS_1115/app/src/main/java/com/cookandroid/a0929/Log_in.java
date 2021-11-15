package com.cookandroid.a0929;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.cookandroid.a0929.DB.LoginRequest;
import com.cookandroid.a0929.Menu_MainActivity;
import com.cookandroid.a0929.R;

import org.json.JSONException;
import org.json.JSONObject;

public class Log_in extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);



        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        /*id pw 저장*/
        EditText et1 = (EditText)findViewById(R.id.log_in_id_et);
        EditText et2 = (EditText)findViewById(R.id.log_in_pw_et);
        TextView tv1 = (TextView) findViewById(R.id.log_pw_err_tv);

            /*-------------text button activity연결----------*/
        TextView tb1=findViewById(R.id.log_in_sign_tv);
        TextView tb2=findViewById(R.id.log_in_id_tv);
        TextView tb3=findViewById(R.id.log_in_pw_tv);
        tb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Log_in.this, Log_sign_up.class);
                startActivity(intent);
            }
        });
        tb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Log_in.this, Log_id.class);
                startActivity(intent);
            }
        });
        tb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Log_in.this, Log_pw.class);
                startActivity(intent);
            }
        });
//        /*--------------애니메이션---------------*/
//        Animation scale = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
//        Animation translate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate);
//        ImageView img1 = (ImageView)findViewById(R.id.log_in_main_img);
//        img1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                view.startAnimation(scale);
//            }
//        });
//        TextView test1 = (TextView)findViewById(R.id.logintv);
//        test1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, left_MainActivity.class);
//                startActivity(intent);
//            }
//        });
        Button btn = (Button) findViewById(R.id.log_in_ok_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            /*----------엑티비티연결--------------*/
            @Override
            public void onClick(View view) {

                String user_id = et1.getText().toString();
                String user_pw = et2.getText().toString();


                Intent intent = new Intent(Log_in.this, Menu_MainActivity.class);
                startActivity(intent);
                finish();


            }
        });

    }
}