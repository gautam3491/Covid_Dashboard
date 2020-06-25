package com.example.info706_c2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static com.example.info706_c2.MyAppUtils.DEFAULT_DRIVER;
import static com.example.info706_c2.MyAppUtils.DEFAULT_PASSWORD;
import static com.example.info706_c2.MyAppUtils.DEFAULT_URL;
import static com.example.info706_c2.MyAppUtils.DEFAULT_USERNAME;
import static com.example.info706_c2.MyAppUtils.TAG;



public class MainActivity extends AppCompatActivity {

    private Connection connection;
    ArrayList<ProductsList> mProductsList;
    CardView cardpie, cardcol, cardgauge, cardrow, cardline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardpie = findViewById(R.id.card_pie);
        cardcol = findViewById(R.id.card_col);
        cardgauge = findViewById(R.id.card_gauge);
        cardrow = findViewById(R.id.card_row);
        cardline = findViewById(R.id.card_line);

        cardpie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPie();
            }
        });

        cardcol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCol();
            }
        });

        cardgauge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGauge();
            }
        });

        cardrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRow();
            }
        });

        cardline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLine();
            }
        });
    }

    public void openPie(){
        Intent intent = new Intent(this, PieCharts.class);
        startActivity(intent);
    }

    public void openCol(){
        Intent intent = new Intent(this, BarCharts.class);
        startActivity(intent);
    }

    public void openGauge(){
        Intent intent = new Intent(this, HalfPieCharts.class);
        startActivity(intent);
    }

    public void openRow(){
        Intent intent = new Intent(this, RowCharts.class);
        startActivity(intent);
    }

    public void openLine(){
        Intent intent = new Intent(this, LineCharts.class);
        startActivity(intent);
    }

    public void goOracle(View V){
        try{
            this.connection = createConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select DHB,(maori+pacific+asian+other)total from covid_test_ethnicity order by total desc FETCH NEXT 5 ROWS ONLY");
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

//        setData(mProductsList,2);
    }

    public static Connection createConnection(String driver, String url, String username, String password) throws ClassNotFoundException, SQLException {

        Class.forName(driver);
        return DriverManager.getConnection(url, username, password);
    }

    public static Connection createConnection() throws ClassNotFoundException, SQLException {
        return createConnection(DEFAULT_DRIVER, DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
    }


}
