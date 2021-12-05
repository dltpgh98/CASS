package com.cookandroid.a0929;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
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
import androidx.core.app.ComponentActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;
import java.util.regex.Pattern;


public class Log_sign_up extends AppCompatActivity {
    private EditText id, pw, email, checkpw, name;
    private TextView hintpw, hintname, hintid, hintemail, hintcheckpw;
    private boolean validate = false;
    private boolean codevalidate = false;
    private AlertDialog dialog;
    Pattern p = Patterns.EMAIL_ADDRESS;
    private Button btn, checkbtn;
    final int[] user_code = new int[1];


    private void make_random(String user_name, String user_id, String user_pw){

        int minimumValue = 100000;
        int maximumValue = 999999;
        Random random = new Random();
        final int[] randomValue = {random.nextInt(maximumValue - minimumValue + 1) + minimumValue};

        Response.Listener<String> responseListener_groupcode = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject( response );
                    boolean success = jsonObject.getBoolean( "success" );

                    System.out.println("make_random 리스너 호출");
                    if (success) {
                        codevalidate = true; //검증 완료
                        make_group(user_name, randomValue, user_id, user_pw);
                    }
                    else {
                        randomValue[0] = random.nextInt(maximumValue - minimumValue + 1) + minimumValue;
                        make_group(user_name, randomValue, user_id, user_pw);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        System.out.println("make_random 함수 호출");
        GroupValidateRequest groupValidateRequest = new GroupValidateRequest(randomValue[0], responseListener_groupcode);
        RequestQueue queue2 = Volley.newRequestQueue( Log_sign_up.this );
        queue2.add(groupValidateRequest);

    }

    private void make_group(String user_name, int[] randomValue, String user_id, String user_pw){

        Response.Listener<String> responseListener_grouptable = new Response.Listener<String>() {// ************회원가입********************
            @Override
            public void onResponse(String response) {

                try {
                    System.out.println("make_group 리스너 호출");
                    JSONObject jsonObject = new JSONObject( response );
                    boolean success = jsonObject.getBoolean( "success" );
                    findUsercode(user_id, user_pw, randomValue[0]);
                    if (success) {

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        //서버로 Volley를 이용해서 요청
        System.out.println("make_group 호출");
        GroupRequest groupRequest = new GroupRequest( randomValue[0], user_name + "님의 스케줄", responseListener_grouptable);
        RequestQueue queue3 = Volley.newRequestQueue( Log_sign_up.this );
        queue3.add(groupRequest);
    }


    private void findUsercode (String user_id, String user_pw, int randomValue){

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    // TODO : 인코딩 문제때문에 한글 DB인 경우 로그인 불가
                    System.out.println("findUsercode" + response);
                    System.out.println("findUsercode 리스너 호출");
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if (success) {
                        user_code[0] = jsonObject.getInt("user_code");
                        //findGroupcode(user_name, user_code[0]);
                        makeMamber(user_code[0], randomValue);
                    } else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        System.out.println("findUsercode 호출");
        LoginRequest loginRequest = new LoginRequest(user_id, user_pw, responseListener);
        RequestQueue queue = Volley.newRequestQueue(Log_sign_up.this);
        queue.add(loginRequest);

    }

//    private void findGroupcode (String group_name, int user_code){
//
//
//        Response.Listener<String> responseListener = new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    // TODO : 인코딩 문제때문에 한글 DB인 경우 로그인 불가
//                    System.out.println("findGroupcode" + response);
//                    JSONObject jsonObject = new JSONObject(response);
//                    System.out.println("group_code 리스너 호출");
//                    boolean success = jsonObject.getBoolean("success");
//                    if (success) {
//                        group_code[0] = jsonObject.getInt("group_code");
//                        makeMamber(user_code, group_code[0]);
//                    } else {
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        System.out.println("group_code 호출");
//        FindgroupcodeRequest findgroupcodeRequest = new FindgroupcodeRequest(group_name, responseListener);
//        RequestQueue queue_group = Volley.newRequestQueue(Log_sign_up.this);
//        queue_group.add(findgroupcodeRequest);
//
//    }


    private void makeMamber (int user_code, int group_code){

        Response.Listener<String> responseListener = new Response.Listener<String>() {// ************멤버테이블 생성********************
            @Override
            public void onResponse(String response) {
                try {
                    System.out.println("makeMamber" + response);
                    System.out.println("makeMamber 리스너 호출");
                    JSONObject jsonObject = new JSONObject( response );
                    boolean success = jsonObject.getBoolean( "success" );
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        //서버로 Volley를 이용해서 요청
        System.out.println("makeMamber 호출");
        System.out.println(user_code + "" + group_code);
        MemberRequest memberRequest = new MemberRequest(user_code, group_code, responseListener);
        RequestQueue queue_member = Volley.newRequestQueue( Log_sign_up.this );
        queue_member.add(memberRequest);

    }


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
                //*******************아이디 중복 체크***************************
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println("아이디 중복체크");
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Log_sign_up.this);
                                dialog = builder.setMessage("사용할 수 있는 아이디입니다.").setPositiveButton("확인", null).create();
                                dialog.show();
                                validate = true; //검증 완료
                                id.setEnabled(false);
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


                if (!validate) {// 중복 체크 했는지 확인
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

                Response.Listener<String> responseListener = new Response.Listener<String>() {// ************회원가입********************
                    @Override
                    public void onResponse(String response) {

                        try {
                            System.out.println("아무이름" + response);
                            JSONObject jsonObject = new JSONObject( response );
                            boolean success = jsonObject.getBoolean( "success" );

                            //회원가입 성공시
                            if(user_pw.equals(user_checkpw)) {

                                if (success) {
                                    make_random(user_name, user_id, user_pw);
                                    Toast.makeText(getApplicationContext(), String.format("%s님 가입을 환영합니다.", user_id), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Log_sign_up.this, Menu_MainActivity.class);
                                    startActivity(intent);
                                    finish();
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
                RegisterRequest registerRequest = new RegisterRequest( user_id, user_pw, user_email, user_name, responseListener);
                RequestQueue queue = Volley.newRequestQueue( Log_sign_up.this );
                queue.add(registerRequest);
            }

        });

    }

}