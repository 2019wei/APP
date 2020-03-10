package com.tom.atm22.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {Expense.class,Word.class},version = 1)
public abstract class ExpenseDatabase extends RoomDatabase {
    public abstract ExpenseDao expenseDao();

}
