package com.cookandroid.a0929;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import com.cookandroid.a0929.DB.*;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;


public class Log_sign_up extends AppCompatActivity {
    private EditText id, pw, email, checkpw, name;
    private TextView hintpw, hintname, hintid, hintemail, hintcheckpw;
    private boolean validate = false;
    private AlertDialog dialog;
    Pattern p = Patterns.EMAIL_ADDRESS;
    private Button btn, checkbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_sign);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        id = (EditText) findViewById(R.id.log_sign_id_tet);
        name = (EditText) findViewById(R.id.log_sign_name_tet);
        email = (EditText) findViewById(R.id.log_sign_email_tet);
        pw = (EditText) findViewById(R.id.log_sign_pw_tet);
        checkpw = (EditText) findViewById(R.id.log_sign_pwcheck_tet);
        hintpw = (TextView) findViewById(R.id.log_sign_pwerr_tv);
        hintid = (TextView) findViewById(R.id.log_sign_iderr_tv);
        hintname = (TextView) findViewById(R.id.log_sign_nameerr_tv);
        hintemail = (TextView) findViewById(R.id.log_sign_emailerr_tv);
        hintcheckpw = (TextView) findViewById(R.id.log_sign_pwcheckerr_tv);
        btn = (Button) findViewById(R.id.log_sign_finish_btn);
        checkbtn = (Button) findViewById(R.id.log_sign_check_btn);

        checkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_id = id.getText().toString();
                if (validate) {
                    return; //검증 완료
                }

                if (user_id.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Log_sign_up.this);
                    dialog = builder.setMessage("아이디를 입력하세요.").setPositiveButton("확인", null).create();
                    dialog.show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Log_sign_up.this);
                                dialog = builder.setMessage("사용할 수 있는 아이디입니다.").setPositiveButton("확인", null).create();
                                dialog.show();
                                validate = true; //검증 완료

                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Log_sign_up.this);
                                dialog = builder.setMessage("이미 존재하는 아이디입니다.").setNegativeButton("확인", null).create();
                                dialog.show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                ValidateRequest validateRequest = new ValidateRequest(user_id, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Log_sign_up.this);
                queue.add(validateRequest);
            }
        });



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String user_id = id.getText().toString();
                final String user_name = name.getText().toString();
                final String user_pw = pw.getText().toString();
                final String user_checkpw = checkpw.getText().toString();
                final String user_email = email.getText().toString();

                if (!validate) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Log_sign_up.this);
                    dialog = builder.setMessage("중복된 아이디가 있는지 확인하세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }

                //한 칸이라도 입력 안했을 경우
                if (user_id.equals("") || user_pw.equals("") || user_name.equals("") || user_checkpw.equals("") || user_email.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Log_sign_up.this);
                    dialog = builder.setMessage("모두 입력해주세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject( response );
                            boolean success = jsonObject.getBoolean( "success" );

                            //회원가입 성공시
                            if(user_pw.equals(checkpw)) {
                                if (success) {

                                    Toast.makeText(getApplicationContext(), String.format("%s님 가입을 환영합니다.", user_id), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Log_sign_up.this, Menu_MainActivity.class);
                                    startActivity(intent);

                                    //회원가입 실패시
                                } else {
                                    Toast.makeText(getApplicationContext(), "회원가입에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Log_sign_up.this);
                                dialog = builder.setMessage("비밀번호가 동일하지 않습니다.").setNegativeButton("확인", null).create();
                                dialog.show();
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };





                //서버로 Volley를 이용해서 요청
                RegisterRequest registerRequest = new RegisterRequest( user_id, user_pw, user_email, user_name,responseListener);
                RequestQueue queue = Volley.newRequestQueue( Log_sign_up.this );
                queue.add( registerRequest );

            }
        });


    }


}