package com.example.pcgur.bejelentkezes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Register extends AppCompatActivity {
    @BindView(R.id.btn_register) Button btn_register;
    @BindView(R.id.edt_usernameinput) EditText edt_usernameinput;
    @BindView(R.id.edt_passwordinput) EditText edt_passwordinput;
    @BindView(R.id.edt_fullnameinput) EditText edt_fullnameinput;
    private DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        actions();
    }
    private void actions(){
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                felhasznalofelvetel();

            }
        });
    }
    private void felhasznalofelvetel(){
        myDb = new DatabaseHelper(this);
        if (!EmptyWidget())
        {
            String felhasznalonev = edt_usernameinput.getText().toString();
            String password = edt_passwordinput.getText().toString();
            String fullname = edt_fullnameinput.getText().toString();
            Boolean eredmeny = myDb.adatRogzites(felhasznalonev,password,fullname);

            if (eredmeny) {
                Toast.makeText(Register.this, "Adatrögzítés sikeres!", Toast.LENGTH_SHORT).show();
                Intent gonext = new Intent(Register.this, MainActivity.class);
                startActivity(gonext);
                finish();
            }
        }else
        {
            Toast.makeText(Register.this, "Minden mezőt ki kell tölteni!", Toast.LENGTH_SHORT).show();
        }
    }
    private boolean EmptyWidget()
    {
        if (edt_fullnameinput.getText().toString().equals("") ||
                edt_passwordinput.getText().toString().equals("") ||
                edt_usernameinput.getText().toString().equals(""))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
