package com.example.project_ln.Main;

import androidx.appcompat.app.AppCompatActivity;
import com.example.project_ln.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    TextView txt_result;
    ImageView Im_result;
    Button back ;
    String _result ;
    Integer idPostFromBlog = null ;

    private static final String TAG = "DetailActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Log.d(TAG, "onCreate: called.");


        back= findViewById(R.id.b_backToList2);
        txt_result = findViewById(R.id.txt_result_detail);
        Im_result = findViewById(R.id.image_view);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back(v);
            }
        });

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String text = intent.getStringExtra("text");
        String imagePath = intent.getStringExtra("Image");
        String price = intent.getStringExtra("price");
        int id = intent.getIntExtra("userId",0 );
        int userId = intent.getIntExtra("id",0) ;

        /*Log.d(TAG, "onCreate: intent intent");*/
        Toast.makeText(DetailActivity.this, "you've been passed to detail activity , titled by: "+ title , Toast.LENGTH_SHORT).show();
        /*String UserID = intent.getExtras().getString("userId");
        String ID = intent.getExtras().getString("Id");
        String Title = intent.getExtras().getString("post_Title");
        String Text = intent.getExtras().getString("post_Text");*/

        //display image
        String finalString = "http://10.0.2.2:"+imagePath.substring(17) ;
        //picasso
        Picasso.get()
                .load(finalString)
                .fit()
                .centerInside()
                .into(Im_result);


        String content = "";
        /*content += "ID: " + id + "\n";
        content += "User ID : " + userId + "\n";*/
        content += "TITLE: " + title + "\n\n";
        content += "PRICE: " + price + "\n\n";
        content += "DESCRIPTION: " + text + "\n\n";

        txt_result.setText(content);


       /* Bundle extras = getIntent().getExtras();
        if (extras != null) {
            _result = extras.getString("mContact");
        Toast.makeText(DetailActivity.this, "Details " + _result, Toast.LENGTH_LONG).show();
        }*/
        /*String detailContact = getIntent().getStringExtra("mContact");*/
    }
    public void getPostById(Integer idPostFromBlog){
        JsonObject object = new JsonObject();
        object.addProperty("id", idPostFromBlog);

    }

    public void back(View v ){
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();
    }



}
