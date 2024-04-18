package com.example.bat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {

    private EditText signupName, signupUsername, signupEmail, signupPassword;
    private TextView loginRedirectText;
    private Button signupButton;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        signupName = findViewById(R.id.signup_name);
        signupEmail = findViewById(R.id.signup_email);
        signupUsername = findViewById(R.id.signup_username);
        signupPassword = findViewById(R.id.signup_password);
        loginRedirectText = findViewById(R.id.loginRedirectText);
        signupButton = findViewById(R.id.signup_button);

        // Initialize Firebase database reference
        reference = FirebaseDatabase.getInstance().getReference("users");

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSignup();
            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signup.this, login.class);
                startActivity(intent);
            }
        });
    }

    private void handleSignup() {
        String name = signupName.getText().toString().trim();
        String email = signupEmail.getText().toString().trim();
        String username = signupUsername.getText().toString().trim();
        String password = signupPassword.getText().toString().trim();

        // Validate input fields
        if (name.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            Toast.makeText(signup.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a Helper instance to store user data
        Helper helperClass = new Helper(name, email, username, password);

        // Store user data in the database
        reference.child(username).setValue(helperClass).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(signup.this, "You have signed up successfully!", Toast.LENGTH_SHORT).show();
                // Redirect to login activity
                Intent intent = new Intent(signup.this, login.class);
                startActivity(intent);
            } else {
                Toast.makeText(signup.this, "Signup failed. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}