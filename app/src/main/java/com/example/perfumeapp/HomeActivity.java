package com.example.perfumeapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
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
        TextView tvAuthBadge = findViewById(R.id.tvAuthBadge);
        TextView tvWelcomeName = findViewById(R.id.tvWelcomeName);
        View layoutAuthButtons = findViewById(R.id.layoutAuthButtons);
        View layoutLoggedIn = findViewById(R.id.layoutLoggedIn);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnSignUp = findViewById(R.id.btnSignUp);
        Button btnLogout = findViewById(R.id.btnLogout);

        if (isLoggedIn) {
            // Show logged-in state
            tvAuthBadge.setText("✓ Signed In");
            tvStatus.setVisibility(View.VISIBLE);
            tvStatus.setText("Logged in as: " + currentUser);
            tvWelcomeName.setText(currentUser);
            layoutAuthButtons.setVisibility(View.GONE);
            layoutLoggedIn.setVisibility(View.VISIBLE);

            btnLogout.setOnClickListener(v -> {
                session.edit().clear().apply();
                recreate();
            });
        } else {
            // Show logged-out state
            tvAuthBadge.setText("Sign In");
            tvStatus.setVisibility(View.GONE);
            layoutAuthButtons.setVisibility(View.VISIBLE);
            layoutLoggedIn.setVisibility(View.GONE);

            btnLogin.setOnClickListener(v ->
                    startActivity(new Intent(this, MainActivity.class)));

            btnSignUp.setOnClickListener(v ->
                    startActivity(new Intent(this, SignUpActivity.class)));
        }
    }
}
