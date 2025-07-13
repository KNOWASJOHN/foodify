package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {
    Button sign_up;
    EditText inputEmail,inputPassword;
    TextView signupBtn;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);

        setContentView(R.layout.signup);


        //email collection

        inputEmail = findViewById(R.id.email_2);
        inputPassword = findViewById(R.id.password_2);
        sign_up = findViewById(R.id.button_2);
        signupBtn = findViewById(R.id.login_2);

        auth = FirebaseAuth.getInstance();

        signupBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        });

        sign_up.setOnClickListener(v -> {
            String email = inputEmail.getText().toString().trim();
            String password = inputPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email or password missing", Toast.LENGTH_SHORT).show();
                return;
            }

            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(this, "Signup Successful", Toast.LENGTH_SHORT).show();
                            // ✅ Pass email to next activity
                            Intent intent = new Intent(this, MainActivity3.class);
                            intent.putExtra("email", email);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(this, "Signup Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
        });

    }

}