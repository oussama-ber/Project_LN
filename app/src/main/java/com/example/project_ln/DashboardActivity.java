package com.example.project_ln;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.project_ln.Authentication.LoginActivity;
import com.example.project_ln.Main.AddActivity;
import com.example.project_ln.Main.MainActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener{
    private CardView login,university, MSB , MEDTECH , LCI , ContactUS, Events, Jobs ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        login  = (CardView) findViewById(R.id.CD_login);

        MSB  = (CardView) findViewById(R.id.CD_msb);


        Events  = (CardView) findViewById(R.id.CD_events);


        //set onclicklistener
        login.setOnClickListener(this);
        MSB.setOnClickListener(this);
        Events.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent intent ;
        switch (v.getId()){
            case R.id.CD_login : intent =
                    new Intent(DashboardActivity.this, LoginActivity.class);
                startActivity(intent);  break;

            case R.id.CD_events : intent =
                    new Intent(DashboardActivity.this, AddActivity.class);
                startActivity(intent); break;
            case R.id.CD_msb : intent =
                    new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(intent); break;
            //still
            /*case R.id.CD_msb : intent = new Intent(DashboardActivity.this,LoginActivity.class);
            case R.id.Cd_medtech : intent = new Intent(DashboardActivity.this,LoginActivity.class);
            case R.id.CD_lci : intent = new Intent(DashboardActivity.this,LoginActivity.class);*/
        }

    }
}
