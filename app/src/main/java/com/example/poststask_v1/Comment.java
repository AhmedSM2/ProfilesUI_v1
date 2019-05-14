package com.example.poststask_v1;

public class Comment {
    private String comment_body;
    private String comment_id;
    private String Rate;

    public Comment() {
    }

    public Comment(String comment_id) {
        this.comment_id = comment_id;
    }

    public Comment(String comment_body, String comment_id, String rate) {
        this.comment_body = comment_body;
        this.comment_id = comment_id;
        Rate = "";
    }


    public String getComment_body() {
        return comment_body;
    }

    public void setComment_body(String comment_body) {
        this.comment_body = comment_body;
    }

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public String getRate() {
        return Rate;
    }

    public void setRate(String rate) {
        Rate = rate;
    }


}
