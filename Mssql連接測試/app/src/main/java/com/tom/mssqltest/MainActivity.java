package com.tom.mssqltest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.Btn);
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
        String sql = "INSERT INTO [dbo].[UserTable] ([UserName],[UserSex]) VALUES(?,?)" ;
        PreparedStatement pstmt = null;
        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        Log.d(TAG, "insert: 加載驅動 成功");
//        m_con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+ "OWNER-PC" + ":1433/" + "MVC_UserDB" ,"sa", "vispark");
        m_con = DriverManager.getConnection("jdbc:jtds:sqlserver://" + "192.168.50.74" + ";" + "databaseName=" + "MVC_UserDB" + ";user=" + "sa" + ";password=" + "vispark" + ";");
        //連接
        if(m_con != null){
            Log.d(TAG, "insert: 連接資料庫成功");
        }
        try {
            pstmt = m_con.prepareStatement(sql);
            pstmt.setString(1,a);
            pstmt.setString(2,b);
            //
            pstmt.executeUpdate();
            Log.d(TAG, "insert: 更新成功");
            pstmt.close();
        }catch (Exception e){
            throw new Exception("更新失敗");
        }finally {
            m_con.close();
        }

    }




}
