package com.cookandroid.a0929.DB;


import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    // 서버 URL 설정 ( PHP 파일 연동 )
    final static private String URL = "http://3.34.182.164/register.php";
    private Map<String, String> map;


    public RegisterRequest(String userID, String userPassword, String userEmail, String userName, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("user_id",userID);
        map.put("user_pw", userPassword);
        map.put("user_email", userEmail);
        map.put("user_name", userName);
        map.put("group_name", userName);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}