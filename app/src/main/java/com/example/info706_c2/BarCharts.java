package com.example.info706_c2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.model.GradientColor;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.example.info706_c2.MyAppUtils.TAG;

public class BarCharts extends AppCompatActivity {
    private BarChart chart;
    private Connection connection;
    ArrayList<ProductsList> mProductsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_charts);

        setTitle("Confirm Cases");

        chart = findViewById(R.id.chart1);

        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(true);

        chart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        chart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false);

        chart.setDrawGridBackground(false);
        // chart.setDrawYLabels(false);

        ValueFormatter xAxisFormatter = new DayAxisValueFormatter(chart);

        XAxis xAxis = chart.getXAxis();
        xAxis.setEnabled(false);

        ValueFormatter custom = new MyValueFormatter("");

        YAxis leftAxis = chart.getAxisLeft();
//        leftAxis.setTypeface(tfLight);
        leftAxis.setLabelCount(8, false);
        leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        chart.getAxisRight().setEnabled(false);
        chart.animateY(1000);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(true);
        l.setYOffset(0f);
        l.setXOffset(10f);
        l.setYEntrySpace(0f);
        l.setTextSize(8f);

        if (mProductsList == null) {
            mProductsList = new ArrayList<>();
        }
        if(Build.VERSION.SDK_INT > 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //connection
        try{
            this.connection = MainActivity.createConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from v_confirm");
            int i = 0;
            while(rs.next()){
                mProductsList.add(new ProductsList(rs.getString(1), rs.getString(2)));
                i++;
            }
            connection.close();

        }catch (Exception e){
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            Log.i(TAG, sw.toString());
        }

        setData(mProductsList);

    }

    private void setData(ArrayList<ProductsList> mProductsList) {

        float start = 1f;

        ArrayList<BarEntry> values = new ArrayList<>();
        ArrayList<BarEntry> values1 = new ArrayList<>();
        ArrayList<BarEntry> values2 = new ArrayList<>();
        ArrayList<BarEntry> values3 = new ArrayList<>();

        values.add(new BarEntry(1, Float.parseFloat(mProductsList.get(0).getLine2()), getResources().getDrawable(R.drawable.star)));
        values1.add(new BarEntry(2, Float.parseFloat(mProductsList.get(1).getLine2()), getResources().getDrawable(R.drawable.star)));
        values2.add(new BarEntry(3, Float.parseFloat(mProductsList.get(2).getLine2()), getResources().getDrawable(R.drawable.star)));
        values3.add(new BarEntry(4, Float.parseFloat(mProductsList.get(3).getLine2()), getResources().getDrawable(R.drawable.star)));

        BarDataSet set1, set2, set3, set4;





            int startColor1 = ContextCompat.getColor(this, android.R.color.holo_orange_light);
            int startColor2 = ContextCompat.getColor(this, android.R.color.holo_blue_light);
            int startColor3 = ContextCompat.getColor(this, android.R.color.holo_purple);
            int startColor4 = ContextCompat.getColor(this, android.R.color.holo_green_light);
            int startColor5 = ContextCompat.getColor(this, android.R.color.holo_red_light);

            int endColor1 = ContextCompat.getColor(this, android.R.color.holo_blue_dark);
            int endColor2 = ContextCompat.getColor(this, android.R.color.holo_purple);
            int endColor3 = ContextCompat.getColor(this, android.R.color.holo_green_dark);
            int endColor4 = ContextCompat.getColor(this, android.R.color.holo_red_dark);
            int endColor5 = ContextCompat.getColor(this, android.R.color.holo_orange_dark);

            List<GradientColor> gradientFills = new ArrayList<>();
            gradientFills.add(new GradientColor(startColor1, startColor1));
            gradientFills.add(new GradientColor(startColor2, startColor2));
            gradientFills.add(new GradientColor(startColor3, startColor3));
            gradientFills.add(new GradientColor(startColor4, startColor4));
            gradientFills.add(new GradientColor(startColor5, startColor5));
//
//            set1.setColor(startColor1);
//
//            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
//            dataSets.add(set1);
//
//            BarData data = new BarData(dataSets);

//            data.setValueTypeface(tfLight);


            // create 4 DataSets
            set1 = new BarDataSet(values, mProductsList.get(0).getLine1());
            set1.setDrawIcons(false);
            set1.setColor(startColor1);
            set2 = new BarDataSet(values1, mProductsList.get(1).getLine1());
            set2.setDrawIcons(false);
            set2.setColor(startColor2);
            set3 = new BarDataSet(values2, mProductsList.get(2).getLine1());
            set3.setDrawIcons(false);
            set3.setColor(startColor3);
            set4 = new BarDataSet(values3, mProductsList.get(3).getLine1());
            set4.setDrawIcons(false);
            set4.setColor(startColor4);

            BarData data = new BarData(set1, set2, set3, set4);
            data.setValueFormatter(new LargeValueFormatter());
            data.setValueTextSize(10f);
            data.setBarWidth(0.9f);
//            data.setValueTypeface(tfLight);

            chart.setData(data);


//            chart.setData(data);

    }
}