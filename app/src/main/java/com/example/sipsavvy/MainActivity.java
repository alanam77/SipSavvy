package com.example.sipsavvy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore firestore;
    private CollectionReference userRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firestore = FirebaseFirestore.getInstance();
        userRef = firestore.collection("users");
        firestore.collection("users").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                Toast.makeText(getApplicationContext(), "Successfully Retrieved User", Toast.LENGTH_LONG).show();
                if (queryDocumentSnapshots.size() > 0) {
                    String userName = queryDocumentSnapshots.getDocuments().get(0).get("username").toString();
                    int feet = Integer.parseInt(queryDocumentSnapshots.getDocuments().get(0).get("ft").toString());
                    int inches = Integer.parseInt(queryDocumentSnapshots.getDocuments().get(0).get("in").toString());
                    double weight = Double.parseDouble(queryDocumentSnapshots.getDocuments().get(0).get("weight").toString());
                    int age = Integer.parseInt(queryDocumentSnapshots.getDocuments().get(0).get("age").toString());
                    Intent intent = new Intent(MainActivity.this, HomeScreen.class);
                    intent.putExtra("userName", userName);
                    intent.putExtra("feet", feet);
                    intent.putExtra("inches",inches);
                    intent.putExtra("weight",weight);
                    intent.putExtra("age", age);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(intent);
                            finish();
                        }
                    }, 3000);

                } else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(MainActivity.this, Screen1.class);
                            startActivity(intent);
                        }
                    }, 3000);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Failed to Get Users",Toast.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity.this, Screen1.class);
                        startActivity(intent);
                        finish();
                    }
                }, 3000);
            }
        });




    }
}