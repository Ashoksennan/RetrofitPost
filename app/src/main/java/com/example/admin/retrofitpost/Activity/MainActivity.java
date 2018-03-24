package com.example.admin.retrofitpost.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.retrofitpost.Network.RetrofitInterface;
import com.example.admin.retrofitpost.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {
EditText tv_username,tv_password,tv_name,tv_email;
Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_username = (EditText)findViewById(R.id.tv_username);
        tv_password = (EditText)findViewById(R.id.tv_password);
        tv_name = (EditText)findViewById(R.id.tv_name);
        tv_email = (EditText)findViewById(R.id.tv_email);
        register = (Button)findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertuser();
            }
        });

    }
    public void insertuser(){
        //Retrofit name was renamed as rest adapter in before versions and way of creating objects are modified later
        RestAdapter retrofit = new RestAdapter.Builder().setEndpoint("http://arid-conductor.000webhostapp.com")
                                .build();
        //creating object for interface
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        Log.e(" fghf",tv_name.getText().toString());
        Log.e(" fghf",tv_username.getText().toString());
        Log.e(" fghf",tv_email.getText().toString());
        Log.e(" fghf",tv_password.getText().toString());
        retrofitInterface.insertdatas(tv_name.getText().toString(), tv_username.getText().toString(), tv_email.getText().toString(), tv_password.getText().toString(),
                new Callback<Response>() {
                    @Override
                    public void success(Response response, Response response2) {
                        //we need to read the output from server using bufferreader
                        BufferedReader bufferedReader = null;
                        //a string to store the buffer reader values
                        String output = "";
                        try {
                            bufferedReader = new BufferedReader(new InputStreamReader(response.getBody().in()));
                            output = bufferedReader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(MainActivity.this, output, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
