package com.cookandroid.a0929;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Menu_MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private AppBarConfiguration mAppBarConfiguration2;
    private long backKeyPressedTime = 0;
    public Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main);
        Toolbar toolbar = findViewById(R.id.menu_leftappbar_main_toolbar);
        setSupportActionBar(toolbar);


        /*좌측 햄버거 */
        DrawerLayout drawer = findViewById(R.id.menu_drawer_layout);
        NavigationView navigationView = findViewById(R.id.menu_main_nav_leftview);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_schedule_main_fr, R.id.nav_schedule_second_fr, R.id.nav_schedule_third_fr)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

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

        Button lp_btn = (Button) findViewById(R.id.schedule_main_fr_list_btn);
        lp_btn.setOnClickListener(new View.OnClickListener() {
            /*----------엑티비티연결--------------*/
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu_MainActivity.this,Schedule_list.class);
                startActivity(intent);
            }
        });
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
                drawer.openDrawer(Gravity.LEFT);
            }
        });
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
}