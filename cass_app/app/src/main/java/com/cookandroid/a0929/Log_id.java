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
import com.cookandroid.a0929.DB.FindidRequest;
import com.cookandroid.a0929.DB.LoginRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Log_id extends AppCompatActivity {

    private AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_id);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        EditText email = (EditText) findViewById(R.id.log_id_email_tet);
        EditText name = (EditText) findViewById(R.id.log_id_name_tet);
        TextView err = (TextView) findViewById(R.id.log_id_err_tv) ;


        Button btn = (Button) findViewById(R.id.log_id_finish_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user_email = email.getText().toString();
                String user_name = name.getText().toString();


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            System.out.println("hongchul" + response);
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) { // 로그인에 성공한 경우

                                String user_id = jsonObject.getString("user_id");

                                System.out.println("user : "+ user_id);
                                //int user_code = jsonObject.getInt("user_code");

                                AlertDialog.Builder builder = new AlertDialog.Builder(Log_id.this);
                                dialog = builder.setMessage("아이디는 : " + user_id).setPositiveButton("확인", null).create();
                                dialog.show();

                            } else { // 로그인에 실패한 경우
                                err.setVisibility(View.VISIBLE);
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                FindidRequest findidRequest = new FindidRequest(user_email, user_name, responseListener);
                RequestQueue queue2 = Volley.newRequestQueue(Log_id.this);
                queue2.add(findidRequest);


            }
        });
    };
}
