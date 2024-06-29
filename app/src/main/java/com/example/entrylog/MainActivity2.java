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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity2 extends AppCompatActivity {
    EditText t1,t2,t3,t4;
    AppCompatButton b1,b2;
    String apiUrl="http://10.0.4.16:3000/api/students";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        t1=(EditText) findViewById(R.id.name);
        t2=(EditText) findViewById(R.id.addno);
        t3=(EditText) findViewById(R.id.sysno);
        t4=(EditText) findViewById(R.id.dept);

        b1=(AppCompatButton) findViewById(R.id.addbtn);
        b2=(AppCompatButton) findViewById(R.id.logoutbtn);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preference=getSharedPreferences("loginapp",MODE_PRIVATE);
                SharedPreferences.Editor editor=preference.edit();
                editor.clear();
                editor.apply();
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);

            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Value Reading
                String getName=t1.getText().toString();
                String getAddNo=t2.getText().toString();
                String getSysNo=t3.getText().toString();
                String getDept=t4.getText().toString();

                 //Creating JSON Object
                JSONObject student= new JSONObject();
                try {
                    student.put("name",getName);
                    student.put("admission_number",getAddNo);
                    student.put("system_number",getSysNo);
                    student.put("department",getDept);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                //JSON Object request creation
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.POST, apiUrl, student,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(getApplicationContext(), "added", Toast.LENGTH_LONG).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                            }
                        }

                );
                //Request Queue
                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(jsonObjectRequest);
            }
        });

    }
}