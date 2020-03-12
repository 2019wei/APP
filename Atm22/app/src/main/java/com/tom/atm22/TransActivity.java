package com.tom.atm22;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TransActivity extends AppCompatActivity {

    private static final String TAG = TransActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans);
        // http://atm201605.appspot.com/h
        new TransTask().execute("http://atm201605.appspot.com/h");
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://atm201605.appspot.com/h").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.d(TAG, "onResponse: " + response.body().string());
                parseJSON(response.body().string());
            }
        });

    }

    private void parseJSON(String json) {
        List<Transaction> transactions = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                Transaction tran = new Transaction(object);
                transactions.add(tran);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public class TransTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
             StringBuilder sb = null;
            try {
                URL url = new URL(strings[0]);
                InputStream is =  url.openStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(is));
                String line =  in.readLine();
                 sb = new StringBuilder();
                while (line != null){
                    sb.append(line);
                    line = in.readLine();
                }
                Log.d(TAG, "onCreate: " + sb.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(TAG, "onPostExecute: " + s);
        }
    }
}
