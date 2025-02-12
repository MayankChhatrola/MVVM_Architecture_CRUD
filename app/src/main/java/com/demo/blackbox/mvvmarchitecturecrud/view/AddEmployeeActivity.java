package com.demo.blackbox.mvvmarchitecturecrud.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.demo.blackbox.mvvmarchitecturecrud.R;
import com.demo.blackbox.mvvmarchitecturecrud.model.Employee;
import com.demo.blackbox.mvvmarchitecturecrud.viewmodel.EmployeeViewModel;

public class AddEmployeeActivity extends AppCompatActivity {

    private EditText editEmployeeName, editEmployeePosition;
    private Button addEmployeeButton, viewAllEmployeeButton;
    private EmployeeViewModel employeeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        editEmployeeName = findViewById(R.id.editEmployeeName);
        editEmployeePosition = findViewById(R.id.editEmployeePosition);
        addEmployeeButton = findViewById(R.id.addEmployeeButton);
        viewAllEmployeeButton = findViewById(R.id.viewAllEmployeeButton);

        employeeViewModel = new EmployeeViewModel(getApplication());

        addEmployeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editEmployeeName.getText().toString().trim();
                String position = editEmployeePosition.getText().toString().trim();

                if (name.isEmpty() || position.isEmpty()) {
                    Toast.makeText(AddEmployeeActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    Employee employee = new Employee(name, position);
                    employeeViewModel.insert(employee);

                    Toast.makeText(AddEmployeeActivity.this, "Employee added successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewAllEmployeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddEmployeeActivity.this, EmployeeList.class);
                startActivity(intent);
            }
        });
    }
}