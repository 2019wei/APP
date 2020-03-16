package com.tom.atm22;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REQUEST_CODE_CAMERA = 5;
    private EditText edUserid;
    private EditText edPassword;
    private CheckBox cbRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Fragment
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.container_news,NewsFragment.getInstance());
//        fragmentTransaction.add(R.id.container_news,new NewsFragment());
        fragmentTransaction.add(R.id.blank_news,BlankFragment.newInstance("",""));
        fragmentTransaction.commit();
//        camera();

//        settingsTest();
        findViews();
        new TestTask().execute("http://tw.yahoo.com");
    }

    public class TestTask extends AsyncTask<String,Void,Integer>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG, "onPreExecute: ");
            Toast.makeText(LoginActivity.this,"onPreExecute",Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            Log.d(TAG, "onPostExecute: ");
            Toast.makeText(LoginActivity.this,"onPostExecute :" +integer,Toast.LENGTH_LONG).show();
        }

        @Override
        protected Integer doInBackground(String... strings) {
            int data = 0;
            try {
                URL url = new URL(strings[0]);
                data = url.openStream().read();
                Log.d(TAG, "onCreate: " + data);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }
    }

    private void findViews() {
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

    private void settingsTest() {
        getSharedPreferences("atm",MODE_PRIVATE)
                .edit()
                .putInt("LEVEL",3)
                .putString("NAME","Tom")
                .apply();
        int level = getSharedPreferences("atm",MODE_PRIVATE)
                .getInt("LEVEL",0);
        Log.d(TAG, "onCreate: "+level);
    }

    private void camera() {
        int permission =  ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if(permission == PackageManager.PERMISSION_GRANTED){
//            takePhoto();
        }else {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},REQUEST_CODE_CAMERA);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_CAMERA){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //takePhoto();
            }
        }
    }

    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
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
