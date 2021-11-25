package com.cookandroid.a0929.List;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.widget.DatePicker;
import android.app.TimePickerDialog;
import android.widget.EditText;
import android.widget.TimePicker;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.cookandroid.a0929.DB.FindMemberRequest;
import com.cookandroid.a0929.DB.RegisterRequest;
import com.cookandroid.a0929.DB.ScheduleRequest;
import com.cookandroid.a0929.Log_sign_up;
import com.cookandroid.a0929.Menu_MainActivity;
import com.cookandroid.a0929.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.app.AlertDialog;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.Calendar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Schedule_pen extends AppCompatActivity
{
    //날짜 및 시간정보 저장 변수//
    private int s_year, s_month, s_date, s_hour, s_minute;
    private int e_year, e_month, e_date, e_hour, e_minute;
    private Date sd,ed, ssd, sed;

    private int[] user_code = new int[1];
    private int[] group_code = new int[1];

    /*선택 날짜 배열*/
    private int y_m_d[];

    //데이터가 저장되었을 시 저장하기 위한 이벤트리스너//
    private DatePickerDialog.OnDateSetListener callback_s_date, callback_e_date;
    private TimePickerDialog.OnTimeSetListener callback_s_time, callback_e_time;

    //데이터 형식 저장용 포맷 객체//
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM"+"월"+"dd"+"일");
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    SimpleDateFormat saveFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    TextView start_hm_time;
    TextView start_day_time;
    String db_sdate;
    String db_edate;

    TextView end_day_time;
    TextView end_hm_time;


    TextView bck_btn;
    TextView save_btn;


    TextView title;
    TextView memo;
    String color ="#ff7a7cc4";
    String writer ="작성자 미지정";


    private Animation fab_open, fab_close;
    private Boolean isFabOpen = false;
    private FloatingActionButton colorM, color1, color2,color3,color4,color5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_pen);
        /*    날짜 받는것    */
        Intent intent = getIntent();
        y_m_d = intent.getIntArrayExtra("main_select_Day");
        user_code[0] = intent.getIntExtra("user_code", 0);

        //뷰 초기화//
        InitializeView();

        //날짜 및 시간 초기화//
        InitializeTime(y_m_d[0],y_m_d[1],y_m_d[2]);

        //리스너 초기화//
        InitializeListener();

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
    private void make_schedule(String s_title, int groupcode, String s_text, String s_color ){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    System.out.println("make_schedule" + response);
                    JSONObject jsonObject = new JSONObject( response );
                    boolean success = jsonObject.getBoolean( "success" );

                    if (success) {

                    } else {
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        System.out.println(groupcode);
        //서버로 Volley를 이용해서 요청
        ScheduleRequest scheduleRequest = new ScheduleRequest(s_title, db_sdate + ":00", db_edate + ":00", s_text, s_color, user_code[0], groupcode,responseListener);
        RequestQueue queue = Volley.newRequestQueue( Schedule_pen.this );
        queue.add( scheduleRequest );

    }

    private void find_groupcode(String s_title, String s_text, String s_color, int user_code){

        Response.Listener<String> responseListener_groupcode = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    System.out.println("findgroupcode" + response);
                    JSONObject jsonObject = new JSONObject( response );
                    boolean success = jsonObject.getBoolean( "success" );

                    if (success) {
                        group_code[0] = jsonObject.getInt("group_code");
                        make_schedule(s_title, group_code[0], s_text, s_color);
                    }
                    else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        FindMemberRequest findMemberRequest = new FindMemberRequest(user_code, responseListener_groupcode);
        RequestQueue queue = Volley.newRequestQueue( Schedule_pen.this );
        queue.add(findMemberRequest);

    }
    private void InitializeView() {//뷰 아이디 지정//
        //텍스트뷰//
        start_day_time = (TextView) findViewById(R.id.schedule_pen_startdate_tv);
        start_hm_time = (TextView) findViewById(R.id.schedule_pen_starttime_tv);
        end_day_time = (TextView) findViewById(R.id.schedule_pen_enddate_tv);
        end_hm_time = (TextView) findViewById(R.id.schedule_pen_endtime_tv);


        bck_btn = (TextView) findViewById(R.id.schedule_pen_close_tv);
        save_btn = (TextView) findViewById(R.id.schedule_pen_save_tv);

        //EditText//
        title = (EditText)findViewById(R.id.schedule_pen_maintitle_et);
        memo = (EditText)findViewById(R.id.schedule_pen_memo_et);


        //플로팅버튼//
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        colorM = (FloatingActionButton) findViewById(R.id.schedule_pen_colormain_fbtn);
        color1 = (FloatingActionButton) findViewById(R.id.schedule_pen_color1_fbtn);
        color2 = (FloatingActionButton) findViewById(R.id.schedule_pen_color2_fbtn);
        color3 = (FloatingActionButton) findViewById(R.id.schedule_pen_color3_fbtn);
        color4 = (FloatingActionButton) findViewById(R.id.schedule_pen_color4_fbtn);
        color5 = (FloatingActionButton) findViewById(R.id.schedule_pen_color5_fbtn);
    }

    private void InitializeTime(int y,int m,int d) {//시간 초기화// 선택한 년도,달,일자 로 초기화 //
        //현재 시간을 받아오기 위한 캘린더 객체 선언//
//        final Calendar cal = Calendar.getInstance();
        s_year=y;                                     //cal.get(Calendar.YEAR);
        s_month=m;                                    //cal.get(Calendar.MONTH);
        s_date=d;                                     //cal.get(Calendar.DATE);
        s_hour=0;
        s_minute=0;
        e_year=y;                                     //cal.get(Calendar.YEAR);
        e_month=m;                                   //cal.get(Calendar.MONTH);
        e_date=d;                                     //cal.get(Calendar.DATE);
        e_hour=23;
        e_minute=59;


        //텍스트뷰에 날짜 및 시간 설정//
        sd = new Date(s_year,s_month,s_date,s_hour,s_minute);
        ed = new Date(e_year,e_month,e_date,e_hour,e_minute);

        String format;
        format = dateFormat.format(sd);
        start_day_time.setText(format);

        format = dateFormat.format(ed);
        end_day_time.setText(format);

        format = timeFormat.format(sd);
        start_hm_time.setText(format);

        format = timeFormat.format(ed);
        end_hm_time.setText(format);
    }

    private void InitializeListener() {//리스너 초기화//

        bck_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {finish();}
        });

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {finish();}
        });

        colorM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {anim();}
        });

        color1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anim();
                colorM.setBackgroundTintList(ColorStateList.valueOf(Color
                        .parseColor("#ff7a7cc4")));
                color="#ff7a7cc4";
            }
        });

        color2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anim();
                colorM.setBackgroundTintList(ColorStateList.valueOf(Color
                        .parseColor("#ffff6600")));
                color="#ffff6600";
            }
        });

        color3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anim();
                colorM.setBackgroundTintList(ColorStateList.valueOf(Color
                        .parseColor("#ff259999")));
                color="#ff259999";
            }
        });
        color4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anim();
                colorM.setBackgroundTintList(ColorStateList.valueOf(Color
                        .parseColor("#ff75c52a")));
                color="#ff75c52a";
            }
        });
        color5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anim();
                colorM.setBackgroundTintList(ColorStateList.valueOf(Color
                        .parseColor("#ffe9a5a7")));
                color="#ffe9a5a7";
            }
        });

        //시작 날짜가 변경되었을때 이벤트//
        callback_s_date = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int y, int m, int d)
            {
                s_year = y;
                s_month = m;
                s_date = d;
                sd = new Date(s_year,s_month,s_date,s_hour,s_minute);
                String format;
                format = dateFormat.format(sd);
                start_day_time.setText(format);
            }
        };

        //시작 시간이 변경되었을때 이벤트//
        callback_s_time = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker view, int h, int m) {
                s_hour = h;
                s_minute = m;
                sd = new Date(s_year,s_month,s_date,s_hour,s_minute);
                String format;
                format = timeFormat.format(sd);
                start_hm_time.setText(format);
            }
        };

        //종료 날짜가 변경되었을때 이벤트//
        callback_e_date = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int y, int m, int d)
            {
                e_year = y;
                e_month = m;
                e_date = d;
                ed = new Date(e_year,e_month,e_date,e_hour,e_minute);
                String format;
                format = dateFormat.format(ed);
                end_day_time.setText(format);
                System.out.println(end_day_time);
            }
        };

        //종료 시간이 변경되었을때 이벤트//
        callback_e_time = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int h, int m) {
                e_hour = h;
                e_minute = m;
                ed = new Date(e_year, e_month, e_date, e_hour, e_minute);
                String format;
                format = timeFormat.format(ed);
                end_hm_time.setText(format);
            }
        };


        //버튼클릭시 종료및 저장하는 이벤트//
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s_title = title.getText().toString();
                String sdate = start_day_time.getText().toString();
                String edate = end_day_time.getText().toString();
                String s_text = memo.getText().toString();
                String s_color = color;
                String s_write = writer;

                //디비 저장용
                String format;
                ssd = new Date(s_year - 1900, s_month, s_date, s_hour, s_minute);
                sed = new Date(e_year - 1900, e_month, e_date, e_hour, e_minute);
                format = saveFormat.format(ssd);
                db_sdate = format;
                format = saveFormat.format(sed);
                db_edate = format;

                find_groupcode(s_title, s_text, s_color, user_code[0]);

                Intent intent = new Intent(Schedule_pen.this, Schedule_ListMainActivity.class);
                intent.putExtra("제목", s_title);
                intent.putExtra("시작날짜", sdate);
                intent.putExtra("종료날짜", edate);
                intent.putExtra("메모", s_text);
                intent.putExtra("컬러", s_color);
                intent.putExtra("작성자", s_write);
                intent.putExtra("main_select_Day", y_m_d);
                startActivity(intent);
                finish();

            }
        });
    }


    public void anim() {

        if (isFabOpen) {
            color1.startAnimation(fab_close);
            color2.startAnimation(fab_close);
            color3.startAnimation(fab_close);
            color4.startAnimation(fab_close);
            color5.startAnimation(fab_close);
            color1.setClickable(false);
            color2.setClickable(false);
            color3.setClickable(false);
            color4.setClickable(false);
            color5.setClickable(false);
            isFabOpen = false;
        } else {
            color1.startAnimation(fab_open);
            color2.startAnimation(fab_open);
            color3.startAnimation(fab_open);
            color4.startAnimation(fab_open);
            color5.startAnimation(fab_open);
            color1.setClickable(true);
            color2.setClickable(true);
            color3.setClickable(true);
            color4.setClickable(true);
            color5.setClickable(true);
            isFabOpen = true;
        }
    }

    public void OnStartDate(View view)
    {
        DatePickerDialog dialog = new DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT, callback_s_date, s_year, s_month, s_date);
        dialog.show();
    }
    public void OnEndDate(View view)
    {
        DatePickerDialog dialog = new DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT, callback_e_date, e_year, e_month, e_date);
        dialog.show();
    }
    public void OnStartTime(View view)
    {
        TimePickerDialog dialog = new TimePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT, callback_s_time, s_hour, s_minute, true);
        dialog.show();
    }
    public void OnEndTime(View view)
    {
        TimePickerDialog dialog = new TimePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT, callback_e_time, e_hour, e_minute, true);
        dialog.show();
    }
}