package com.cookandroid.a0929;

import android.content.Intent;
import android.os.Bundle;
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
import com.cookandroid.a0929.DB.FindpwRequest;
import com.cookandroid.a0929.DB.LoginRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Log_pw extends AppCompatActivity {
    AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_pw);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        EditText id = (EditText) findViewById(R.id.log_pw_id_tet);
        EditText eamil = (EditText) findViewById(R.id.log_pw_email_tet);
        EditText name = (EditText) findViewById(R.id.log_pw_name_tet);
        TextView err = (TextView) findViewById(R.id.log_pw_err_tv);


        Button btn = (Button) findViewById(R.id.log_pw_finish_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user_id = id.getText().toString();
                String user_email = eamil.getText().toString();
                String user_name = name.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // TODO : 인코딩 문제때문에 한글 DB인 경우 로그인 불가
                            System.out.println("hongchul" + response);
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) { // 로그인에 성공한 경우
                                String user_id = jsonObject.getString("user_id");
                                String user_pw = jsonObject.getString("user_pw");
                                String user_name = jsonObject.getString("user_name");

                                AlertDialog.Builder builder = new AlertDialog.Builder(Log_pw.this);
                                dialog = builder.setMessage("비밀번호는 : " + user_pw).setPositiveButton("확인", null).create();
                                dialog.show();

                                //finish();
                            } else { // 로그인에 실패한 경우
                                err.setVisibility(View.VISIBLE);
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                FindpwRequest findpwRequest = new FindpwRequest(user_id, user_email, user_name,responseListener);
                RequestQueue queue = Volley.newRequestQueue(Log_pw.this);
                queue.add(findpwRequest);
            }
        });
    };
}