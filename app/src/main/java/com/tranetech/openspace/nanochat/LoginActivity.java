package com.tranetech.openspace.nanochat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText Name;
    Button StartChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Name = (EditText) findViewById(R.id.editText);
        StartChat = (Button) findViewById(R.id.button);

        StartChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Name.getText().toString().isEmpty()){

                    Toast.makeText(LoginActivity.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
                }else{

                    Intent intent =  new Intent(getApplicationContext(),MainActivity.class);
                    intent.putExtra("Name", Name.getText().toString());
                    startActivity(intent);
                }

            }
        });
    }
}
