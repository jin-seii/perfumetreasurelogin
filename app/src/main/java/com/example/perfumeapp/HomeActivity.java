package com.example.perfumeapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences session = getSharedPreferences("session", MODE_PRIVATE);
        boolean isLoggedIn = session.getBoolean("isLoggedIn", false);
        String currentUser = session.getString("currentUser", "");

        TextView tvStatus = findViewById(R.id.tvStatus);
        Button btnLoginLogout = findViewById(R.id.btnLoginLogout);

        if (isLoggedIn) {
            tvStatus.setText("Logged in as: " + currentUser);
            btnLoginLogout.setText("Log Out");
            btnLoginLogout.setOnClickListener(v -> {
                session.edit().clear().apply();
                recreate(); // refresh the page
            });
        } else {
            tvStatus.setText("You are not logged in");
            btnLoginLogout.setText("Log In / Sign Up");
            btnLoginLogout.setOnClickListener(v -> {
                startActivity(new Intent(this, MainActivity.class));
            });
        }
    }
}