package com.cookandroid.a0929.DB;


import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MemberRequest extends StringRequest {

    // 서버 URL 설정 ( PHP 파일 연동 )
    final static private String URL = "http://3.34.182.164/member.php";
    private Map<String, String> map;


    public MemberRequest(int user_code, int group_code, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        int member_role = 1;

        System.out.println("MemberRequest 호출");
        System.out.println("MemberRequest 값 호출" +  user_code +"   "+ group_code);
        map = new HashMap<>();
        map.put("user_code", user_code + "");
        map.put("group_code", group_code + "");
        map.put("member_role", member_role + "");

    }


    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}