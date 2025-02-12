package com.demo.blackbox.mvvmarchitecturecrud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.demo.blackbox.mvvmarchitecturecrud.model.User;
import com.demo.blackbox.mvvmarchitecturecrud.view.AddEmployeeActivity;
import com.demo.blackbox.mvvmarchitecturecrud.view.ViewAllUserActivity;
import com.demo.blackbox.mvvmarchitecturecrud.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity {

    private EditText nameInput, emailInput;
    private Button addButton, viewAllButton, addEmployeeButton;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = findViewById(R.id.nameInput);
        emailInput = findViewById(R.id.emailInput);
        addButton = findViewById(R.id.addButton);
        viewAllButton = findViewById(R.id.viewAllButton);
        addEmployeeButton = findViewById(R.id.addEmployeeButton);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameInput.getText().toString().trim();
                String email = emailInput.getText().toString().trim();

                if (!name.isEmpty() && !email.isEmpty()) {
                    User user = new User(name, email);
                    userViewModel.addUser(user);
                    Toast.makeText(MainActivity.this, "User added", Toast.LENGTH_SHORT).show();
                    nameInput.setText("");
                    emailInput.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewAllUserActivity.class);
                startActivity(intent);
            }
        });

        addEmployeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddEmployeeActivity.class);
                startActivity(intent);
            }
        });
    }
}