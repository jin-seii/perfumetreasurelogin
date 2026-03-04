package com.example.perfumeapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnLogin, btnGoSignUp, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnGoSignUp = findViewById(R.id.btnGoSignUp);
        btnBack = findViewById(R.id.btnBack);

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences users = getSharedPreferences("users", MODE_PRIVATE);
            String savedPassword = users.getString(email, null);

            if (savedPassword != null && savedPassword.equals(password)) {
                SharedPreferences session = getSharedPreferences("session", MODE_PRIVATE);
                session.edit().putBoolean("isLoggedIn", true).putString("currentUser", email).apply();

                Toast.makeText(this, "Logged in!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, HomeActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
            }
        });

        btnGoSignUp.setOnClickListener(v -> {
            startActivity(new Intent(this, SignUpActivity.class));
        });

        btnBack.setOnClickListener(v -> {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        });
    }
}