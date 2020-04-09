package com.tom.mssqltest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ArrayList<Data> list;
    private Listadapter listadapter;
    private ProgressDialog progressD;
    private GifImageView gifImageView;
    private Intent intent;
    private String json;
    private ArrayList<Transaction> transactions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.Btn);
        list = new ArrayList<>();
        listadapter =new Listadapter(list);

        ListView listView = findViewById(R.id.Listviews);
        listView.setAdapter(listadapter);

        //ProgressDialog
        progressD = new ProgressDialog(this);
        progressD.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressD.setTitle(R.string.progressbar);
        progressD.setProgressNumberFormat("");
        progressD.setCancelable(false);
        progressD.setIndeterminate(false);
        progressD.setMax(100);
        //GIF 動態圖   implementation 'pl.droidsonroids.gif:android-gif-drawable:1.2.19'
        gifImageView = findViewById(R.id.imageView);
        try {
            GifDrawable gifDrawable = new GifDrawable(getResources(),R.drawable.pic);
            gifImageView.setImageDrawable(gifDrawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //延遲毫秒轉換
//        https://atm201605.appspot.com/h
//        intent = new Intent(MainActivity.this,Main2Activity.class);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(5000);
//                    startActivity(intent);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

        //OKhttp
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder().url("https://atm201605.appspot.com/h").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                json = response.body().string();
                Log.d(TAG, "onResponse: " );
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        parserGSON(json);
                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: " + "開啟");
    }


    public void Bsql(View view){
        progressD.show();
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

    private void parserGSON(String json) {
        Gson gson = new Gson();
        transactions = new ArrayList<>();
        transactions = gson.fromJson(json,
                new TypeToken<ArrayList<Transaction>>(){}.getType());
        Log.d(TAG, "parserGSON: " + transactions);

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
//            pstmt = m_con.prepareStatement(sql);

            pstmt = m_con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
           //須設定ResultSet的Type ， 这是默认的，它是ResultSet.TYPE_FORWARD_ONLY，这意味着你只能使用rs.next()
            // 若要使用last().等其他的ResultSet方法 需要加上ResultSet的Type
            // 沒有加ResultSet的Type會報錯 E/Exception: ResultSet may only be accessed in a forward direction.
            ResultSet rs = pstmt.executeQuery();

            rs.last();
            int count = rs.getRow();
            Log.d(TAG, "insert: "+count);
            rs.beforeFirst();

            int i = 1;

            while (rs.next()){
                String id = rs.getString(1);
                String name = rs.getString(2);
                list.add(new Data(id,name));

                Message m = new Message();
                m.what = (int) (i/count) * 100;
                handler2.sendMessage(m);
                i++;

            }
            Log.d(TAG, "insert: 更新成功");
            pstmt.close();
        }catch (Exception e){
            Log.e("Exception",e.getMessage());
            throw new Exception("更新失敗");
        }finally {
            handler.sendEmptyMessage(0);
//            getHandler.sendEmptyMessage(0);
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    listadapter.notifyDataSetChanged();
//                }
//            });
            m_con.close();
            progressD.dismiss();
        }

    }



    private Handler handler =new Handler( new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Log.d(TAG, "handleMessage: "+list.size());
            listadapter.notifyDataSetChanged();
            return true;
        }
    });

    private Handler handler2 =new Handler( new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            progressD.setProgress(msg.what);
            return true;
        }
    });











}
