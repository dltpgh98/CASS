package com.cookandroid.a0929.List;

public class ListItem {

    private String title;
    private String sdate;
    private String edate;
    private String memo;
    private String color;
    private String writer;
    private int group_code;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public String getEdate() {
        return edate;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getGroup_code() {
        return group_code;
    }

    public void setGroup_code(int group_code) {
        this.group_code = group_code;
    }

    public ListItem(String title, String sdate, String edate, String memo, String color, String writer, int group_code) {
        this.title = title;
        this.sdate = sdate;
        this.edate = edate;
        this.memo = memo;
        this.color = color;
        this.writer = writer;
        this.group_code = group_code;
    }
}