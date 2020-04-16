package com.tom.mssqltest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.tom.mssqltest.data.Expense;
import com.tom.mssqltest.data.ExpenseDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FinanceActivity extends AppCompatActivity {

    private static final String TAG = FinanceActivity.class.getSimpleName();
    private List<Expense> expenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);
        final ExpenseDatabase database = Room.databaseBuilder(this, ExpenseDatabase.class,"expense.db").build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                database.expenseDao().insert(new Expense("2020-01-03","Parking",75));
            }
        }).start();
        //類似Thread的方法
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                expenses = database.expenseDao().getAll();
                for (Expense expense : expenses) {
                    Log.d(TAG, "Expense: " + expense.getDate() +"/"+expense.getInfo()+ "/"+expense.getAmount());
                }
            }
        });
         //Listview
        List2 listadapter = new List2(expenses);
        ListView listView = findViewById(R.id.listview);
        listView.setAdapter(listadapter);



    }
}
