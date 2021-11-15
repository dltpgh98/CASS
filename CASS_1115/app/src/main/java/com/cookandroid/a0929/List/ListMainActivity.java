package com.cookandroid.a0929.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.cookandroid.a0929.R;
import com.cookandroid.a0929.Schedule_pen;

public class ListMainActivity extends AppCompatActivity {

    private ListView listView;
    private ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity_main);

        //엑티비티연결
        Button wp_btn = (Button) findViewById(R.id.schedule_list_pen_btn);
        wp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListMainActivity.this, Schedule_pen.class);
                startActivity(intent);
            }
        });
        //엑티비티연결
        Button back_btn = (Button) findViewById(R.id.schedule_list_close_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        listView = (ListView) findViewById(R.id.listview);

        adapter = new ListViewAdapter(ListMainActivity.this);
        listView.setAdapter(adapter);

        // 데이터 추가하기
        Intent getintent = getIntent();
        String s1 = getintent.getStringExtra("제목");
        String s2 = getintent.getStringExtra("시작날짜");
        String s3 = getintent.getStringExtra("종료날짜");
        String s4 = getintent.getStringExtra("메모");
        String s5 = getintent.getStringExtra("컬러");
        String s6 = getintent.getStringExtra("작성자");
        if(s1!=null) {
            adapter.addItem(s1, s2, s3, s4, s5, s6);
            adapter.notifyDataSetChanged();
        };
    }
}