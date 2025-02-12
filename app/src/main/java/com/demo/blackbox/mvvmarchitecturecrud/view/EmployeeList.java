package com.demo.blackbox.mvvmarchitecturecrud.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.blackbox.mvvmarchitecturecrud.R;
import com.demo.blackbox.mvvmarchitecturecrud.adapter.EmployeeAdapter;
import com.demo.blackbox.mvvmarchitecturecrud.model.Employee;
import com.demo.blackbox.mvvmarchitecturecrud.viewmodel.EmployeeViewModel;

import java.util.List;


public class EmployeeList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EmployeeAdapter employeeAdapter;
    private EmployeeViewModel employeeViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);

        recyclerView = findViewById(R.id.recyclerViewEmployee);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        employeeAdapter = new EmployeeAdapter(this);
        recyclerView.setAdapter(employeeAdapter);

        employeeViewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);

        employeeViewModel.getAllEmployees().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                employeeAdapter.setEmployees(employees);
            }
        });
    }
}