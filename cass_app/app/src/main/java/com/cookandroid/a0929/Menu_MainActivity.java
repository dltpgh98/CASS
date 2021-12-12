package com.cookandroid.a0929;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.cookandroid.a0929.DB.FindMemberRequest;
import com.cookandroid.a0929.DB.FindgroupcodeRequest;
import com.cookandroid.a0929.DB.GroupRequest;
import com.cookandroid.a0929.DB.GroupValidateRequest;
import com.cookandroid.a0929.DB.LoginRequest;
import com.cookandroid.a0929.DB.MemberRequest;
import com.cookandroid.a0929.DB.ParticipateRequest;
import com.cookandroid.a0929.List.ListViewAdapter;
import com.cookandroid.a0929.List.Schedule_ListMainActivity;
import com.cookandroid.a0929.ui.deco.OneDayDecorator;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;
import java.util.Random;

public class Menu_MainActivity extends AppCompatActivity {
    //schedule_main_fr

    private AppBarConfiguration mAppBarConfiguration;
    private AppBarConfiguration mAppBarConfiguration2;
    private long backKeyPressedTime = 0;
    public Toast toast;

    TextView mainName;

    final int[] group_code = new int[1];
    String [] group_name = new String[1];

    /**/
    private final int[] y_m_d =new int[3];//
    int user_code;
    int init = 0;
    int saveGroup;

    MaterialCalendarView materialCalendarView;
    List<CalendarDay> selectedDay;

    ArrayList<Integer> user_code_array;
    ArrayList<String> user_name_array;
    ArrayList<Integer> group_code_array;
    ArrayList<String> group_name_array;
    ArrayList<Integer> member_role_array;

    ArrayList<Integer> able_group_code_array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main);
        Toolbar toolbar = findViewById(R.id.menu_leftappbar_main_toolbar);
        setSupportActionBar(toolbar);
        materialCalendarView = (MaterialCalendarView) findViewById(R.id.schedule_main_fr_calendar_clv);

        OneDayDecorator oneDayDecorator = new OneDayDecorator();
        materialCalendarView.addDecorators(oneDayDecorator);



        Intent intent = getIntent();
        user_code = intent.getIntExtra("user_code", 0);
        String user_name = intent.getStringExtra("user_name");


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

        new DrawerTask().execute();

        View left_header = navigationView.getHeaderView(0);
        TextView l_title = left_header.findViewById(R.id.menu_leftheader_name);
        l_title.setText(user_name);



        //////////////////////////////////*우측 햄버거*/////////////////////////////////
        NavigationView navigationView2 = findViewById(R.id.menu_main_nav_rightview);

        View right_header = navigationView2.getHeaderView(0);
        Button sett =(Button) right_header.findViewById(R.id.menu_rightheader_sett_btn);
        sett.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu_MainActivity.this,SettingsActivity.class);
                intent.putExtra("group_code",group_code[0]);
                startActivity(intent);
            }
        });

