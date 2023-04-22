package com.example.sipsavvy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.nullness.qual.NonNull;

public class SettingPage extends AppCompatActivity {

    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_page);
        firestore = FirebaseFirestore.getInstance();

        Button submit = findViewById(R.id.submitButtonSettings);
        Button clear = findViewById(R.id.clearButton);

        EditText newUser = findViewById(R.id.newUserName);
        EditText newHeightFeet = findViewById(R.id.newFeet);
        EditText newheightInches = findViewById(R.id.newInches);
        EditText newWeight = findViewById(R.id.newWeight);
        Intent currIntent = getIntent();
        String prevUser = currIntent.getStringExtra("com.example.SipSavvy.userName");
        String prevWeight = currIntent.getStringExtra("com.example.SipSavvy.userWeight");
        Intent toHome = new Intent(SettingPage.this, HomeScreen.class);
        Intent toUserInfo = new Intent(SettingPage.this, Screen1.class);
        submit.setOnClickListener(v -> {
            String currUser = prevUser;
            String currWeight = prevWeight;
            if(!newUser.getText().toString().isBlank()){
                currUser = newUser.getText().toString();
                firestore.collection("users").document("Current User").update("username", newUser.getText().toString());
            }
            if(!newHeightFeet.getText().toString().isBlank()){
                firestore.collection("users").document("Current User").update("ft", newHeightFeet.getText().toString());
            }
            if(!newheightInches.getText().toString().isBlank()){
                firestore.collection("users").document("Current User").update("in", newheightInches.getText().toString());
            }
            if(!newWeight.getText().toString().isBlank()){
                currWeight = newWeight.getText().toString();
                firestore.collection("users").document("Current User").update("weight", newWeight.getText().toString());
            }
            toHome.putExtra("com.example.SipSavvy.userName", currUser);
            toHome.putExtra("com.example.SipSavvy.userWeight", currWeight);
            startActivity(toHome);
            finish();
        });

        clear.setOnClickListener(v -> {
            firestore.collection("users").document("Current User").delete();
            startActivity(toUserInfo);
            finish();
        });


    }
}