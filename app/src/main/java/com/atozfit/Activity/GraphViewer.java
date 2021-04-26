package com.atozfit.Activity;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.atozfit.R;
import com.atozfit.Service.AtoZBPService;
import com.atozfit.main.AtoZBPAttributes;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

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
        LineGraphSeries<DataPoint> systolicSeries=null;
        LineGraphSeries<DataPoint> diastolicSeries=null;
        GraphView graph1 = (GraphView) findViewById(R.id.graph1);
        GraphView graph2= (GraphView) findViewById(R.id.graph2);
        systolicSeries = new LineGraphSeries<DataPoint>(generateDataPoint("systolic"));
        systolicSeries.setDrawDataPoints(Boolean.TRUE);
        systolicSeries.setColor( Color.RED);
        systolicSeries.setDataPointsRadius(6);
        diastolicSeries = new LineGraphSeries<DataPoint>(generateDataPoint("diastolic"));
        diastolicSeries.setDrawDataPoints(Boolean.TRUE);
        diastolicSeries.setDataPointsRadius(6);
        diastolicSeries.setColor( Color.BLUE);
        graph1.addSeries(systolicSeries);
        graph2.addSeries(diastolicSeries);
        graph1.onDataChanged(Boolean.TRUE,Boolean.TRUE);
        graph1.getViewport().setScrollable(Boolean.TRUE);
        //graph1.getViewport().setScalable(Boolean.TRUE);
        graph2.getViewport().setScrollable(Boolean.TRUE);
        //graph2.getViewport().setScalable(Boolean.TRUE);
        graph2.onDataChanged(Boolean.TRUE,Boolean.TRUE);
        diastolicSeries.setTitle("Diastolic");
        systolicSeries.setTitle("Systolic");
        //graph1.getViewport().setYAxisBoundsManual(true);
        //graph2.getViewport().setYAxisBoundsManual(true);


    }

    private DataPoint[] generateDataPoint(String type) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd", Locale.ENGLISH);
        List<DataPoint> dataPointList = new ArrayList<>();
        AtoZBPService bpService = new AtoZBPService();
        List<AtoZBPAttributes> data = bpService.retrieveBPData();
        DataPoint[] values = new DataPoint[data.size()];
        if(!data.isEmpty()){
            for(int i=0;i<data.size();i++){
                if(type.equals("systolic")) {
                    DataPoint v = new DataPoint(data.get(i).getDate(), Double.parseDouble( data.get(i).getSystolic() ) );
                    values[i] = v;
                }else{
                    DataPoint v = new DataPoint(data.get(i).getDate(), Double.parseDouble( data.get(i).getDiastolic() ) );
                    values[i] = v;
                }
        }
    }
        return values;
    }
}