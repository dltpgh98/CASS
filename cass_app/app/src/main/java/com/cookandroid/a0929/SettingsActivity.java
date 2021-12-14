package com.cookandroid.a0929;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsActivity extends AppCompatActivity {
    SharedPreferences pref;
    static int group_code ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_main);
        Intent intent =getIntent();
        group_code = intent.getIntExtra("group_code", 0);
        /*----------변경사항 자동저장--------(화면 회전시 데이터 손실방지)*/
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        /*-------툴바 설정--------*/
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
    /*-------뒤로가기키 추가--------*/
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:{ // 툴바의 back키 눌렀을때
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.set_list, rootKey);


            EditTextPreference signaturePreference_code = findPreference("setting_code");

            signaturePreference_code.setTitle("code: " +group_code);

            //방장권한
            int i =0; // 방장코드 확인
            /*if (i==0) { //방장코드 확인
                System.out.println(signaturePreference_pw);
                if (signaturePreference_ban != null) {
                    signaturePreference_ban.setVisible(true);
                }if (signaturePreference_intercept != null) {
                    signaturePreference_intercept.setVisible(true);
                }
            }*/
        }
    }
}