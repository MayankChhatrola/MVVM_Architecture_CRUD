package com.demo.blackbox.mvvmarchitecturecrud.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.demo.blackbox.mvvmarchitecturecrud.R;
import com.demo.blackbox.mvvmarchitecturecrud.model.Employee;
import com.demo.blackbox.mvvmarchitecturecrud.viewmodel.EmployeeViewModel;

public class EditEmployeeActivity extends AppCompatActivity {

    private EditText editEmployeeName, editEmployeePosition;
    private Button saveButton;
    private EmployeeViewModel employeeViewModel;
    private int employeeId;
    private String employeeName, employeePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_employee);

        editEmployeeName = findViewById(R.id.editEmployeeName);
        editEmployeePosition = findViewById(R.id.editEmployeePosition);
        saveButton = findViewById(R.id.saveEmployeeButton);

        employeeId = getIntent().getIntExtra("employeeId", -1);
        employeeName = getIntent().getStringExtra("employeeName");
        employeePosition = getIntent().getStringExtra("employeePosition");

        if (employeeName != null && employeePosition != null) {
            editEmployeeName.setText(employeeName);
            editEmployeePosition.setText(employeePosition);
        }

        employeeViewModel = new EmployeeViewModel(getApplication());

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = editEmployeeName.getText().toString().trim();
                String newPosition = editEmployeePosition.getText().toString().trim();

                if (newName.isEmpty() || newPosition.isEmpty()) {
                    Toast.makeText(EditEmployeeActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {

                    Employee updatedEmployee = new Employee(employeeId, newName, newPosition);
                    employeeViewModel.update(updatedEmployee);

                    Toast.makeText(EditEmployeeActivity.this, "Employee updated successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}