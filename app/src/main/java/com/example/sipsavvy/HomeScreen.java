package com.example.sipsavvy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.EditorInfo;
import android.widget.*;
import android.widget.TextView;

public class HomeScreen extends AppCompatActivity {



    private ProgressBar progressBar;
    private TextView textViewProgress;
    private int progress = 0;
    private int goal = 0;
    private int finalGoal = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        Intent mainIntent = getIntent();

        TextView welcomeUser = findViewById(R.id.welcomeUser);
        String userName = mainIntent.getStringExtra("com.example.SipSavvy.userName");
        welcomeUser.setText("Welcome, " + userName + "!");


        TextView idealWaterIntake = findViewById(R.id.idealWaterIntake);
        String userWeight = mainIntent.getStringExtra("com.example.SipSavvy.userWeight");
        int weightOfUser = Integer.parseInt(userWeight);
        int waterIntake = weightOfUser / 2;
        Constants.IDEAL_INTAKE = waterIntake;

        idealWaterIntake.setText(waterIntake + "\nounces per day");

        TextView idealWaterIntake2 = findViewById(R.id.idealWaterIntake2);
        goal = (waterIntake * 20) / 100;
        finalGoal = waterIntake - goal;
        Constants.CURRENT_GOAL = finalGoal;
        idealWaterIntake2.setText(finalGoal + " ounces!");

        //for progress bar
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setMin(0);
        progressBar.setMax(finalGoal);
        textViewProgress = findViewById(R.id.text_view_progress);
        progressBar.setProgress(0,true);
        textViewProgress.setText("0 Ounces!");

        EditText customAmount = findViewById(R.id.customAmount);

        //fix this
//        customAmount.setOnEditorActionListener(
//                (arg0, actionId, event) -> {
//                    if (actionId == EditorInfo.IME_ACTION_DONE)
//                    {
//                        progress += 10;
//                        updateProgressBar(progress);
//                    }
//                    return false;
//                })
//


        Button addEightOunces = findViewById(R.id.addEightOunces);
        addEightOunces.setOnClickListener(v ->
        {
            if(progress < finalGoal)
            {

                progress += 8;
                Constants.CURRENT_INTAKE = progress;
                updateProgressBar(progress);
            }

            else if(progress >= finalGoal)
            {
                updateProgressBar(progress);
                textViewProgress.setText("You did it!");
            }
        });

        ImageButton settingsButton = findViewById(R.id.settingsButton);

        Intent settingpage = new Intent(this, SettingPage.class);

        settingsButton.setOnClickListener(v ->
        {
            startActivity(settingpage);
        });


    }



    private void updateProgressBar(int val)
    {
        progressBar.setProgress(val,true);
        textViewProgress.setText(val + " Ounces!");
    }


}