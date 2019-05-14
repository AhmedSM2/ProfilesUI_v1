package com.example.poststask_v1;

public class Like {
    public String user_name,post_id,likeCount,dlikeCount;
    public boolean type;

    public Like(String post_id) {
        this.user_name = "me";
        this.post_id = post_id;
    }

    public Like() {
    }
}
