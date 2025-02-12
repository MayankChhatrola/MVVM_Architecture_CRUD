package com.demo.blackbox.mvvmarchitecturecrud.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.demo.blackbox.mvvmarchitecturecrud.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDao extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "employee_database";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_EMPLOYEES = "employees";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_POSITION = "position";

    public EmployeeDao(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_EMPLOYEES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_POSITION + " TEXT" + ")";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEES);
        onCreate(db);
    }

    public void insertEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, employee.getName());
        values.put(COLUMN_POSITION, employee.getPosition());
        db.insert(TABLE_EMPLOYEES, null, values);
        db.close();
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_EMPLOYEES, null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
                String position = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_POSITION));
                employeeList.add(new Employee(id, name, position));
            } while (cursor.moveToNext());
            cursor.close();
        }

        return employeeList;
    }

    public void updateEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, employee.getName());
        values.put(COLUMN_POSITION, employee.getPosition());
        db.update(TABLE_EMPLOYEES, values, COLUMN_ID + "=?", new String[]{String.valueOf(employee.getId())});
        db.close();
    }

    public void deleteEmployee(int employeeId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EMPLOYEES, COLUMN_ID + "=?", new String[]{String.valueOf(employeeId)});
        db.close();
    }
}