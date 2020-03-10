package com.tom.atm22;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;

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
        final ExpenseDatabase database = Room.databaseBuilder(this, ExpenseDatabase.class,"expense.db").build();
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
}

