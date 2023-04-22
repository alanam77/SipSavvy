package com.example.sipsavvy;


import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
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
    private int progress = Constants.CURRENT_INTAKE;
    private int goal = 0;
    private int finalGoal = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        createNotificationChannel();

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
        progressBar.setProgress(Constants.CURRENT_INTAKE,true);
        textViewProgress.setText(progress +" Ounces!");

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
                progress += 8;
                Constants.CURRENT_INTAKE = progress;
                updateProgressBar(progress);
                if(progress >= finalGoal){
                    textViewProgress.setText("You did it!");
                }
                else {
                    textViewProgress.setText(progress + " Ounces!");
                }
        });
        Button remindBtn = findViewById(R.id.remindBtn);
        remindBtn.setOnClickListener(v -> {
            Toast.makeText(this, "Reminder Set", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(HomeScreen.this, Reminder.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(HomeScreen.this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

            long timeAtButtonClick = System.currentTimeMillis();

            long tenSecsInMillis = 1000 * 10;

            alarmManager.set(AlarmManager.RTC_WAKEUP,
                    timeAtButtonClick + tenSecsInMillis,
                    pendingIntent);

        });

        ImageButton settingsButton = findViewById(R.id.settingsButton);

        Intent settingpage = new Intent(this, SettingPage.class);




        settingsButton.setOnClickListener(v ->
        {
            settingpage.putExtra("com.example.SipSavvy.userName", userName);
            settingpage.putExtra("com.example.SipSavvy.userWeight",userWeight);
            startActivity(settingpage);
        });


    }



    private void updateProgressBar(int val)
    {
        progressBar.setProgress(val,true);
    }
    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "SipSavvy Reminder";
            String description = "Channel for Drink Reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notifyDrink", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


}