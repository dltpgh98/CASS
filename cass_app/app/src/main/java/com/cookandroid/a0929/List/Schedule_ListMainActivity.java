package com.cookandroid.a0929.List;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.baoyz.widget.PullRefreshLayout;
import com.cookandroid.a0929.DB.DeleteScheduleRequest;
import com.cookandroid.a0929.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Schedule_ListMainActivity extends AppCompatActivity {

    private ListView listView;
    private ListViewAdapter adapter;
    private List<ListItem> scheduleList;
    private List<ListItem> saveList;
    /*선택 날짜 배열*/
    private int y_m_d[];

    private void delate_schedule(int schedule_code) {

        Response.Listener<String> responseListener_groupcode = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    System.out.println("delate_schedule" + response);
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");

                    if (success) {
                        Toast.makeText(getApplicationContext(), "삭제 되었습니다.", Toast.LENGTH_SHORT).show();
                    } else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        DeleteScheduleRequest deleteScheduleRequest = new DeleteScheduleRequest(schedule_code, responseListener_groupcode);
        RequestQueue queue = Volley.newRequestQueue(Schedule_ListMainActivity.this);
        queue.add(deleteScheduleRequest);

    }







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity_main);

        /*    날짜 받는것    */
        Intent intent = getIntent();
        y_m_d = intent.getIntArrayExtra("main_select_Day");
        int user_code = intent.getIntExtra("user_code", 0);
        int group_code = intent.getIntExtra("group_code", 0);
        int schedule_code = intent.getIntExtra("schedule_Code", 0);
        System.out.println("스케줄 코드"+schedule_code );
        String user_name = intent.getStringExtra("user_name");
        listView = (ListView) findViewById(R.id.listview);
        scheduleList = new ArrayList<ListItem>();
        saveList = new ArrayList<ListItem>();

        adapter = new ListViewAdapter(getApplicationContext(), scheduleList, this, saveList);
        listView.setAdapter(adapter);

        try {
            //intent로 값을 가져옵니다 이때 JSONObject타입으로 가져옵니다
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("scheduleList"));
            System.out.println(" 배열" + intent.getStringExtra("scheduleList"));

            //List.php 웹페이지에서 response라는 변수명으로 JSON 배열을 만들었음..
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count = 0;

            String title, sdate, edate, text, color, writer;
            int groupcode, schedulecode;

            //JSON 배열 길이만큼 반복문을 실행
            while (count < jsonArray.length()) {
                //count는 배열의 인덱스를 의미
                JSONObject object = jsonArray.getJSONObject(count);

                title = object.getString("schedule_title");//여기서 ID가 대문자임을 유의
                sdate = object.getString("schedule_sdate");
                edate = object.getString("schedule_edate");
                text = object.getString("schedule_text");
                color = object.getString("schedule_color");
                writer = object.getString("user_name");
                groupcode = object.getInt("group_code");
                schedulecode = object.getInt("schedule_code");

                //System.out.println("여기"+schedulecode);

                //값들을 User클래스에 묶어줍니다
                ListItem list = new ListItem(title, sdate, edate, text, color, writer, groupcode, schedulecode);
                //scheduleList.add(list);//리스트뷰에 값을 추가해줍니다
                saveList.add(list);
                count++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        searchUser(group_code);


        //엑티비티연결
        Button wp_btn = (Button) findViewById(R.id.schedule_list_pen_btn);
        wp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Schedule_ListMainActivity.this, Schedule_pen.class);
                //날짜 전달//
                intent.putExtra("main_select_Day",y_m_d);
                intent.putExtra("user_code", user_code);
                intent.putExtra("group_code", group_code);
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
    public void searchUser(int group_code) {
        scheduleList.clear();
        for (int i = 0; i < saveList.size(); i++) {
            System.out.println("---------------------------------------");
            System.out.println(saveList.get(i).getGroup_code());
            System.out.println(group_code);
            if (saveList.get(i).getGroup_code() == group_code) {//값이 있으면 true를 반환함
                scheduleList.add(saveList.get(i));
            }
        }
        adapter.notifyDataSetChanged();//어댑터에 값일 바뀐것을 알려줌
    }
}