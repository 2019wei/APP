package com.hank.test2;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText eduserid;
    private EditText edpasswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSharedPreferences("atm",MODE_PRIVATE).edit().putInt("LEVEL",3)
                .putString("NAME","Tom")
                .commit();
         int level = getSharedPreferences("atm",MODE_PRIVATE)
                .getInt("LEVEL",0);
        Log.d(LoginActivity.class.getSimpleName(), "onCreate: " + level);
        eduserid = findViewById(R.id.account);
        edpasswd = findViewById(R.id.passwd);
        String userid = getSharedPreferences("atm",MODE_PRIVATE)
                .getString("USERID","");
        eduserid.setText(userid);

    }
    public  void  login(View view){
        final String userid = eduserid.getText().toString();
        final String passwd = edpasswd.getText().toString();
        FirebaseDatabase.getInstance().getReference("users").child(userid).child("password")
              .addListenerForSingleValueEvent(new ValueEventListener() {
                  @Override
                  public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                      String pw =  dataSnapshot.getValue().toString();
                      if(pw.equals(passwd)){
                          //save
                          getSharedPreferences("atm",MODE_PRIVATE).edit()
                                  .putString("USERID",userid)
                                  .apply();
                          setResult(RESULT_OK);
                          finish();
                      }else
                          new AlertDialog.Builder(LoginActivity.this)
                                  .setTitle("登入結果")
                          .setMessage("登入失敗!!!")
                          .setPositiveButton("ok",null)
                          .show();
                  }

                  @Override
                  public void onCancelled(@NonNull DatabaseError databaseError) {

                  }
              });
//        if("jack".equals(userid) && "1234".equals(passwd)){
//            setResult(RESULT_OK);
//            finish();
//        }
    }

    public  void  quit(View view){

    }
}
