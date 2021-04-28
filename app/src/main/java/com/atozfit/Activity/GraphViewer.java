package com.atozfit.Activity;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.atozfit.R;
import com.atozfit.Service.AtoZBPService;
import com.atozfit.main.AtoZBPAttributes;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class GraphViewer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_view);
        drawGraph();
    }

    public void drawGraph(){
        List<AtoZBPAttributes> data=null;
        AtoZBPService bpService = new AtoZBPService();
        LineGraphSeries<DataPoint> systolicSeries=null;
        LineGraphSeries<DataPoint> diastolicSeries=null;
        GraphView graph1 = (GraphView) findViewById(R.id.graph1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM-dd", Locale.ENGLISH);
        data=bpService.fetchBasedOnDate(bpService.retrieveBPData());
        try {
            systolicSeries = new LineGraphSeries<DataPoint>(generateDataPoint("systolic",data));
        } catch (ParseException e) {
            e.printStackTrace();
        }
       systolicSeries.setDrawDataPoints(Boolean.TRUE);
        systolicSeries.setColor( Color.RED);
        //systolicSeries.setDataPointsRadius(6);
        try {
            diastolicSeries = new LineGraphSeries<DataPoint>(generateDataPoint("diastolic",data));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        diastolicSeries.setDrawDataPoints(Boolean.TRUE);
       // diastolicSeries.setDataPointsRadius(6);
        diastolicSeries.setColor( Color.BLUE);
        graph1.addSeries(systolicSeries);
        graph1.getGridLabelRenderer().setVerticalAxisTitle("Systolic & Diastolic");
        graph1.getGridLabelRenderer().setHorizontalAxisTitle("Date");
        graph1.addSeries(diastolicSeries);
        graph1.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    return dateFormat.format(new Date((long) value));
                } else {
                    return super.formatLabel(value, isValueX);
                }
            }
        });
       // graph1.onDataChanged(Boolean.TRUE,Boolean.TRUE);
       // graph1.getViewport().setScrollable(Boolean.TRUE);
        //graph1.getViewport().setScalable(Boolean.TRUE);
    }

    private DataPoint[] generateDataPoint(String type,List<AtoZBPAttributes> data) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM-dd-yyyy", Locale.ENGLISH);
        List<DataPoint> dataPointList = new ArrayList<>();
        DataPoint[] values = new DataPoint[data.size()];
        if(!data.isEmpty()){
            for(int i=0;i<data.size();i++){
                if(type.equals("systolic")) {
                    DataPoint v = new DataPoint(dateFormat.parse(data.get(i).getDate()), Double.parseDouble( data.get(i).getSystolic() ) );
                    values[i] = v;
                }else{
                    DataPoint v = new DataPoint(dateFormat.parse(data.get(i).getDate()), Double.parseDouble( data.get(i).getDiastolic() ) );
                    values[i] = v;
                }
        }
    }
        return values;
    }




}