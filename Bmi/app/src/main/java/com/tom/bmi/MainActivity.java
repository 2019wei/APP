package com.tom.bmi;

import android.content.DialogInterface;
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

    private EditText edWeight;
    private EditText edHeight;
    private TextView result;
    private Button help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
    }

    private void findViews() {
        edWeight = findViewById(R.id.ed_weight);
        edHeight = findViewById(R.id.ed_height);
        result = findViewById(R.id.result);
        help = findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this).setTitle("訊息")
                        .setMessage("thihithithihti")
                        .setPositiveButton("ok",null)
                        .show();
            }
        });

    }

    public void bmi(View view){
        String w = edWeight.getText().toString();
        String h = edHeight.getText().toString();
        if(w.isEmpty()  || h.isEmpty()){
            new AlertDialog.Builder(this)
                    .setTitle("錯誤訊息")
                    .setMessage("內容不可以空白喔")
                    .setPositiveButton("close",null)
                    .show();
        }
        else
            {
            float weight = Float.parseFloat(w);
            float height = Float.parseFloat(h);
            float bmi = weight / (height * height);
            Log.d("Main", "bmi:" + bmi);
            Toast.makeText(this, "your Bmi is" + bmi, Toast.LENGTH_LONG).show();
            result.setText("Your bmi is " + bmi);
            new AlertDialog.Builder(this)
                    .setTitle("BMI")
                    .setMessage("your bmi" + bmi)
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            edWeight.setText("");
                            edHeight.setText("");
                        }
                    })
                    .show();
            }
    }
}
