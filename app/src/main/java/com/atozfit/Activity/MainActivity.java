package com.atozfit.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.atozfit.R;
import com.atozfit.Service.AtoZBPService;
import com.atozfit.dto.AtoZBPRepo;
import com.atozfit.main.AtoZBPAttributes;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    String patnName=null;

    ProgressDialog mProgressBar;

    private TableLayout mTableLayout;

    private AtoZBPService atoZBPService;

    private static MainActivity instance;

    Button addUserBtn;

    Button addBpBtn;

    Button viewBpBtn;

    TextView systolicTxt =null;

    TextView diastolicTxt=null;

    ImageButton graphicalView;

    public static Context getContext() {
        return instance.getApplicationContext();
    }

    public String retriveName(){
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        atoZBPService= new AtoZBPService();
        patnName =atoZBPService.retrievePatientName();
        return patnName;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;
        mProgressBar = new ProgressDialog(this);
        Intent intent = getIntent();
        String patientName = retriveName();
        startLoadData();
        if(patientName!=null && !patientName.isEmpty()){
           TextView tvName = (TextView)findViewById(R.id.welcome_txt);
           tvName.setText("Welcome:" + " "+patientName);
       }

        addUserBtn = (Button) findViewById(R.id.add_user_btn);
        addUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity(Registration.class);
            }
        });

        graphicalView = (ImageButton) findViewById(R.id.GraphicalViewBtn);
        graphicalView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity(GraphViewer.class);
            }
        });


        addBpBtn = (Button) findViewById(R.id.add_bp_btn);
        addBpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity(AddBPReadings.class);
            }
        });


        viewBpBtn=(Button)findViewById(R.id.view_bp_btn);
        viewBpBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick (View v){
        openNewActivity(DisplayBP_Results.class);
    }
    });
        getSupportActionBar().setDisplayShowTitleEnabled(false);
}
    public void openNewActivity(Class className){
        Intent intent = new Intent(this,className);
        if(patnName!=null) {
            intent.putExtra("patientName", patnName);
        }
        startActivity(intent);
    }

    public void startLoadData() {
        atoZBPService= new AtoZBPService();
        List<AtoZBPAttributes> bpAttributes=atoZBPService.retrieveBPData();
        int size=bpAttributes.size();
        systolicTxt = (TextView)findViewById(R.id.Systolic_txt);
        systolicTxt.setText("Systolic:" + " "+bpAttributes.get(size-1).getSystolic());
        diastolicTxt = (TextView)findViewById(R.id.Diastolic_txt);
        diastolicTxt.setText("Diastolic:" + " "+bpAttributes.get(size-1).getDiastolic());
    }

}

