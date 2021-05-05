package com.atozfit.Activity;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.atozfit.R;
import com.atozfit.Service.AtoZBPService;
import com.atozfit.main.AtoZBPAttributes;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        GraphView graph1 = (GraphView) findViewById(R.id.systolic_big);
        StaticLabelsFormatter staticLabelsFormatter1 = new StaticLabelsFormatter(graph1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM-dd", Locale.ENGLISH);
       // data=bpService.fetchBasedOnDate(bpService.retrieveBPData());
        data=bpService.retrieveBPData();
        String[] labels=setStaticLabels(data);
        try {
            systolicSeries = new LineGraphSeries<DataPoint>(generateDataPoint("systolic",data));
        } catch (ParseException e) {
            e.printStackTrace();
        }
       systolicSeries.setDrawDataPoints(Boolean.TRUE);
        systolicSeries.setColor( Color.RED);
        systolicSeries.setDataPointsRadius(6);
        try {
            diastolicSeries = new LineGraphSeries<DataPoint>(generateDataPoint("diastolic",data));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        diastolicSeries.setDrawDataPoints(Boolean.TRUE);
        diastolicSeries.setDataPointsRadius(6);
        diastolicSeries.setColor( Color.BLUE);
        graph1.addSeries(systolicSeries);
        graph1.getGridLabelRenderer().setVerticalAxisTitle("Systolic & Diastolic");
        graph1.getGridLabelRenderer().setHorizontalAxisTitle("Date");
        graph1.addSeries(diastolicSeries);
        graph1.getViewport().setXAxisBoundsManual(true);
        graph1.getViewport().setYAxisBoundsManual(true);
        graph1.getViewport().setMinY(30);
        graph1.getViewport().setMaxY(220);
        graph1.getViewport().setScalable(true);
        graph1.getViewport().setScrollable(true); // enables horizontal scrolling
        graph1.getViewport().setScalableY(true);  // activate horizontal and vertical zooming and scrolling
        graph1.getViewport().setScrollableY(true); // enables vertical scrolling
        graph1.getViewport().setDrawBorder(false);
        graph1.getViewport().setBackgroundColor(Color.TRANSPARENT);
        graph1.setTitleColor(R.color.purple_200);
        staticLabelsFormatter1.setHorizontalLabels(labels);
        graph1.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter1);
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

    private String[] setStaticLabels(List<AtoZBPAttributes> data) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM-dd", Locale.ENGLISH);
        String[] values = new String[data.size()];
        List<String> month = new ArrayList<>();
        if(!data.isEmpty()){
            for(int i=0;i<data.size();i++){
                String v = data.get(i).getDate();
               String s[]= v.split("-");
             if(!month.contains(s[0])) {
                 values[i] = v;
                 month.add(s[0]);
             }else{
                 values[i]=".";
             }

            }
        }
        return values;
    }


}