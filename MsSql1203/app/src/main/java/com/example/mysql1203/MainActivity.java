package com.example.mysql1203;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "15151515";
    private EditText id;
    private EditText name;
    private ListView lv;
    private ArrayList<String> list;
    private ListAdapter1 adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.Btn1).setOnClickListener(event);
        id = findViewById(R.id.editText1);
        name = findViewById(R.id.editText2);
        lv = findViewById(R.id.LT);
        list = new ArrayList<>();
        adapter = new ListAdapter1(list);
        lv.setAdapter(adapter);


        if (Build.VERSION.SDK_INT >= 23) {  // ANDROID 6
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());}
    }

    private View.OnClickListener event =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            insert();
        }
    };
    protected void insert(){
        try {
            String idtext = id.getText().toString();
            String nametext = name.getText().toString();
            list.clear();

            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String url  = "jdbc:jtds:sqlserver://192.168.0.14/";
            Connection c = DriverManager.getConnection(url,"sa","vispark");
           PreparedStatement st =  c.prepareStatement("SELECT * from [KaiShingPlug].[dbo].[TEST1203] ");
           //st.setString(1,idtext);
            //st.setString(2,nametext);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                list.add(rs.getString(1)+rs.getString(2));
            }

            st.close();
            c.close();
        }
        catch (ClassNotFoundException e ){
            e.printStackTrace();

        }catch (SQLException e){
            e.printStackTrace();
        }

        adapter.notifyDataSetChanged();


    }





}
