package com.cookandroid.a0929.List;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.baoyz.widget.PullRefreshLayout;
import com.cookandroid.a0929.R;

public class Schedule_ListMainActivity extends AppCompatActivity {

    private ListView listView;
    private ListViewAdapter adapter;
    /*선택 날짜 배열*/
    private int y_m_d[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity_main);

        /*    날짜 받는것    */
        Intent intent = getIntent();
        y_m_d = intent.getIntArrayExtra("main_select_Day");
        int user_code = intent.getIntExtra("user_code", 0);

        //엑티비티연결
        Button wp_btn = (Button) findViewById(R.id.schedule_list_pen_btn);
        wp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Schedule_ListMainActivity.this, Schedule_pen.class);
                //날짜 전달//
                intent.putExtra("main_select_Day",y_m_d);
                intent.putExtra("user_code",user_code);
                startActivity(intent);
                finish();
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

        adapter = new ListViewAdapter(Schedule_ListMainActivity.this);
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
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                // create "Close" item
                SwipeMenuItem openItem = new SwipeMenuItem(getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9,0xC9,0xCE)));
                // set item width
                openItem.setWidth(200);
                // set item title
                openItem.setTitle("수정");
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // set item title fontsize
                openItem.setTitleSize(18);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(200);
                // set a icon
                deleteItem.setIcon(R.drawable.icon_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        SwipeMenuListView listview;
        listview =findViewById(R.id.listview);
        listview.setAdapter(adapter);   // 리스트가 뷰 된 이후에 다음 추가한다.
        listview.setMenuCreator(creator);

        listview.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {
                // swipe start
                listview.smoothOpenMenu(position);
            }

            @Override
            public void onSwipeEnd(int position) {
                // swipe end
                listview.smoothOpenMenu(position);
            }
        });
        listview.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // open
                        //Toast.makeText(getApplicationContext(),"test3", Toast.LENGTH_SHORT).show();
                        System.out.println(adapter.getCount());
                        Object fixItem = adapter.getItem(0);

                        break;
                    case 1:
                        // delete
                        //여기에 삭제 버턴 클릭시 코딩
                        long selectedid = adapter.getItemId(position);
                        int deleteid = (int) selectedid;

                        AlertDialog.Builder builder = new AlertDialog.Builder(Schedule_ListMainActivity.this);
                        builder.setTitle("삭제");
                        builder.setMessage("정말 삭제하시겠습니까?");
                        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                adapter.deleteItem(deleteid);
                            }
                        });
                        builder.setNegativeButton("아니오",null);
                        builder.setNeutralButton("취소",null);
                        builder.create().show();

                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
        //당겨서 새로고침
        PullRefreshLayout PullRefreshLayout = (PullRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        PullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.notifyDataSetChanged();
                PullRefreshLayout.setRefreshing(false);
                //PullRefreshLayout.setRefreshStyle(com.baoyz.widget.PullRefreshLayout.STYLE_CIRCLES);
                //PullRefreshLayout.setRefreshStyle(com.baoyz.widget.PullRefreshLayout.STYLE_MATERIAL;
                //PullRefreshLayout.setRefreshStyle(com.baoyz.widget.PullRefreshLayout.STYLE_WATER_DROP);
                //PullRefreshLayout.setRefreshStyle(com.baoyz.widget.PullRefreshLayout.STYLE_RING);

            }
        });
    }
}