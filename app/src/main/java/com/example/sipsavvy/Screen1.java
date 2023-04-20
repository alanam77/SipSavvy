package com.example.sipsavvy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class Screen1 extends AppCompatActivity {
    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen1);
        firestore = FirebaseFirestore.getInstance();
        Button submitButton = findViewById(R.id.submitButton);
        EditText username = findViewById(R.id.UserName);
        EditText age = findViewById(R.id.UserAge);
        EditText userfeet = findViewById(R.id.UserFeet);
        EditText userinches = findViewById(R.id.UserInches);
        EditText userweight = findViewById(R.id.UserWeight);
        String userName = username.getText().toString();
        String userAge = age.getText().toString();
        String userFeet = userfeet.getText().toString();
        String userInches = userinches.getText().toString();
        String userWeight = userweight.getText().toString();
        Map<String, Object> userInfo = new HashMap<String, Object>();
        userInfo.put("username", userName);
        userInfo.put("age", userAge);
        userInfo.put("ft", userFeet);
        userInfo.put("in", userInches);
        userInfo.put("weight", userWeight);
        Intent intent = new Intent(Screen1.this, HomeScreen.class);

        submitButton.setOnClickListener(v -> {
                firestore.collection("users").document("Current User").set(userInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(), "Set User Success",Toast.LENGTH_LONG).show();
                        intent.putExtra("userName", userName);
                        intent.putExtra("feet", userFeet);
                        intent.putExtra("inches",userInches);
                        intent.putExtra("weight",userWeight);
                        intent.putExtra("age", userAge);
//                        intent.putExtra("Wake Up", userWakeUp.getText().toString());
//                        intent.putExtra("Sleep Time", userSleep.getText().toString());
                        startActivity(intent);
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Set User Failed", Toast.LENGTH_LONG).show();
                        }
                });
        });
    }
}