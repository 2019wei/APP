package com.tom.atm22;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static final int REQUSET_LOGIN = 100;
    boolean logon = false;
    String[] functions = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(!logon){
            Intent intent = new Intent(this,LoginActivity.class);
//            startActivity(intent);
            startActivityForResult(intent,REQUSET_LOGIN);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Adapter
        //functions = getResources().getStringArray(R.array.functions);
        FunctionAdapter adapter = new FunctionAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUSET_LOGIN){
            if (resultCode != RESULT_OK){
                finish();
            }
        }

    }
}
