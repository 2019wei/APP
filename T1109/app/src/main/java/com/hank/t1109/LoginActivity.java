package com.hank.t1109;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private EditText edUserid;
    private EditText edPasswd;
    private CheckBox cbRemeber;
    private boolean rember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSharedPreferences("atm",MODE_PRIVATE).edit()
                .putInt("Level",3)
                .putString("Name","Tom")
                .commit();
        int level =  getSharedPreferences("atm",MODE_PRIVATE).getInt("Level",0);
        Log.d(TAG, "onCreate:" + level);
        edUserid = findViewById(R.id.userid);
        edPasswd = findViewById(R.id.passwd);
        cbRemeber = findViewById(R.id.cb_remeber);
        cbRemeber.setChecked(
                getSharedPreferences("atm",MODE_PRIVATE)
                        .getBoolean("Rember_userid",false));
        cbRemeber.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                getSharedPreferences("atm",MODE_PRIVATE)
                        .edit()
                        .putBoolean("Rember_userid",isChecked)
                        .apply();
            }
        });
        String userid = getSharedPreferences("atm", MODE_PRIVATE).getString("Userid","");
        if(getSharedPreferences("atm",MODE_PRIVATE)
        .getBoolean("Rember_userid",false)){
            edUserid.setText(userid);
        }else {edUserid.setText("");}
    }

    public void login(View view){
        String userid = edUserid.getText().toString();
        String passwd = edPasswd.getText().toString();
        if("jack".equals(userid) && "1234".equals(passwd)){
            rember = getSharedPreferences("atm",MODE_PRIVATE)
                    .getBoolean("Rember_userid",false);
            if(rember){
                //save userid
                getSharedPreferences("atm",MODE_PRIVATE).edit()
                        .putString("Userid",userid)
                        .apply();
                //
            }

            setResult(RESULT_OK);
            finish();
        }
    }

    public void quit(View view){

    }
}
