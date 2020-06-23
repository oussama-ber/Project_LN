package com.example.project_ln.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project_ln.Model.Book;
import com.example.project_ln.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_ln.JsonPlaceHolderApi;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddActivity extends AppCompatActivity {
    private Button addBtn;
    private Button back ;

    private EditText Et_title;
    private EditText Et_text;

    private TextView textViewResult;

    private JsonPlaceHolderApi jsonPlaceHolderApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);




        //add post button
        addBtn = findViewById(R.id.btnAdd);
        //read title, text
        Et_title = findViewById(R.id.etName);
        Et_text = findViewById(R.id.et_text);
        // bach button
        back =(Button) findViewById(R.id.b_backToList);


        // force retrofit to put value null in the gson variable in it when do some update.
        Gson gson = new GsonBuilder().serializeNulls().create();

        // interceptor
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {

                    @Override
                    public okhttp3.Response intercept( Chain chain) throws IOException {
                        Request originalRequest = chain.request();

                        Request newRequest = originalRequest.newBuilder()
                                .header("Interceptor-header","xyz")
                                .build();
                        return chain.proceed(newRequest);
                    }
                })
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        jsonPlaceHolderApi = retrofit.create((JsonPlaceHolderApi.class));

        //textview display result
        textViewResult = findViewById(R.id.txt_result);


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPost();
                Toast.makeText(AddActivity.this, "Post added successfully, titled by: "+Et_title.getText(), Toast.LENGTH_SHORT).show();

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back(v);
            }
        });
    }

    public void back(View v ){
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    //create post!
    private void createPost() {
        Book post = new Book("5ea5f18f9685a4552603a1416",Et_title.getText().toString(), Et_text.getText().toString()," http://localhost:3000/images/user1-1586083538673.png","5ee51f477488c8acbd58998f","120");
        Call<Book> call = jsonPlaceHolderApi.createPost(post);

        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                Book postResponse = response.body();

                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "ID: " + postResponse.getId() + "\n";
                content += "User ID : " + postResponse.getCreator() + "\n";
                content += "Title : " + postResponse.getTitle() + "\n";
                content += "Price : " + postResponse.getPrice() + "\n";
                content += "Text : " + postResponse.getContent() + "\n\n";

                textViewResult.setText(content);
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                textViewResult.setText(t.getMessage());

            }
        });
    }

}
