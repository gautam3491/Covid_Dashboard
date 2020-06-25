package com.example.info706_c2;

public class MyAppUtils {
    public static  MyAppUtils _INSTANCE = new MyAppUtils();

    //Oracle connection
    public static final String DEFAULT_DRIVER = "oracle.jdbc.driver.OracleDriver";
    public static final String DEFAULT_URL = "jdbc:oracle:thin:@info706.cwwvo42siq12.ap-southeast-2.rds.amazonaws.com:1521:orcl";
    public static final String DEFAULT_USERNAME = "20482502";
    public static final String DEFAULT_PASSWORD = "12345";

    //Locat TAG
    public static final String TAG = "MyDB";

    private MyAppUtils(){
        //no instance allowed - stop replication
    }

    public static MyAppUtils getInstance(){
        return _INSTANCE;
    }
}
