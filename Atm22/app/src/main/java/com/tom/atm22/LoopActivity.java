package com.tom.atm22;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class LoopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loop);
        TextView result = findViewById(R.id.textname);
        String name =  getIntent().getStringExtra("Name");
        result.setText(name);
    }
}
