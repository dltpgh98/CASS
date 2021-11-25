package com.cookandroid.a0929.DB;


import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ScheduleRequest extends StringRequest {

    // 서버 URL 설정 ( PHP 파일 연동 )
    final static private String URL = "http://3.34.182.164/write.php";
    private Map<String, String> map;


    public ScheduleRequest(String title, String sdate, String edate, String text, String color, int usercode, int groupcode, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("schedule_title", title);
        map.put("schedule_sdate", sdate);
        map.put("schedule_edate", edate);
        map.put("schedule_text", text);
        map.put("schedule_color", color);
        map.put("user_code",usercode + "");
        map.put("group_code", groupcode + "");

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}