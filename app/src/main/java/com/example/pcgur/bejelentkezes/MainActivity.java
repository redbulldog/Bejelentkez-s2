package com.example.pcgur.bejelentkezes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btn_register) Button btn_register;
    @BindView(R.id.btn_login) Button btn_login;
    @BindView(R.id.edt_username) EditText edt_username;
    @BindView(R.id.edt_password) EditText edt_password;
    private DatabaseHelper myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        actions();

    }
    private void actions(){
        myDb = new DatabaseHelper(this);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(MainActivity.this, Register.class);
                startActivity(register);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ellenorzes();
            }
        });
    }
    private void ellenorzes(){

        Cursor res = myDb.adatLekerdezes();
        StringBuffer stringBuffer = new StringBuffer();
        res.moveToFirst();
        if (res!=null && res.getCount()>0)
        {
            while (res.moveToNext())
            {
                // stringbuffer = egy hosszú string amihez hozzá fűzzük (appendeljük) a változókat

                if (res.getString(1).toString().equals(edt_username.getText().toString()))
                {
                    if (res.getString(2).toString().equals(edt_password.getText().toString())){
                        SharedPreferences sharedPreferences = getSharedPreferences("Name", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("name",res.getString(3).toString());
                        editor.commit();
                        Intent loggedin = new Intent(MainActivity.this, LoggedIn.class);
                        startActivity(loggedin);
                    } else{
                        Toast.makeText(MainActivity.this, "Rossz a jelszó", Toast.LENGTH_SHORT).show();
                    }
                } else {}

            }
        }else
        {
            Toast.makeText(MainActivity.this, "Nincs adat, amit le lehetne kérdezni!", Toast.LENGTH_SHORT).show();
        }
    }
}
