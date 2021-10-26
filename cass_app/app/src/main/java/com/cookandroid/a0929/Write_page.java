package com.cookandroid.a0929;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.cookandroid.a0929.ui.fragment.DatePickDialog;
import com.cookandroid.a0929.ui.fragment.TimePickDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;


public class Write_page extends AppCompatActivity  implements View.OnClickListener {
    long mNow;
    Date mDate;
    long dNow;
    Date dDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("hh:mm");
    SimpleDateFormat dFormat = new SimpleDateFormat("MM월dd일");
    TextView start_hm_time;
    TextView start_day_time;
    TextView end_day_time;
    TextView end_hm_time;
    TextView bck_btn;
    TextView save_btn;


    private Animation fab_open, fab_close;
    private Boolean isFabOpen = false;
    private FloatingActionButton fab, fab1, fab2,fab3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_page);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        start_day_time = (TextView) findViewById(R.id.wp_start_time_day);
        start_hm_time = (TextView) findViewById(R.id.wp_start_time_hm);
        end_day_time = (TextView) findViewById(R.id.wp_end_time_day);
        end_hm_time = (TextView) findViewById(R.id.wp_end_time_hm);
        bck_btn = (TextView) findViewById(R.id.wp_back_TextBtn);
        save_btn = (TextView) findViewById(R.id.wp_save_button);

        start_day_time.setText(getTimeD());
        end_day_time.setText(getTimeD());
        start_hm_time.setText(getTime());
        end_hm_time.setText(getTime());
        //플로팅버튼//
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fab = (FloatingActionButton) findViewById(R.id.fab1);   fab.setOnClickListener(this);
        fab1 = (FloatingActionButton) findViewById(R.id.fab2);  fab1.setOnClickListener(this);
        fab2 = (FloatingActionButton) findViewById(R.id.fab3);  fab2.setOnClickListener(this);
        fab3 = (FloatingActionButton) findViewById(R.id.fab4);  fab3.setOnClickListener(this);
        //

        bck_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {finish();}
        });
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {finish();}
        });


        start_day_time.setOnClickListener(new View.OnClickListener(){
            DatePickDialog pd = new DatePickDialog();
            @Override
            public void onClick(View view) {
                pd.setListener(d);
                pd.show(getSupportFragmentManager(), "YearMonthPickerTest");
            }
        });
        start_hm_time.setOnClickListener(new View.OnClickListener(){
            TimePickDialog pd = new TimePickDialog();
            @Override
            public void onClick(View view) {
                pd.setListener(d);
                pd.show(getSupportFragmentManager(), "YearMonthPickerTest");
            }
        });
        end_day_time.setOnClickListener(new View.OnClickListener(){
            DatePickDialog pd = new DatePickDialog();
            @Override
            public void onClick(View view) {
                pd.setListener(d);
                pd.show(getSupportFragmentManager(), "YearMonthPickerTest");
            }
        });
        end_hm_time.setOnClickListener(new View.OnClickListener(){
            TimePickDialog pd = new TimePickDialog();
            @Override
            public void onClick(View view) {
                pd.setListener(d);
                pd.show(getSupportFragmentManager(), "YearMonthPickerTest");
            }
        });
    }
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
            Log.d("YearMonthPickerTest", "year = " + year + ", month = " + monthOfYear + ", day = " + dayOfMonth);
        }
    };
    private String getTimeD(){
        dNow = System.currentTimeMillis();
        dDate = new Date(dNow);
        return dFormat.format(dDate);
    }
    private String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fab1:
                anim();
                Toast.makeText(this, "Floating Action Button", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fab2:
                anim();
                Toast.makeText(this, "Button1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fab3:
                anim();
                Toast.makeText(this, "Button2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fab4:
                anim();
                Toast.makeText(this, "Button3", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void anim() {

        if (isFabOpen) {
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab3.startAnimation(fab_open);
            fab1.setClickable(false);
            fab2.setClickable(false);
            fab3.setClickable(false);
            isFabOpen = false;
        } else {
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab3.startAnimation(fab_close);
            fab1.setClickable(true);
            fab2.setClickable(true);
            fab3.setClickable(true);

            isFabOpen = true;
        }
    }
}
