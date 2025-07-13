package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity3 extends AppCompatActivity {

    String[] hostel_option = {"Yes", "No"};
    String[] branch_option = {"ME", "CE", "ECE", "EEE", "CSE", "CSE(DS)"};
    String[] sem_option = {"S1", "S2", "S3", "S4", "S5", "S6", "S7", "S8"};

    AutoCompleteTextView hostelDropdown, branchDropdown, semDropdown;
    ArrayAdapter<String> adapterItems;
    Button submitBtn;
    EditText InputName, InputAge, InputPhone, InputCCEid;
    FirebaseDatabase database;
    FirebaseAuth auth;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail); // make sure your XML file is named "detail.xml"

        email = getIntent().getStringExtra("email");
        // Basic Input Fields
        InputName = findViewById(R.id.name);
        InputAge = findViewById(R.id.age);
        InputPhone = findViewById(R.id.Phone);
        InputCCEid = findViewById(R.id.cceid);

        // Dropdowns
        branchDropdown = findViewById(R.id.branch_items); // AutoCompleteTextView
        semDropdown = findViewById(R.id.sem_items);       // AutoCompleteTextView
        hostelDropdown = findViewById(R.id.dropdown_menu); // AutoCompleteTextView

        // Adapters
        adapterItems = new ArrayAdapter<>(this, R.layout.dropdown_items, hostel_option);
        hostelDropdown.setAdapter(adapterItems);

        adapterItems = new ArrayAdapter<>(this, R.layout.dropdown_items, branch_option);
        branchDropdown.setAdapter(adapterItems);

        adapterItems = new ArrayAdapter<>(this, R.layout.dropdown_items, sem_option);
        semDropdown.setAdapter(adapterItems);

        // Submit Button
        submitBtn = findViewById(R.id.button2);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        submitBtn.setOnClickListener(v -> {
            String uid = auth.getCurrentUser().getUid(); // 🔑 Unique user path

            Student student = new Student(
                    InputName.getText().toString(),
                    Integer.parseInt(InputAge.getText().toString()),
                    branchDropdown.getText().toString(),
                    InputCCEid.getText().toString(),
                    semDropdown.getText().toString(),
                    hostelDropdown.getText().toString(),
                    InputPhone.getText().toString(),
                    email // from intent
            );

            DatabaseReference studentRef = database.getReference("students");
            studentRef.child(uid).setValue(student)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(this, "Details Saved", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(this, MainActivity.class);
                            startActivity(intent);
                            finish();// Go back or to home page
                        } else {
                            Toast.makeText(this, "Save Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }
}
