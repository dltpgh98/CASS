package com.cookandroid.a0929.DB;


import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ParticipateRequest extends StringRequest {

    // 서버 URL 설정 ( PHP 파일 연동 )
    final static private String URL = "http://3.34.182.164/participate.php";
    private Map<String, String> map;

    public ParticipateRequest(int user_code, int group_code, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("user_code",user_code + "");
        map.put("group_code", group_code + "");
        map.put("member_role", 0 + "");
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}