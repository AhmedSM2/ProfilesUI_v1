package com.example.poststask_v1;

import java.util.ArrayList;
import java.util.Map;

public class Post {
    public String content, id,name;
    private Map<String,Like> Likes;

    public Post() {
    }

    public Post(String content, String name,String id) {
        this.content = content;
        this.name = name;
        this.id = id;

    }
}
