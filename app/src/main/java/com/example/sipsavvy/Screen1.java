package com.example.sipsavvy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Screen1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen1);

        EditText UserName = findViewById(R.id.UserName);
        EditText UserAge = findViewById(R.id.UserAge);
        EditText UserWeight = findViewById(R.id.UserWeight);

        Button submitButton = findViewById(R.id.submitButton);

        Intent secondIntent = new Intent(this, HomeScreen.class);

        submitButton.setOnClickListener(v ->
        {
            secondIntent.putExtra("com.example.SipSavvy.userInput", UserName.getText().toString());
            secondIntent.putExtra("com.example.SipSavvy.userInput2", UserAge.getText().toString());
            secondIntent.putExtra("com.example.SipSavvy.userInput3", UserWeight.getText().toString());

            startActivity(secondIntent);
        });
    }
}