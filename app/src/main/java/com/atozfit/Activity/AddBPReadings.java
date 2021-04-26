package com.atozfit.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.atozfit.R;
import com.atozfit.Service.AtoZBPService;
import com.atozfit.main.AtoZBPAttributes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddBPReadings extends AppCompatActivity {

    final Calendar myCalendar = Calendar.getInstance();

    String patientName=null;

    Button viewBPBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_b_p_readings);
        Intent intent = getIntent();
        patientName = intent.getStringExtra("patientName");
        if(patientName!=null && !patientName.isEmpty()){
            TextView tvName = (TextView)findViewById(R.id.add_welcome_txt);
            tvName.setText("Welcome:" + " "+patientName);
        }

        viewBPBtn = (Button) findViewById(R.id.add_bp_btn_mn);
        viewBPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordBPData(v);
                openNewActivity(GraphViewer.class);
            }
        });
    }

    public void recordBPData(View view) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        Date date = new Date();
        AtoZBPService bpService = new AtoZBPService();
        List<AtoZBPAttributes> bpList = new ArrayList<>();
        AtoZBPAttributes atobpreadings = new AtoZBPAttributes();
        EditText systolicText = (EditText) findViewById(R.id.systolicText);
        String systolic = systolicText.getText().toString();
        atobpreadings.setPatientName(patientName);
        atobpreadings.setSystolic(systolic);
        EditText diastolicText = (EditText) findViewById(R.id.diastolicText);
        String diastolic = diastolicText.getText().toString();
        atobpreadings.setDiastolic(diastolic);
        atobpreadings.setDate(myCalendar.getTime());
        bpList.add(atobpreadings);
        bpService.saveData(bpList);
    }

    public void openNewActivity(Class className){
        Intent intent = new Intent(this,className);
        startActivity(intent);
    }
}