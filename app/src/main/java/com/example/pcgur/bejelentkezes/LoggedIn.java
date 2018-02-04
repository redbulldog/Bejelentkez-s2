package com.example.pcgur.bejelentkezes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoggedIn extends AppCompatActivity {
    @BindView(R.id.txt_name) TextView txt_name;
    @BindView(R.id.btn_logout) Button btn_logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        ButterKnife.bind(this);
        SharedPreferences sharedPreferences = getSharedPreferences("Name", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("name","");

        txt_name.setText("Üdvözöllek " + name);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logout = new Intent(LoggedIn.this, MainActivity.class);
                startActivity(logout);
            }
        });
    }
}
