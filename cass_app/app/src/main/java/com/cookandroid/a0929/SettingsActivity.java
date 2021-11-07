package com.cookandroid.a0929;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsActivity extends AppCompatActivity {
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_main);

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
        }
    }
    /*----------preference에 저장된 값 가져오기------*/
    //@Override
    //public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
    //setPreferencesFromResource(R.xml.preferences, rootKey);
    //EditTextPreference signaturePreference = findPreference("signature");
    //}// do something with this preference
}
