package com.example.poststask_v1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllPostsActivity extends AppCompatActivity {
    private DatabaseReference mPost ;
    final ArrayList<Post>postsList = new ArrayList<>();
    String postTempId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_posts);
        Button btn_send = findViewById(R.id.btn_sendPost);
        final EditText postContent = findViewById(R.id.postContent);
        final ListView listView = findViewById(R.id.PostsList);
        final CustomeAdpater customeAdpater = new CustomeAdpater();
        mPost = FirebaseDatabase.getInstance().getReference().child("Post");
        listView.setAdapter(customeAdpater);

        // Add Post
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = mPost.child("Post").push().getKey();
                Post post = new Post(postContent.getText().toString(),"ahmed shaaban",id);
                mPost.child(id).setValue(post);
                postContent.setText("");
            }
        });

        // read the post from firebase
        mPost.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Post postData = dataSnapshot.getValue(Post.class);
                postsList.add(0,postData);
                customeAdpater.notifyDataSetChanged();
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    // Custome list view
    class CustomeAdpater extends BaseAdapter {
        boolean checklike = false;
        @Override
        public int getCount() {
            return postsList.size();
        }
        @Override
        public Object getItem(int position) {
            return null;
        }
        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.posts_structure,null);
            TextView p_name = convertView.findViewById(R.id.pUserName);
            TextView p_conent = convertView.findViewById(R.id.pPostContent);
            TextView t_likes= convertView.findViewById(R.id.pLikeCount);
            TextView t_disLikes= convertView.findViewById(R.id.pdLikeCount);
            final ImageButton like = convertView.findViewById(R.id.pLike);
            final ImageButton dislike = convertView.findViewById(R.id.pdLike);
            final String postId =mPost.child("/"+postsList.get(position).id).getKey();
            final ImageButton Menu = convertView.findViewById(R.id.postMenu);
            final Like like1 = new Like(postId);
            // Post Menu that include edit , remove and report
            Menu.setOnClickListener(new View.OnClickListener() {
                String postContent=  postsList.get(position).content;
                @Override
                public void onClick(View v) {
                    PopupMenu popupMenu = new PopupMenu(getApplicationContext(),v);
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()){
                                case R.id.item1:
                                    sendUserToEditPostActivity(postContent,postId);

                                case R.id.item2:
                                    mPost.child(postId).removeValue();
                                    Post p = new Post();
                                    p = postsList.get(position);
                                    postsList.remove(p);
                                    notifyDataSetChanged();
                                    return true;
                                case R.id.item3:
                                    Toast.makeText(AllPostsActivity.this, "Item3 :)",
                                            Toast.LENGTH_SHORT).show();
                                    return true;
                                default: return  false;
                            }
                        }
                    });
                    popupMenu.inflate(R.menu.menu_post);
                    popupMenu.show();
                }
            });

            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dislike.setColorFilter(getResources().getColor(R.color.colorLikeFaceDefault));
                    like.setColorFilter(getResources().getColor(R.color.colorLikeFace));
                    checklike = true;
                    mPost.child(postId).child("Likes").push().setValue(like1);
                    mPost.child(postId).child("Likes/likeCount").setValue("5");

                    checklike = true;
                }
            });
            dislike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    like.setColorFilter(getResources().getColor(R.color.colorLikeFaceDefault));
                    dislike.setColorFilter(getResources().getColor(R.color.colorLikeFace));
                    checklike = false;
                    mPost.child(postId).child("DisLikes").push().setValue("false");
                }
            });
            p_name.setText(postsList.get(position).name);
            p_conent.setText(postsList.get(position).content);
            return convertView;
        }

    }

    private  void sendUserToEditPostActivity(String content, String id)
    {
        Intent editPostActivity = new Intent(getApplicationContext(),EditPostActivity.class);
        editPostActivity.putExtra("Content",content);
        editPostActivity.putExtra("PostId",id);
        startActivity(editPostActivity);
    }
}
