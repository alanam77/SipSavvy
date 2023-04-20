package com.example.sipsavvy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        Intent mainIntent = getIntent();

        TextView welcomeUser = findViewById(R.id.welcomeUser);
        String userName = mainIntent.getStringExtra("com.example.SipSavvy.userInput");
        welcomeUser.setText("Welcome, " + userName + "!");


        TextView idealWaterIntake = findViewById(R.id.idealWaterIntake);
        String userWeight = mainIntent.getStringExtra("com.example.SipSavvy.userInput3");
        int weightOfUser = Integer.valueOf(userWeight);
        int waterIntake = weightOfUser / 2;

        idealWaterIntake.setText(waterIntake + "\nounces per day");

        TextView idealWaterIntake2 = findViewById(R.id.idealWaterIntake2);
        int goal = (waterIntake * 20) / 100;
        int finalGoal = waterIntake - goal;
        idealWaterIntake2.setText(finalGoal + " ounces is your current goal! You got this!");

    }

}