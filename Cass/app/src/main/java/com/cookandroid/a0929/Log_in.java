package com.cookandroid.a0929;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
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
        TextView tv1 = (TextView) findViewById(R.id.log_in_err_tv);

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

        Button btn = (Button) findViewById(R.id.log_in_ok_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            /*----------엑티비티연결--------------*/
            @Override
            public void onClick(View view) {

                String user_id = et1.getText().toString();
                String user_pw = et2.getText().toString();


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // TODO : 인코딩 문제때문에 한글 DB인 경우 로그인 불가
                            System.out.println("login" + response);
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) { // 로그인에 성공한 경우
                                String user_id = jsonObject.getString("user_id");
                                String user_pw = jsonObject.getString("user_pw");
                                String user_name = jsonObject.getString("user_name");
                                String user_email = jsonObject.getString("user_email");
                                int user_code = jsonObject.getInt("user_code");


                                Toast.makeText(getApplicationContext(), user_name+"님 환영합니다.",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Log_in.this, Menu_MainActivity.class);
                                intent.putExtra("user_id", user_id);
                                //intent.putExtra("user_pw", user_pw);
                                intent.putExtra("user_code", user_code);
                                intent.putExtra("user_name", user_name);
                                intent.putExtra("user_email", user_email);
                                startActivity(intent);
                                finish();
                            } else { // 로그인에 실패한 경우
                                tv1.setVisibility(View.VISIBLE);
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(user_id, user_pw, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Log_in.this);
                queue.add(loginRequest);
/*
                Intent intent = new Intent(Log_in.this, Menu_MainActivity.class);
                startActivity(intent);
                finish();
*/

            }

        });
        et2.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    btn.performClick();
                    return true;
                }
                return false;
            }
        });

    }
}