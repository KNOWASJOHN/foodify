package com.example.app;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class profile extends AppCompatActivity {

    TextView nameText, ageText, phoneText, branchText, cceidText, semText, hostelText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        nameText = findViewById(R.id.name_display);
        ageText = findViewById(R.id.age_display);
        phoneText = findViewById(R.id.phone_display);
        branchText = findViewById(R.id.branch_display);
        cceidText = findViewById(R.id.cceid_display);
        semText = findViewById(R.id.sem_display);
        hostelText = findViewById(R.id.hostel_display);

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("students").child(uid);

        userRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult().exists()) {
                DataSnapshot data = task.getResult();
                nameText.setText("Name: " + data.child("name").getValue(String.class));
                ageText.setText("Age: " + data.child("age").getValue(Integer.class));
                phoneText.setText("Phone: " + data.child("phone").getValue(String.class));
                branchText.setText("Branch: " + data.child("branch").getValue(String.class));
                cceidText.setText("CCE ID: " + data.child("id").getValue(String.class));
                semText.setText("Semester: " + data.child("semester").getValue(String.class));
                hostelText.setText("Hostel: " + data.child("hostel").getValue(String.class));
            } else {
                Toast.makeText(this, "Failed to load details", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
