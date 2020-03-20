package com.example.firebasepushnotifications;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import static com.example.firebasepushnotifications.FirebaseMessagingService.ARG_BODY;
import static com.example.firebasepushnotifications.FirebaseMessagingService.ARG_TITLE;

public class MainActivity extends AppCompatActivity {
    private TextView titleTextView;
    private TextView headingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleTextView = findViewById(R.id.textviewtitle);
        headingTextView = findViewById(R.id.textviewheading);
        Intent intent = getIntent();
        String title = intent.getStringExtra(ARG_TITLE);
        String body = intent.getStringExtra(ARG_BODY);
        titleTextView.setText(title);
        headingTextView.setText(body);

    }
}
