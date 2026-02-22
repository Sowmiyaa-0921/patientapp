package com.example.patientapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText etName, etAge, etDate;
    Spinner spIllness;
    Button btnCall, btnSMS, btnEmail;

    String[] illnessList = {"Fever", "Cold", "Headache", "Cough", "Diabetes"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etDate = findViewById(R.id.etDate);
        spIllness = findViewById(R.id.spIllness);
        btnCall = findViewById(R.id.btnCall);
        btnSMS = findViewById(R.id.btnSMS);
        btnEmail = findViewById(R.id.btnEmail);

        // Spinner Setup
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, illnessList);
        spIllness.setAdapter(adapter);

        // Date Picker
        etDate.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    MainActivity.this,
                    (datePicker, y, m, d) -> etDate.setText(d + " / " + (m + 1) + " / " + y),
                    year, month, day);

            datePickerDialog.show();
        });

        // Call Button
        btnCall.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(android.net.Uri.parse("tel:1234567890"));
            startActivity(intent);
        });

        // SMS Button
        btnSMS.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(android.net.Uri.parse("sms:1234567890"));
            intent.putExtra("sms_body", "Hello Doctor, I need appointment.");
            startActivity(intent);
        });

        // Email Button
        btnEmail.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("message/rfc822");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"doctor@gmail.com"});
            intent.putExtra(Intent.EXTRA_SUBJECT, "Appointment Request");
            intent.putExtra(Intent.EXTRA_TEXT, "Hello Doctor, I need appointment.");
            startActivity(Intent.createChooser(intent, "Send Email"));
        });
    }
}