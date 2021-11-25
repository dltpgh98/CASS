package com.cookandroid.a0929;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.cookandroid.a0929.DB.FindMemberRequest;
import com.cookandroid.a0929.DB.FindgroupcodeRequest;
import com.cookandroid.a0929.List.ListViewAdapter;
import com.cookandroid.a0929.List.Schedule_ListMainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import java.util.List;

public class Menu_MainActivity extends AppCompatActivity {
    //schedule_main_fr

    private AppBarConfiguration mAppBarConfiguration;
    private AppBarConfiguration mAppBarConfiguration2;
    private long backKeyPressedTime = 0;
    public Toast toast;

    final int[] group_code = new int[1];
    String [] group_name = new String[1];

    /**/
    private int y_m_d[]=new int[3];//

    MaterialCalendarView materialCalendarView;
    List<CalendarDay> selectedDay;

    private void find_groupcode(int user_code){

        Response.Listener<String> responseListener_groupcode = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject( response );
                    boolean success = jsonObject.getBoolean( "success" );

                    if (success) {
                        group_code[0] = jsonObject.getInt("group_code");
                    }
                    else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        FindMemberRequest findMemberRequest = new FindMemberRequest(user_code, responseListener_groupcode);
        RequestQueue queue = Volley.newRequestQueue( Menu_MainActivity.this );
        queue.add(findMemberRequest);

    }

    private void find_groupname(int group_code){

        Response.Listener<String> responseListener_groupcode = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject( response );
                    boolean success = jsonObject.getBoolean( "success" );

                    if (success) {
                        group_name[0] =  jsonObject.getString("group_name");
                    }
                    else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        FindgroupcodeRequest findgroupcodeRequest = new FindgroupcodeRequest(group_code, responseListener_groupcode);
        RequestQueue queue = Volley.newRequestQueue( Menu_MainActivity.this );
        queue.add(findgroupcodeRequest);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main);
        Toolbar toolbar = findViewById(R.id.menu_leftappbar_main_toolbar);
        setSupportActionBar(toolbar);
        materialCalendarView = (MaterialCalendarView) findViewById(R.id.schedule_main_fr_calendar_clv);



        Intent intent = getIntent();
        int user_code = intent.getIntExtra("user_code", 0);
        System.out.println(user_code);

        /*좌측 햄버거 */
        DrawerLayout drawer = findViewById(R.id.menu_drawer_layout);
        NavigationView navigationView = findViewById(R.id.menu_main_nav_leftview);
        Menu left_menu = navigationView.getMenu();
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_schedule_main_fr, R.id.nav_schedule_second_fr, R.id.nav_schedule_third_fr)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        left_menu.clear();
        left_menu.add("Test");
        left_menu.getItem(0).setCheckable(true);

        drawer.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                left_menu.clear();
                left_menu.add("Test");
                left_menu.getItem(0).setCheckable(true);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });


        /*우측 햄버거*/
        NavigationView navigationView2 = findViewById(R.id.menu_main_nav_rightview);
        navigationView2.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_rightdrawer_item4:
                        Intent intent = new Intent(Menu_MainActivity.this,SettingsActivity.class);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });

        //플로팅버튼으로 엑티비티 연결코드
        FloatingActionButton schedule_list_floatbtn = (FloatingActionButton) findViewById(R.id.schedule_main_fr_list_fbtn);
        schedule_list_floatbtn.setOnClickListener(new View.OnClickListener() {
            /*----------엑티비티연결--------------*/
            @Override
            public void onClick(View view) {
                selectedDay= materialCalendarView.getSelectedDates();
                System.out.println(selectedDay);
                InitializeDay();//날짜 초기화

                Intent intent = new Intent(Menu_MainActivity.this, Schedule_ListMainActivity.class);
                intent.putExtra("main_select_Day",y_m_d);
                intent.putExtra("user_code", user_code);
                startActivity(intent);
            }
        });
        //코드 끝
        Button mp_btn_right = (Button) findViewById(R.id.schedule_main_fr_group_btn);
        mp_btn_right.setOnClickListener(new View.OnClickListener() {
            /*----------엑티비티연결--------------*/
            @Override
            public void onClick(View view) {
                drawer.openDrawer(Gravity.RIGHT);
            }
        });
        Button mp_btn_left = (Button) findViewById(R.id.schedule_main_fr_menu_btn);
        mp_btn_left.setOnClickListener(new View.OnClickListener() {
            /*----------엑티비티연결--------------*/
            @Override
            public void onClick(View view) {
                System.out.println(1);
                drawer.openDrawer(Gravity.LEFT);
            }
        });
        /*캘린더 주말 색상*/


    }
    /*버튼 2번눌렀을시 종료 코드 */
    @Override
    public void onBackPressed() {
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간에 2초를 더해 현재시간과 비교 후
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간이 2초가 지났으면 Toast Show
        // 2000 milliseconds = 2 seconds
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간에 2초를 더해 현재시간과 비교 후
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간이 2초가 지나지 않았으면 종료
        // 현재 표시된 Toast 취소
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finish();
            toast.cancel();
        }
    }
    /*종료*/
    @Override
    /*===========setting - menu=========================*/
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    /*app mainmenu setting item 클릭시 setting엑티비티 이동*/
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(Menu_MainActivity.this, SettingsActivity.class);

                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    /*============right menu=============*/
    public boolean onNavigaitionItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_rightdrawer_item1:
                Toast.makeText(this, "test1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_rightdrawer_item2:
                Toast.makeText(this, "test2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_rightdrawer_item3:
                Intent intent = new Intent(Menu_MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                break;
        }
        return onNavigaitionItemSelected(item);
    }
    @Override
    /*================nav contorller=================*/
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void InitializeDay(){

        selectedDay= materialCalendarView.getSelectedDates();

        if(selectedDay.toString()=="[]"){       //선택 하지 않았을때 상태가[]이기 때문에 맞다면 현재 시간을 받아 초기화 시켜줌
            final Calendar calendar = Calendar.getInstance();
            final int day = calendar.get(Calendar.DAY_OF_MONTH);
            final int month = calendar.get(Calendar.MONTH);
            final int year = calendar.get(Calendar.YEAR);
            y_m_d[0]=year; y_m_d[1]=month;  y_m_d[2]=day;
        }else {                                 //선택한 날로 초기화
            CalendarDay calendar = selectedDay.get(0);
            y_m_d[0] = calendar.getYear();  y_m_d[1] = calendar.getMonth(); y_m_d[2] = calendar.getDay();
            for (int i=0;i<3;i++){
                System.out.println(y_m_d[i]);
            }
        }

    }


    public void InitializeCalendar(){
        //그룹 코드,password,
    }



    /*날짜 분할*/

//                String week = new SimpleDateFormat("EE").format(calendar.getTime());
//                String day_full = year + "."+ (month+1)  + "." + day + "." + week + "요일";
//                dayResult += (day_full + "\n");
    /*종료*/
}