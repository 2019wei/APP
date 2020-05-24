package com.hank.currency;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edcurrency;
    private TextView ucurrency;
    private TextView jcurrency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
    }

    private void findViews() {
        edcurrency = findViewById(R.id.editText);
        ucurrency = findViewById(R.id.textView2);
        jcurrency = findViewById(R.id.textView3);
    }

    public void currency(View view){
        String txt = edcurrency.getText().toString();
        if(txt.isEmpty()){
            new AlertDialog.Builder(this)
                    .setTitle("錯誤資訊")
                    .setMessage("請輸入數字")
                    .setPositiveButton("close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            edcurrency.setText("");
                        }
                    })
                    .show();
        }
        else
            {
            float total = Float.parseFloat(txt);
            float uresult = total / 30.5f;
            float jresylt = total / 0.28f;
            ucurrency.setText("美元:" + uresult + "元");
            jcurrency.setText("日元:" + jresylt + "元");
            Toast.makeText(this,"美元"+uresult+"日幣"+jresylt,Toast.LENGTH_LONG).show();
            new AlertDialog.Builder(this)
                    .setTitle("幣值")
                    .setMessage("美金"+uresult+"" + "日幣"+jresylt)
                    .show();
            }
    }
}
