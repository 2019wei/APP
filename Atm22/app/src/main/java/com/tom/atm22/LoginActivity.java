package com.tom.atm22;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText edUserid;
    private EditText edPassword;
    private CheckBox cbRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSharedPreferences("atm",MODE_PRIVATE)
                .edit()
                .putInt("LEVEL",3)
                .putString("NAME","Tom")
                .apply();
        int level = getSharedPreferences("atm",MODE_PRIVATE)
                .getInt("LEVEL",0);
        Log.d(TAG, "onCreate: "+level);
        edUserid = findViewById(R.id.userid);
        edPassword = findViewById(R.id.passwd);
        cbRemember = findViewById(R.id.cb_rem_userid);
        cbRemember.setChecked(getSharedPreferences("atm",MODE_PRIVATE).getBoolean("REMEBER_USERID",false));
        Boolean remeber_userid = getSharedPreferences("atm",MODE_PRIVATE).getBoolean("REMEBER_USERID",false);
        cbRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                getSharedPreferences("atm",MODE_PRIVATE)
                        .edit()
                        .putBoolean("REMEBER_USERID",isChecked)
                        .apply();
            }
        });
         String userid = getSharedPreferences("atm",MODE_PRIVATE)
                .getString("USERID","");
         if(remeber_userid){
         edUserid.setText(userid);
         }else
         {
             edUserid.setText("");
         }

    }
    public void  login(View view){
            final String userid = edUserid.getText().toString();
            final String passwd = edPassword.getText().toString();
        FirebaseDatabase.getInstance().getReference("users")
                .child(userid).child("password")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String pw = (String) dataSnapshot.getValue();
                        if (pw.equals(passwd)){
                            boolean remeber = getSharedPreferences("atm",MODE_PRIVATE)
                                    .getBoolean("REMEBER_USERID",false);
                            if(remeber){
                            //save userid
                            getSharedPreferences("atm",MODE_PRIVATE)
                                    .edit()
                                    .putString("USERID",userid)
                                    .apply();
                            }
                            setResult(RESULT_OK);
                            Log.d(TAG, "login: login");
                            finish();
                        }else {
                            new AlertDialog.Builder(LoginActivity.this)
                                    .setTitle("登入結果")
                                    .setMessage("登入失敗")
                                    .setPositiveButton("ok",null)
                                    .show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            /*if("jack".equals(userid) && "1234".equals(passwd)){
                setResult(RESULT_OK);
                Log.d(TAG, "login: login");
                finish();
            }*/
    }
    public  void  quit(View view){

    }
}
