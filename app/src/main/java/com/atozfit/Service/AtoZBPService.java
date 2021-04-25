package com.atozfit.Service;

import android.content.Context;
import android.util.Log;

import com.atozfit.Activity.MainActivity;
import com.atozfit.R;
import com.atozfit.main.AtoZBPAttributes;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.content.Context.MODE_APPEND;

public class AtoZBPService {

    private static final String TAG = "AtoZBPService";

    private Context context;

    private String delimit = "|";


    static final int READ_BLOCK_SIZE = 100;

    public void saveData(List<AtoZBPAttributes> pbpAttributes) {
        try {
            Log.d(TAG, "Inside the AtoZBPService saveData method");
            List<AtoZBPAttributes> bpAttributes = pbpAttributes;
            StringBuilder strData = new StringBuilder();
            context = MainActivity.getContext();
            FileOutputStream fileout = context.openFileOutput(context.getString(R.string.BPFileName), MODE_APPEND);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            for (AtoZBPAttributes val : bpAttributes) {
                StringBuilder sbuilder = new StringBuilder();
                sbuilder.append(val.getsNo() + delimit + val.getDate() + delimit + val.getPatientName() + delimit + val.getSystolic() + delimit + val.getDiastolic());
                outputWriter.write(sbuilder.toString());
                outputWriter.write("\n\r");
                Log.d(TAG, "Following values are inserted:" + sbuilder.toString());
            }

            outputWriter.close();
            Log.d(TAG, "Outside the AtoZBPService saveData method");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<AtoZBPAttributes> retrieveBPData() {
        Log.d(TAG, "Inside the AtoZBPService returnData method");
        FileInputStream fileIn = null;
        List<AtoZBPAttributes> bpList = new ArrayList<>();
        try {
            context = MainActivity.getContext();
            fileIn = context.openFileInput(context.getString(R.string.BPFileName));
            InputStreamReader inputRead = new InputStreamReader(fileIn);
            BufferedReader reader = new BufferedReader(inputRead);
            String readText=null;
            while ((readText=reader.readLine()) != null) {
                if (readText != null && !readText.isEmpty()) {
                    String[] arrStr = readText.split("\\"+delimit);
                    if (arrStr.length > 4) {
                        AtoZBPAttributes atozbpattributes = new AtoZBPAttributes();
                        atozbpattributes.setsNo(Integer.parseInt(arrStr[0]));
                        Log.d(TAG, "SNO:" + Integer.parseInt(arrStr[0]));
                        try {
                            Calendar cal = Calendar.getInstance();
                            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
                            cal.setTime(sdf.parse(arrStr[1]));// all done
                            final Date date = cal.getTime();
                            String year = new SimpleDateFormat("yyyy").format(date); // 4 digit year
                            atozbpattributes.setDate(date);
                            Log.d(TAG, "Date:" + date);
                            atozbpattributes.setYear(year);
                            Log.d(TAG, "Year:" + year);
                            atozbpattributes.setPatientName(arrStr[2]);
                            Log.d(TAG, "Patient Name:" + arrStr[2]);
                            atozbpattributes.setSystolic(arrStr[3]);
                            Log.d(TAG, "Systolic:" + arrStr[3]);
                            atozbpattributes.setDiastolic(arrStr[4]);
                            Log.d(TAG, "Diastolic:" + arrStr[4]);
                            bpList.add(atozbpattributes);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
            inputRead.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "bpList List Size:" + bpList.size());
        Log.d(TAG, "Outside the AtoZBPService returnData method");
        return bpList;
    }

    public void savePatientName(String pPatientName) {
        try {
            Log.d(TAG, "Inside the AtoZBPService savePatientName method");
            String patientName = pPatientName;
            context = MainActivity.getContext();
            FileOutputStream userFileOut = context.openFileOutput(context.getString(R.string.UserFileName), MODE_APPEND);
            OutputStreamWriter outputWriter = new OutputStreamWriter(userFileOut);
            outputWriter.write(patientName);
            outputWriter.write("\n\r");
            Log.d(TAG, "PatientName:" + patientName);
            outputWriter.close();
            Log.d(TAG, "Outside the AtoZBPService savePatientName method");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public String retrievePatientName() {
        Log.d(TAG, "Inside the AtoZBPService retrievePatientName method");
        List<String> userNamList = new ArrayList<>();
        try {
            context = MainActivity.getContext();
            FileInputStream fileIn = context.openFileInput(context.getString(R.string.UserFileName));
            InputStreamReader inputRead = new InputStreamReader(fileIn);
            BufferedReader reader = new BufferedReader(inputRead);
            String patientName=null;
            while ( (patientName = reader.readLine()) != null ) {
                userNamList.add(patientName);
                Log.d(TAG, "patientName1:" + patientName);
                Log.d(TAG, "patientName:" + userNamList.get(0));
                Log.d(TAG, "Outside the AtoZBPService retrievePatientName method");
            }
            inputRead.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userNamList.isEmpty() ? "" : userNamList.get(0);
    }
}