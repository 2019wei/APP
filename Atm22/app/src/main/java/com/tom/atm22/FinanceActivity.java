package com.tom.atm22;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.FirebaseDatabase;
import com.tom.atm22.data.Expense;
import com.tom.atm22.data.ExpenseDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FinanceActivity extends AppCompatActivity {

    private static final String TAG = FinanceActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);
        final ExpenseDatabase database = ExpenseDatabase.getInstance(this);
//        final ExpenseDatabase database = Room.databaseBuilder(this, ExpenseDatabase.class,"expense.db").build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                database.expenseDao().insert(new Expense("2020-01-07","停車費",75));
            }
        }).start();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<Expense> expenses =  database.expenseDao().getAll();
               for (Expense expens : expenses) {
                    Log.d(TAG, "Expense: " + expens.getDate() + "/" +expens.getInfo() + "/" + expens.getAmount());
                }
            }
        });
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                List<Expense> expenses =  database.expenseDao().getAll();
//                for (Expense expens : expenses) {
//                    Log.d(TAG, "Expense: " + expens.getDate() + "/" +expens.getInfo() + "/" + expens.getAmount());
//                }
//            }
//        }).start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_finance,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_expense_to_firebase){
            final String userid = getSharedPreferences("atm",MODE_PRIVATE)
                    .getString("USERID",null);
            if(userid != null){
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        List<Expense> expenses =  ExpenseDatabase.getInstance(FinanceActivity.this).expenseDao().getAll();
                        FirebaseDatabase.getInstance().getReference("users").child(userid).child("expenses")
                                .setValue(expenses);
                    }
                });

            }


        }
        return super.onOptionsItemSelected(item);
    }
}

