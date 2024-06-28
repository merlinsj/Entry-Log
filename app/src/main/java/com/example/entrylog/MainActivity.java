package com.example.entrylog;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText t1,t2;
    AppCompatButton b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        SharedPreferences preference = getSharedPreferences("loginapp",MODE_PRIVATE);
        String username=preference.getString("user",null);
        if(username!=null)
        {
            Intent i=new Intent(getApplicationContext(),MainActivity2.class);
            startActivity(i);
        }
        t1=(EditText) findViewById(R.id.uname);
        t2=(EditText) findViewById(R.id.pass);
        b1=(AppCompatButton) findViewById(R.id.logbtn);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getUsername=t1.getText().toString();
                String getPassword=t2.getText().toString();
                if(getUsername.equals("admin") && getPassword.equals("12345"))
                {
                    SharedPreferences preference=getSharedPreferences("loginapp",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preference.edit();
                    editor.putString("user","admin");
                    editor.apply();
                    Intent i=new Intent(getApplicationContext(),MainActivity2.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid credentials",Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}