package com.hank.test1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private  static  final String TAG =MainActivity.class.getSimpleName();
    private EditText edWeight;
    private EditText edheight;
    private TextView result;
    private Button help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_main);
        findviews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    private void findviews() {
        edWeight = findViewById(R.id.ed_weight);
        edheight = findViewById(R.id.ed_height);
        result = findViewById(R.id.result);
        help = findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this).setTitle(R.string.help).setMessage("bmi is ....").setPositiveButton(R.string.ok,null)
                        .show();
            }
        });
    }

    public  void  bmi(View view){
        String w = edWeight.getText().toString();
        String h = edheight.getText().toString();
        Float weight = Float.parseFloat(w);
        Float height = Float.parseFloat(h);
        Float bmi = weight / (height * height);
        Log.d("MainActivity", "bmi: " + bmi);
        Toast.makeText(this,getString(R.string.you_bmi_is)+bmi,Toast.LENGTH_LONG).show();
        result.setText("you bmi is:"+ bmi);
        Intent intent = new Intent(this,ResultActivity.class);
        intent.putExtra("BMI",bmi);
        startActivity(intent);
//        new AlertDialog.Builder(this).setTitle("BMI").setMessage("your bmi is" + bmi)
//                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        edheight.setText("");
//                        edWeight.setText("");
//                    }
//                }).show();
    }

}
