package com.example.sipsavvy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.*;
import android.widget.TextView;

public class HomeScreen extends AppCompatActivity {

    private int progress = 0;

    private ProgressBar progressBar;
    private TextView textViewProgress;
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

        idealWaterIntake.setText(waterIntake + "\nounces per day");

        TextView idealWaterIntake2 = findViewById(R.id.idealWaterIntake2);
        int goal = (waterIntake * 20) / 100;
        int finalGoal = waterIntake - goal;
        idealWaterIntake2.setText(finalGoal + " ounces is your current goal!");


        //for progress bar
        progressBar = findViewById(R.id.progress_bar);
        textViewProgress = findViewById(R.id.text_view_progress);
        progressBar.setProgress(0);
        textViewProgress.setText("0%");

        EditText customAmount = findViewById(R.id.customAmount);
      //  String amount = customAmount.getText().toString();
      //  int intAmount = Integer.valueOf(amount);


        //fix this
        customAmount.setOnEditorActionListener(
                (arg0, actionId, event) -> {
                    if (actionId == EditorInfo.IME_ACTION_DONE)
                    {
                        progress += 10;
                        updateProgressBar();
                    }
                    return false;
                });
        //


        Button addEightOunces = findViewById(R.id.addEightOunces);
        addEightOunces.setOnClickListener(v ->
        {
            if(progress <= finalGoal)
            {
                progress += 8;
                updateProgressBar();
            }

            if(progress >= finalGoal)
            {
                updateProgressBar();
                textViewProgress.setText("You did it! Congratulations!");
            }
        });

    }



    private void updateProgressBar()
    {
        progressBar.setProgress(progress);
        textViewProgress.setText(progress + "%");
    }


}