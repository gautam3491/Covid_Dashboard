package com.example.info706_c2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static com.example.info706_c2.MyAppUtils.TAG;

public class RowCharts extends AppCompatActivity {
    private HorizontalBarChart chart;
    ArrayList<ProductsList> mProductsList;
    private Connection connection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_row_charts);

        setTitle("Probable Cases");

        chart = findViewById(R.id.chart1);
        // chart.setHighlightEnabled(false);

        chart.setDrawBarShadow(false);

        chart.setDrawValueAboveBar(true);

        chart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        chart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false);

        // draw shadows for each bar that show the maximum value
        // chart.setDrawBarShadow(true);

        chart.setDrawGridBackground(false);

        XAxis xl = chart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawAxisLine(true);
        xl.setDrawGridLines(false);
        xl.setGranularity(10f);

        YAxis yl = chart.getAxisLeft();
        yl.setDrawAxisLine(true);
        yl.setDrawGridLines(true);
        yl.setAxisMinimum(0f); // this replaces setStartAtZero(true)
//        yl.setInverted(true);

        YAxis yr = chart.getAxisRight();
        yr.setEnabled(false);
//        yr.setDrawAxisLine(true);
//        yr.setDrawGridLines(false);
//        yr.setAxisMinimum(0f); // this replaces setStartAtZero(true)
////        yr.setInverted(true);

        chart.setFitBars(true);
        chart.animateY(1000);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(8f);
        l.setXEntrySpace(4f);

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
            ResultSet rs = stmt.executeQuery("select * from v_probable");
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

        float barWidth = 0.9f;
        float spaceForBar = 10f;
        ArrayList<BarEntry> values = new ArrayList<>();
        ArrayList<BarEntry> values1 = new ArrayList<>();
        ArrayList<BarEntry> values2 = new ArrayList<>();

        values.add(new BarEntry(1, Float.parseFloat(mProductsList.get(0).getLine2()), getResources().getDrawable(R.drawable.star)));
        values1.add(new BarEntry(2, Float.parseFloat(mProductsList.get(1).getLine2()), getResources().getDrawable(R.drawable.star)));
        values2.add(new BarEntry(3, Float.parseFloat(mProductsList.get(2).getLine2()), getResources().getDrawable(R.drawable.star)));

        BarDataSet set1, set2, set3;

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

        set1 = new BarDataSet(values, mProductsList.get(0).getLine1());
        set1.setDrawIcons(false);
        set1.setColor(startColor1);
        set2 = new BarDataSet(values1, mProductsList.get(1).getLine1());
        set2.setDrawIcons(false);
        set2.setColor(startColor2);
        set3 = new BarDataSet(values2, mProductsList.get(2).getLine1());
        set3.setDrawIcons(false);
        set3.setColor(startColor3);

        BarData data = new BarData(set1, set2, set3);
        data.setValueTextSize(10f);
        data.setBarWidth(barWidth);

        chart.setData(data);

    }
}