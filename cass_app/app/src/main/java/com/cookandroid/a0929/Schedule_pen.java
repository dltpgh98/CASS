package com.cookandroid.a0929;

import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.app.TimePickerDialog;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.app.AlertDialog;
import java.util.Calendar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Schedule_pen extends AppCompatActivity
{
    //날짜 및 시간정보 저장 변수//
    private int s_year, s_month, s_date, s_hour, s_minute;
    private int e_year, e_month, e_date, e_hour, e_minute;
    private Date sd,ed;

    //데이터가 저장되었을 시 저장하기 위한 이벤트리스너//
    private DatePickerDialog.OnDateSetListener callback_s_date, callback_e_date;
    private TimePickerDialog.OnTimeSetListener callback_s_time, callback_e_time;

    //데이터 형식 저장용 포맷 객체//
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM"+"월"+"dd"+"일");
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    TextView start_hm_time;
    TextView start_day_time;
    TextView end_day_time;
    TextView end_hm_time;
    TextView bck_btn;
    TextView save_btn;

    private Animation fab_open, fab_close;
    private Boolean isFabOpen = false;
    private FloatingActionButton colorM, color1, color2,color3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_pen);

        //뷰 초기화//
        InitializeView();

        //날짜 및 시간 초기화//
        InitializeTime();

        //리스너 초기화//
        InitializeListener();

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    private void InitializeView() {//뷰 아이디 지정//
        //텍스트뷰//
        start_day_time = (TextView) findViewById(R.id.schedule_pen_startdate_tv);
        start_hm_time = (TextView) findViewById(R.id.schedule_pen_starttime_tv);
        end_day_time = (TextView) findViewById(R.id.schedule_pen_enddate_tv);
        end_hm_time = (TextView) findViewById(R.id.schedule_pen_endtime_tv);
        bck_btn = (TextView) findViewById(R.id.schedule_pen_close_tv);
        save_btn = (TextView) findViewById(R.id.schedule_pen_save_tv);

        //플로팅버튼//
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        colorM = (FloatingActionButton) findViewById(R.id.schedule_pen_colormain_fbtn);
        color1 = (FloatingActionButton) findViewById(R.id.schedule_pen_color1_fbtn);
        color2 = (FloatingActionButton) findViewById(R.id.schedule_pen_color2_fbtn);
        color3 = (FloatingActionButton) findViewById(R.id.schedule_pen_color3_fbtn);
    }

    private void InitializeTime() {//시간 초기화//
        //현재 시간을 받아오기 위한 캘린더 객체 선언//
        final Calendar cal = Calendar.getInstance();
        s_year=cal.get(Calendar.YEAR);
        s_month=cal.get(Calendar.MONTH);
        s_date=cal.get(Calendar.DATE);
        s_hour=0;
        s_minute=0;
        e_year=cal.get(Calendar.YEAR);
        e_month=cal.get(Calendar.MONTH);
        e_date=cal.get(Calendar.DATE);
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
            }
        });

        color2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anim();
                colorM.setBackgroundTintList(ColorStateList.valueOf(Color
                        .parseColor("#ffff6600")));
            }
        });

        color3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anim();
                colorM.setBackgroundTintList(ColorStateList.valueOf(Color
                        .parseColor("#ff259999")));
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
            }
        };

        //종료 시간이 변경되었을때 이벤트//
        callback_e_time = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker view, int h, int m) {
                e_hour = h;
                e_minute = m;
                ed = new Date(e_year,e_month,e_date,e_hour,e_minute);
                String format;
                format = timeFormat.format(ed);
                end_hm_time.setText(format);
            }
        };
    }

    public void anim() {

        if (isFabOpen) {
            color1.startAnimation(fab_open);
            color2.startAnimation(fab_open);
            color3.startAnimation(fab_open);
            color1.setClickable(false);
            color2.setClickable(false);
            color3.setClickable(false);
            isFabOpen = false;
        } else {
            color1.startAnimation(fab_close);
            color2.startAnimation(fab_close);
            color3.startAnimation(fab_close);
            color1.setClickable(true);
            color2.setClickable(true);
            color3.setClickable(true);
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