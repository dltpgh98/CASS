package com.cookandroid.a0929.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.cookandroid.a0929.DB.FindRequest;
import com.cookandroid.a0929.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    private Context mContext;
    private List<ListItem> listItems;
    private List<ListItem> saveList;
    private int group_code;
    private Activity parentActivity;


    public ListViewAdapter(Context context, List<ListItem> listItems, Activity parentActivity, List<ListItem> saveList){
        this.mContext = context;
        this.listItems = listItems;
        this.saveList = saveList;
        this.parentActivity = parentActivity;

    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int i) {
        return listItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void deleteItem(int i){
        listItems.remove(i);
        notifyDataSetChanged();
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        // item.xml 레이아웃을 inflate해서 참조획득
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }


        // item.xml 의 참조 획득
        TextView txt_title = (TextView)convertView.findViewById(R.id.txt_title);
        TextView txt_sdate = (TextView)convertView.findViewById(R.id.txt_sdate);
        TextView txt_edate = (TextView)convertView.findViewById(R.id.txt_edate);
        TextView txt_memo = (TextView)convertView.findViewById(R.id.txt_memo);
        TextView txt_color = (TextView)convertView.findViewById(R.id.txt_color);
        TextView txt_writer = (TextView)convertView.findViewById(R.id.txt_writer);

        ListItem listItem = listItems.get(position);

        // 가져온 데이터를 텍스트뷰에 입력
        txt_title.setText(listItem.getTitle());
        txt_sdate.setText(listItem.getSdate());
        txt_edate.setText(listItem.getEdate());
        txt_memo.setText(listItem.getMemo());
        txt_color.setText(listItem.getColor());
        txt_writer.setText(listItem.getWriter());
        //
        txt_color.setBackgroundColor(Color.parseColor(listItem.getColor()));


        return convertView;
    }
//
//    public void addItem(String title, String sdate, String edate, String memo, String color, String writer){
//        ListItem listItem = new ListItem();
//        listItem.setTitle(title);
//        listItem.setSdate(sdate);
//        listItem.setEdate(edate);
//        listItem.setMemo(memo);
//        listItem.setColor(color);
//        listItem.setWriter(writer);
//        listItems.add(listItem);
//    }
}