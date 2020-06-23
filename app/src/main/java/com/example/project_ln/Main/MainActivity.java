package com.example.project_ln.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.project_ln.Model.Book;
import com.example.project_ln.Model.Products;
import com.example.project_ln.R;
import com.example.project_ln.Retrofit.ApiUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ContactsAdapter.OnPostListener {
    private RecyclerView mRecycleView;
    private ImageButton toAddBtn;
    private RecyclerView.LayoutManager mLayoutManager;
    private ContactsAdapter mAdapter;
    ArrayList<Book> contacts;
    /* ArrayList<Contact> posts;*/
    Products contactss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


/*
        Contact postt = new Contact("5e88d92d9a620e0e2010c450",23, Et_title.getText().toString(), Et_text.getText().toString(),"http://localhost:3000/images/user1-1586083538673.png");
*/
        /* Contact post = new Contact("",23, "New Title", "New Text");*/
        toAddBtn = (ImageButton) findViewById(R.id.btnToAdd);

        //getPosts
        // <List<Contact>>
        ApiUtil.getServiceClass().getpostList().enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                if (!response.isSuccessful()) {
                    /*  textViewResult.setText("Code: "+ response.code());*/
                    Toast.makeText(MainActivity.this, "code " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                contactss = response.body();
                assert contactss != null;
                initRecyclerview(contactss.getPosts());
                System.out.println("in the call "+contactss.toString());
                Log.d(TAG, "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                System.out.print(t.getMessage());
                Toast.makeText(MainActivity.this, "code " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        toAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });


    }

    private void initRecyclerview(List contactss) {
        mRecycleView = findViewById(R.id.mRecyclerview);
        toAddBtn = findViewById(R.id.btnToAdd);
        mRecycleView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        //

        /* List<Contact> items = new ArrayList<Contact>();
        for(int i = 0; i < mAdapter.getItemCount(); i++){
            items.add(mAdapter.getItem(i));
        }*/
        // Initialize contacts
        System.out.println("posts in the recycler view:" + contactss);
        Log.d(TAG, "initRecyclerview: " + contactss);
        mAdapter = new ContactsAdapter(contactss, this);
        mRecycleView.setLayoutManager(mLayoutManager);
        mRecycleView.setAdapter(mAdapter);
        /*mAdapter.OnPostListener(contacts);*/
    }


    private static final String TAG = "RecyclerviewActivity";

    /*int idd = contacts.get().getUserId();*/
    @Override
    public void onPostClick(int position) {
        /*List<Contact> items = new ArrayList<Contact>();*/
        /*contacts.get(position);*/
        Log.d(TAG, "onPostClick: clickeddddddd." + position);
        System.out.println("contactssssssssssssssssssssssssssssssssss :" + contactss.toString());
        Intent detailintent = new Intent(MainActivity.this, DetailActivity.class);

        Book clickedPost = (Book) contactss.getPosts().get(position);
        System.out.println("contacts.get(position)" + contactss.getPosts().get(position));
        System.out.println("contactss.toString()........:" + contactss.toString());
        System.out.println("clickedPost  ........  :" + clickedPost);
        detailintent.putExtra("title", clickedPost.getTitle());
        detailintent.putExtra("text", clickedPost.getContent());
        detailintent.putExtra("price", clickedPost.getPrice());
        detailintent.putExtra("userId", clickedPost.getCreator());
        detailintent.putExtra("id", clickedPost.getId());
        detailintent.putExtra("Image", clickedPost.getImagePath());

        startActivity(detailintent);


    }
}
