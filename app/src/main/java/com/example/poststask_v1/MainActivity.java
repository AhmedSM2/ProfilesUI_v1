package com.example.poststask_v1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button showPosts_btn = findViewById(R.id.showPosts_btn);
        Button showProfile_btn = findViewById(R.id.showProfile_btn);
        Button showProfile2_btn = findViewById(R.id.profile2_btn);
        Button showProfile3_btn = findViewById(R.id.profile3_btn);

        showPosts_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserToPostsActivity();
            }
        });
        showProfile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserToProfileActivity();
            }
        });
        showProfile2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserToProfil2Activity();
            }
        });
        showProfile3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserToProfil3Activity();
            }
        });
    }
    private  void sendUserToPostsActivity()
    {
        Intent allPostsActivity = new Intent(getApplicationContext(),AllPostsActivity.class);
        startActivity(allPostsActivity);
    }
    private  void sendUserToProfileActivity()
    {
        Intent profile = new Intent(getApplicationContext(),Profile1.class);
        startActivity(profile);
    }
    private  void sendUserToProfil2Activity()
    {
        Intent profile2 = new Intent(getApplicationContext(),profile2.class);
        startActivity(profile2);
    }
    private  void sendUserToProfil3Activity()
    {
        Intent profile3 = new Intent(getApplicationContext(),Profile3.class);
        startActivity(profile3);
    }
}
