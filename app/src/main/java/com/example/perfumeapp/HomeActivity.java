package com.example.perfumeapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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
        LinearLayout layoutLoginButtons = findViewById(R.id.layoutLoginButtons);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnSignup = findViewById(R.id.btnSignup);
        Button btnLogout = findViewById(R.id.btnLogout);
        Button btnDiscover = findViewById(R.id.btnDiscover);

        if (isLoggedIn) {
            // User is logged in
            tvStatus.setText(getString(R.string.logged_in_message, currentUser));
            layoutLoginButtons.setVisibility(View.GONE);
            btnLogout.setVisibility(View.VISIBLE);

            btnLogout.setOnClickListener(v -> {
                session.edit().clear().apply();
                recreate(); // refresh the page
            });
        } else {
            // User is not logged in
            tvStatus.setText(R.string.not_logged_in_message);
            layoutLoginButtons.setVisibility(View.VISIBLE);
            btnLogout.setVisibility(View.GONE);

            btnLogin.setOnClickListener(v -> {
                startActivity(new Intent(this, MainActivity.class));
            });

            btnSignup.setOnClickListener(v -> {
                startActivity(new Intent(this, SignUpActivity.class));
            });
        }

        // Discover button (placeholder for future functionality)
        btnDiscover.setOnClickListener(v -> {
            // TODO: Navigate to featured collections or marketplace
        });
    }
}