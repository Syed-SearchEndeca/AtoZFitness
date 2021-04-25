package com.atozfit.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.atozfit.R;
import com.atozfit.Service.AtoZBPService;

public class Registration extends AppCompatActivity {

    private AtoZBPService atoZBPService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    /**
     * Called when the user taps the Send button
     */
    public void savePatientName(View view) {
        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName2);
        String patientName = editText.getText().toString();
        atoZBPService= new AtoZBPService();
        atoZBPService.savePatientName(patientName);
    }


}