//        navigationView2.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.menu_rightdrawer_item4:
//                        Intent intent = new Intent(Menu_MainActivity.this,SettingsActivity.class);
//                        intent.putExtra("group_code",group_code[0]);
//                        startActivity(intent);
//                        return true;
//                }
//                return false;
//            }
//        });




        //플로팅버튼으로 엑티비티 연결코드
        FloatingActionButton schedule_list_floatbtn = (FloatingActionButton) findViewById(R.id.schedule_main_fr_list_fbtn);
        schedule_list_floatbtn.setOnClickListener(new View.OnClickListener() {
            /*----------엑티비티연결--------------*/
            @Override
            public void onClick(View view) {
                selectedDay= materialCalendarView.getSelectedDates();
                System.out.println(selectedDay);
                //InitializeDay();//날짜 초기화
                new BackgroundTask().execute();
//                Intent intent = new Intent(Menu_MainActivity.this, Schedule_ListMainActivity.class);
//                intent.putExtra("main_select_Day",y_m_d);
//                intent.putExtra("user_code", user_code);
//                intent.putExtra("user_name", user_name);
//                intent.putExtra("group_code", group_code[0]);

//                startActivity(intent);
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

        Handler delay = new Handler();

        delay.postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println(" ");
                System.out.println("===============System Message===============");
                System.out.println("현재 그룹코드 : " + group_code[0]);
                System.out.println("============================================");
            }
        },1000);



    }
    /*버튼 2번눌렀을시 종료 코드 */
    @Override
    public void onBackPressed() {
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간에 2초를 더해 현재시간과 비교 후
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간이 2초가 지났으면 Toast Show
        // 2000 milliseconds = 2 seconds
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "'뒤로' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
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
            final int month = calendar.get(Calendar.MONTH)+1;
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
    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            //List.php은 파싱으로 가져올 웹페이지
            target = "http://3.34.182.164/list.php";
        }

        @Override
        protected String doInBackground(Void... voids) {

            try{
                URL url = new URL(target);//URL 객체 생성

                //URL을 이용해서 웹페이지에 연결하는 부분
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

                //바이트단위 입력스트림 생성 소스는 httpURLConnection
                InputStream inputStream = httpURLConnection.getInputStream();

                //웹페이지 출력물을 버퍼로 받음 버퍼로 하면 속도가 더 빨라짐
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;

                //문자열 처리를 더 빠르게 하기 위해 StringBuilder클래스를 사용함
                StringBuilder stringBuilder = new StringBuilder();

                //한줄씩 읽어서 stringBuilder에 저장함
                while((temp = bufferedReader.readLine()) != null){
                    stringBuilder.append(temp + "\n");//stringBuilder에 넣어줌
                }

                System.out.println(stringBuilder.toString());

                //사용했던 것도 다 닫아줌
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();//trim은 앞뒤의 공백을 제거함

            }catch(Exception e){
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            Intent tent = getIntent();
            int user_code = tent.getIntExtra("user_code", 0);
            String user_name = tent.getStringExtra("user_name");
            System.out.println(user_code);
            InitializeDay();//날짜 초기화

            for(int i = 0; i < group_name_array.size(); i++){
                if(mainName.getText().equals(group_name_array.get(i))){
                    group_code[0]=group_code_array.get(i);
                }
            }

            Intent intent = new Intent(Menu_MainActivity.this, Schedule_ListMainActivity.class);
            intent.putExtra("scheduleList", result);//파싱한 값을 넘겨줌
            intent.putExtra("group_code", group_code[0]);
            intent.putExtra("main_select_Day",y_m_d);
            intent.putExtra("user_code",user_code);
            Menu_MainActivity.this.startActivity(intent);//ManagementActivity로 넘어감

        }

    }
    class DrawerTask extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            //List.php은 파싱으로 가져올 웹페이지
            target = "http://3.34.182.164/menulist.php";
        }

        @Override
        protected String doInBackground(Void... voids) {

            try{
                URL url = new URL(target);//URL 객체 생성

                //URL을 이용해서 웹페이지에 연결하는 부분
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

                //바이트단위 입력스트림 생성 소스는 httpURLConnection
                InputStream inputStream = httpURLConnection.getInputStream();

                //웹페이지 출력물을 버퍼로 받음 버퍼로 하면 속도가 더 빨라짐
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;

                //문자열 처리를 더 빠르게 하기 위해 StringBuilder클래스를 사용함
                StringBuilder stringBuilder = new StringBuilder();

                //한줄씩 읽어서 stringBuilder에 저장함
                while((temp = bufferedReader.readLine()) != null){
                    stringBuilder.append(temp + "\n");//stringBuilder에 넣어줌
                }

                System.out.println(stringBuilder.toString());

                //사용했던 것도 다 닫아줌
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();//trim은 앞뒤의 공백을 제거함

            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");

                String user_name, group_name;
                int user_code, group_code, member_role;
                user_code_array = new ArrayList<>();
                user_name_array = new ArrayList<>();
                group_code_array = new ArrayList<>();
                group_name_array = new ArrayList<>();
                member_role_array = new ArrayList<>();

                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject object = jsonArray.getJSONObject(i);

                    user_code = object.getInt("user_code");
                    group_code = object.getInt("group_code");
                    user_name = object.getString("user_name");
                    group_name = object.getString("group_name");
                    member_role = object.getInt("member_role");

                    user_code_array.add(user_code);
                    group_code_array.add(group_code);
                    user_name_array.add(user_name);
                    group_name_array.add(group_name);
                    member_role_array.add(member_role);
                }

                print_left_drawer();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
    public void Left_Button(View view){
        View dialogView = getLayoutInflater().inflate(R.layout.schedule_menu_group_fr, null);
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setView(dialogView);
        final AlertDialog alertDialog = dialog.create();
        alertDialog.show();

        TextView plus_group = dialogView.findViewById(R.id.schedule_menu_group_fr_plus_tv);
        TextView attend_group = dialogView.findViewById(R.id.schedule_menu_group_fr_attend_tv);
        plus_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                View Pdialog = getLayoutInflater().inflate(R.layout.schedule_menu_plusgroup_fr,null);
                AlertDialog.Builder p_dialog=new AlertDialog.Builder(view.getContext());
                p_dialog.setView(Pdialog);
                final AlertDialog aalertDialog = p_dialog.create();
                aalertDialog.show();

                Button plusBtn = Pdialog.findViewById(R.id.schedule_menu_plusgroup_btn);

                plusBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        EditText et = Pdialog.findViewById(R.id.schedule_menu_plusgroup_et);
                        String plus_name = et.getText().toString();

                        final int[] randomV = new int[1];

                        Handler delay = new Handler();

                        delay.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                int minimumValue = 100000;
                                int maximumValue = 999999;
                                Random random = new Random();
                                randomV[0] = random.nextInt(maximumValue - minimumValue + 1) + minimumValue;

                                Response.Listener<String> responseListener_groupcode = new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {

                                            JSONObject jsonObject = new JSONObject( response );
                                            boolean success = jsonObject.getBoolean( "success" );

                                            if (success) {

                                            }
                                            else {
                                                randomV[0] = random.nextInt(maximumValue - minimumValue + 1) + minimumValue;
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };
                                GroupValidateRequest groupValidateRequest = new GroupValidateRequest(randomV[0], responseListener_groupcode);
                                RequestQueue queue2 = Volley.newRequestQueue( Menu_MainActivity.this );
                                queue2.add(groupValidateRequest);
                            }
                        },200);

                        delay.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Response.Listener<String> responseListener_grouptable = new Response.Listener<String>() {// ************회원가입********************
                                    @Override
                                    public void onResponse(String response) {

                                        try {
                                            JSONObject jsonObject = new JSONObject( response );
                                            boolean success = jsonObject.getBoolean( "success" );

                                            if (success) {
                                                System.out.println(" ");
                                                System.out.println("===============System Message===============");
                                                System.out.println("그룹명 : " + plus_name);
                                                System.out.println("그룹코드 : " + randomV[0]);
                                                System.out.println("============================================");
                                            }

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                };
                                //서버로 Volley를 이용해서 요청
                                System.out.println("make_group 호출");
                                GroupRequest groupRequest = new GroupRequest( randomV[0], plus_name, responseListener_grouptable);
                                RequestQueue queue3 = Volley.newRequestQueue( Menu_MainActivity.this );
                                queue3.add(groupRequest);
                            }
                        },200);

                        delay.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Response.Listener<String> responseListener = new Response.Listener<String>() {// ************멤버테이블 생성********************
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonObject = new JSONObject( response );
                                            boolean success = jsonObject.getBoolean( "success" );
                                            if(success){
                                                System.out.println(" ");
                                                System.out.println("===============System Message===============");
                                                System.out.println("그룹 생성 완료 ["+plus_name+"("+randomV[0]+")] = "+user_code+" : "+1);
                                                System.out.println("============================================");
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };
                                //서버로 Volley를 이용해서 요청
                                MemberRequest memberRequest = new MemberRequest(user_code, randomV[0], 1, responseListener);
                                RequestQueue queue_member = Volley.newRequestQueue( Menu_MainActivity.this );
                                queue_member.add(memberRequest);
                            }
                        },400);

                        delay.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                aalertDialog.dismiss();
                                DrawerLayout drawer = findViewById(R.id.menu_drawer_layout);
                                NavigationView leftDrawerView = findViewById(R.id.menu_main_nav_leftview);
                                drawer.closeDrawer(Gravity.LEFT);
                                new DrawerTask().execute();
                            }
                        },600);

                    }
                });
            }
        });
        attend_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                View Pdialog = getLayoutInflater().inflate(R.layout.schedule_menu_attendgroup_fr,null);
                AlertDialog.Builder p_dialog=new AlertDialog.Builder(view.getContext());
                p_dialog.setView(Pdialog);
                final AlertDialog aalertDialog = p_dialog.create();
                aalertDialog.show();

                Button attendBtn = Pdialog.findViewById(R.id.schedule_menu_attendgroup_btn);

                attendBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        EditText et = Pdialog.findViewById(R.id.schedule_menu_attendgroup_et);
                        int attendCode = Integer.parseInt(et.getText().toString());

                        int stop = -1;

                        for(int i = 0; i < user_code_array.size(); i++) {
                            System.out.println("배열 : " + group_code_array.get(i) + "   입력 : " + attendCode);
                            System.out.println("배열 : " + user_code_array.get(i) + "   입력 : " + user_code);
                            if(stop== -1 && group_code_array.get(i) == attendCode){
                                stop = i;
                            }

                            if(user_code_array.get(i).equals(user_code)&&group_code_array.get(i).equals(attendCode)){
                                stop = -2;
                            }
                        }

                        if(stop == -1) {
                            Toast.makeText(getApplicationContext(), "존재하지 않는 그룹코드 입니다.", Toast.LENGTH_SHORT).show();
                            System.out.println("존재하지 않는 그룹코드 입니다.");
                        }

                        else if(stop == -2){
                            Toast.makeText(getApplicationContext(),"이미 참여되있는 그룹입니다.",Toast.LENGTH_SHORT).show();
                            System.out.println("이미 참여되있는 그룹입니다.");
                        }

                        else{
                            Response.Listener<String> responseListener = new Response.Listener<String>() {// ************멤버테이블 생성********************
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonObject = new JSONObject( response );
                                        boolean success = jsonObject.getBoolean( "success" );
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            };
                            MemberRequest memberRequest = new MemberRequest(user_code, attendCode, 0, responseListener);
                            RequestQueue queue_member = Volley.newRequestQueue( Menu_MainActivity.this );
                            queue_member.add(memberRequest);

                            Handler handler=new Handler();

                            handler.postDelayed(new Runnable(){
                                public void run(){
                                    aalertDialog.dismiss();
                                    DrawerLayout drawer = findViewById(R.id.menu_drawer_layout);
                                    NavigationView leftDrawerView = findViewById(R.id.menu_main_nav_leftview);
                                    drawer.closeDrawer(Gravity.LEFT);
                                    new DrawerTask().execute();
                                    saveGroup = attendCode;
                                }
                            },1000);

                        }
                    }
                });
            }
        });
    }

    public void print_left_drawer(){
        DrawerLayout drawer = findViewById(R.id.menu_drawer_layout);
        NavigationView leftDrawerView = findViewById(R.id.menu_main_nav_leftview);
        Menu left_menu = leftDrawerView.getMenu();

        left_menu.clear();
        able_group_code_array = new ArrayList<>();

        for(int i = 0; i < user_code_array.size(); i++)
        {
            if (user_code_array.get(i).equals(user_code))
            {
                left_menu.add(group_name_array.get(i));
                able_group_code_array.add(group_code_array.get(i));
            }
        }

        for(int j = 0; j < left_menu.size(); j++)
        {
            left_menu.getItem(j).setCheckable(true);
        }


        mainName = (TextView)findViewById(R.id.schedule_main_fr_name);
        leftDrawerView.setCheckedItem(0);
        init=1;
        group_code[0] = able_group_code_array.get(0);
        mainName.setText(left_menu.getItem(0).getTitle());


        drawer.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                int i,j;
                mainName = (TextView)findViewById(R.id.schedule_main_fr_name);

                for(i = 0; i < left_menu.size(); i++)
                {
                    if(left_menu.getItem(i).isChecked()){
                        for(j = 0; j < group_code_array.size(); j++){
                            if(group_name_array.get(j).equals(left_menu.getItem(i).getTitle().toString())){
                                group_code[0] = group_code_array.get(j);
                                mainName.setText(left_menu.getItem(i).getTitle());
                                print_right_drawer();
                                break;
                            }
                        }
                    }
                }
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

    }

    public void print_right_drawer(){
        DrawerLayout drawer = findViewById(R.id.menu_drawer_layout);
        NavigationView rightDrawerView = findViewById(R.id.menu_main_nav_rightview);
        Menu right_menu = rightDrawerView.getMenu();

        right_menu.clear();
        ArrayList<Integer> role = new ArrayList<>();

        for(int i = 0; i < group_code_array.size(); i++)
        {
            if(group_code_array.get(i).equals(group_code[0]))
            {
                right_menu.add(user_name_array.get(i));
                role.add(member_role_array.get(i));
            }
        }

        for(int j = 0; j < right_menu.size(); j++)
        {
            right_menu.getItem(j).setChecked(false);
            if(role.get(j).equals(1))
            {
                right_menu.getItem(j).setIcon(R.drawable.test);//<<<<이미지 수정해야됨
            }
        }
    }



    /*날짜 분할*/

//                String week = new SimpleDateFormat("EE").format(calendar.getTime());
//                String day_full = year + "."+ (month+1)  + "." + day + "." + week + "요일";
//                dayResult += (day_full + "\n");
    /*종료*/
}