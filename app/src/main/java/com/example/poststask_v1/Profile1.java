package com.example.poststask_v1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Profile1 extends AppCompatActivity {
    ArrayList<Post> postsList = new ArrayList<>();
    private DatabaseReference userData ,Num_userFriends,Numfollowers;
    int sum=0 ;
    int followers1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile1);

        final Button follow_btn = findViewById(R.id.profile_follow_btn);
        final TextView username = findViewById(R.id.profile_userName);
        final TextView email = findViewById(R.id.profile_email);
        final TextView phone = findViewById(R.id.profile_phone);
        final TextView friends = findViewById(R.id.profile_friends);
        final TextView follower_number = findViewById(R.id.profile_followers);

        String userId ="-Lc8MPK5ib1txlUQ11zI";
        Numfollowers = FirebaseDatabase.getInstance().getReference().child("followers");
        userData = FirebaseDatabase.getInstance().getReference().child("Post/"+userId);
        Num_userFriends = FirebaseDatabase.getInstance().getReference().child("Post");

        userData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Post post = dataSnapshot.getValue(Post.class);
                username.setText(post.name);
                phone.setText(post.id);
                email.setText(post.content);
          //      Log.w("myApp", post.name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Num_userFriends.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot ds : dataSnapshot.getChildren()){
////                    sum+=1;
////                }
////                friends.setText(Integer.toString(sum));
                sum = (int) dataSnapshot.getChildrenCount();
                friends.setText(Integer.toString(sum));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Numfollowers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                followers1 = (int) dataSnapshot.getValue(Integer.class);
                follower_number.setText(Integer.toString(followers1));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        follow_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                followers1+=1;
                Numfollowers.setValue(followers1);

            }
        });
    }
}
