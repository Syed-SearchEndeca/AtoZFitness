package com.atozfit.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.atozfit.R;
import com.atozfit.Service.AtoZBPService;

public class Registration extends AppCompatActivity {

    private AtoZBPService atoZBPService;

    Button saveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        saveButton = (Button) findViewById(R.id.Save_Btn);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePatientName(v);
                openNewActivity(MainActivity.class);
            }
        });
    }

    /**
     * Called when the user taps the Send button
     */
    public void savePatientName(View view) {
        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName2);
        String patientName = editText.getText().toString();
        atoZBPService = new AtoZBPService();
        atoZBPService.savePatientName(patientName);
    }
        public void openNewActivity(Class className){
            Intent intent = new Intent(this,className);
            startActivity(intent);
        }
    }
