package com.tom.mssqltest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ArrayList<Data> list;
    private Listadapter listadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.Btn);
        list = new ArrayList<>();
        listadapter =new Listadapter(list);

        ListView listView = findViewById(R.id.Listviews);
        listView.setAdapter(listadapter);
    }
    public void Bsql(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    insert("測試資料","M");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
    public void insert(String a ,String b) throws Exception {
        Connection m_con =null;
        String sql = "SELECT * from [KaiShingPlug].[dbo].[TEST1203] " ;
        PreparedStatement pstmt = null;
        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        Log.d(TAG, "insert: 加載驅動 成功");
//        m_con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+ "OWNER-PC" + ":1433/" + "MVC_UserDB" ,"sa", "vispark");
        m_con = DriverManager.getConnection("jdbc:jtds:sqlserver://" + "192.168.0.14" + ";" + "user=" + "sa" + ";password=" + "vispark" + ";");
        //連接
        if(m_con != null){
            Log.d(TAG, "insert: 連接資料庫成功");
        }
        try {
            list.clear();
            pstmt = m_con.prepareStatement(sql);
            //
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                String id = rs.getString(1);
                String name = rs.getString(2);
                list.add(new Data(id,name));
            }
            Log.d(TAG, "insert: 更新成功");
            pstmt.close();
        }catch (Exception e){
            throw new Exception("更新失敗");
        }finally {
            handler.sendEmptyMessage(0);
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    listadapter.notifyDataSetChanged();
//                }
//            });
            m_con.close();
        }

    }


    private Handler handler =new Handler( new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            listadapter.notifyDataSetChanged();
            return true;
        }
    });










}
