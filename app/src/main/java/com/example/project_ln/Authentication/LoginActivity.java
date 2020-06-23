package com.example.project_ln.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.project_ln.DashboardActivity;
import com.example.project_ln.Retrofit.ApiUtil;
import com.example.project_ln.Retrofit.RetrofitInterface;
import com.example.project_ln.R;

import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.rengwuxian.materialedittext.MaterialEditText;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    TextView txt_create_account;
    MaterialEditText edt_login_email,edt_login_password;
    Button btn_login;


    CompositeDisposable compositeDisposable = new CompositeDisposable();
    RetrofitInterface iMyService;

    @Override
    protected void onStop () {
        compositeDisposable.clear();
        super.onStop();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //init service
        Retrofit retrofitClient = ApiUtil.getInstance();
        iMyService = retrofitClient.create(RetrofitInterface.class);

        //init view
        edt_login_email= (MaterialEditText)findViewById(R.id.edt_email);
        edt_login_password= (MaterialEditText)findViewById(R.id.edt_password);

        btn_login = (Button)findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {


                    loginUser(edt_login_email.getText().toString(),
                            edt_login_password.getText().toString());
                }catch (Exception ex){System.out.println("error 1: "+ ex);}
            }
        });

        txt_create_account = (TextView)findViewById(R.id.btn_cretae_account);
        txt_create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View register_layout = LayoutInflater.from(LoginActivity.this)
                        .inflate(R.layout.register_layout,null);

                new MaterialStyledDialog.Builder(LoginActivity.this)

                        .setTitle("REGISTRATION")
                        .setDescription("please fill all fields")
                        .setCustomView(register_layout)
                        .setNegativeText("CANCEL")
                        .onNegative((dialog, which) -> dialog.dismiss())
                        .setPositiveText("REGISTER")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                MaterialEditText edt_register_email = (MaterialEditText)register_layout.findViewById(R.id.edt_email);
//                                MaterialEditText edt_register_name = (MaterialEditText)register_layout.findViewById(R.id.edt_name);
                                MaterialEditText edt_register_password = (MaterialEditText)register_layout.findViewById(R.id.edt_password);

                                if (TextUtils.isEmpty(edt_register_email.getText().toString())){
                                    Toast.makeText(LoginActivity.this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
                                    return;
                                }
//                                if (TextUtils.isEmpty(edt_register_name.getText().toString())){
//                                    Toast.makeText(MainActivity.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
//                                    return;
//                                }
                                if (TextUtils.isEmpty(edt_register_password.getText().toString())){
                                    Toast.makeText(LoginActivity.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                registerUser(edt_register_email.getText().toString(),
                                        edt_register_password.getText().toString());
                            }
                        }).show();
            }
        });

    }

    private void registerUser(String email, String password) {

        compositeDisposable.add(iMyService.registerUser(email ,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String response) throws Exception {
                        Toast.makeText(LoginActivity.this, "user registered successfully", Toast.LENGTH_SHORT).show();
                    }
                }));
    }


    private void loginUser(String email, String password) {
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        compositeDisposable.add(iMyService.loginUser(email,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String response) throws Exception {
                        Toast.makeText(LoginActivity.this, "login successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                        /* Log.d("response", "accept: reponse " + response );*/
                        String  responseLogin = response;
                        Log.d("String ========== response", "accept: reponse " + responseLogin );
                        intent.putExtra("response", responseLogin );
/*
                        String userString = (Contact) this.User.toString();
                        System.out.println("contacts.get(position)"+contactss.getPosts().get(position));
                        System.out.println("contactss.toString()........:" + contactss.toString());
                        intent.putExtra("user", userString);*/
                        startActivity(intent);
                        /*setContentView(R.layout.activity_recyclerview);*/
                    }
                }));
    }
    //-----------------------------------------------------------------------------------------------------------
    /* *//* private SessionManager sessionManager;*//*
    private EditText emailEt, passwordEt;
    private Button loginBtn, toSignUpBtn;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // User Session Manager
        *//*sessionManager = new SessionManager(getApplicationContext());*//*

        emailEt = findViewById(R.id.etEmailLogin);
        passwordEt = findViewById(R.id.etPasswordLogin);
        loginBtn = findViewById(R.id.btnLogin);
        toSignUpBtn = findViewById(R.id.btnToSignUp);

        toSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateInputs()){
                    displayLoader();
                    *//*login();*//*
                }
            }
        });

    }

   *//* public void login() {

        JsonObject object = new JsonObject();
        object.addProperty("email", emailEt.getText().toString().trim());
        object.addProperty("password", passwordEt.getText().toString().trim());
        ApiUtil.getServiceClass().login(object).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                pDialog.dismiss();
                Gson gson = new Gson();
                JsonObject myJsonResponse = new JsonObject();
                myJsonResponse.getAsJsonObject(gson.toJson(response.body()));
                boolean existe = gson.toJson(response.body()).contains("false");
                if (existe) {
                    pDialog.dismiss();
                    emailEt.setError("bad credentials");
                    emailEt.requestFocus();
                } else {
                    User responseUser = gson.fromJson(response.body(), User.class);
                    sessionManager.createUserLoginSession(gson.toJson(responseUser));
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                pDialog.dismiss();
                Toast.makeText(LoginActivity.this, "please connect to the internet", Toast.LENGTH_SHORT).show();
            }
        });
    }*//*

    private boolean validateInputs() {
        if (passwordEt.getText().toString().equals("")) {
            passwordEt.setError("required");
            passwordEt.requestFocus();
            return false;
        }
        if (emailEt.getText().toString().equals("")) {
            emailEt.setError("required");
            emailEt.requestFocus();
            return false;
        }

        return true;
    }

    private void displayLoader() {
        pDialog = new ProgressDialog(LoginActivity.this);
        pDialog.setMessage("Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }*/





}

