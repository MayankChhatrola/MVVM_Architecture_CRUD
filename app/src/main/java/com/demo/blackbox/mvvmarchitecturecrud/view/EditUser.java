package com.demo.blackbox.mvvmarchitecturecrud.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.demo.blackbox.mvvmarchitecturecrud.R;
import com.demo.blackbox.mvvmarchitecturecrud.model.User;
import com.demo.blackbox.mvvmarchitecturecrud.viewmodel.UserViewModel;

public class EditUser extends AppCompatActivity {

    private EditText editNameInput, editEmailInput;
    private Button saveButton;
    private UserViewModel userViewModel;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        editNameInput = findViewById(R.id.editNameInput);
        editEmailInput = findViewById(R.id.editEmailInput);
        saveButton = findViewById(R.id.saveButton);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        Intent intent = getIntent();
        userId = intent.getIntExtra("userId", -1);
        String name = intent.getStringExtra("userName");
        String email = intent.getStringExtra("userEmail");

        editNameInput.setText(name);
        editEmailInput.setText(email);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updatedName = editNameInput.getText().toString().trim();
                String updatedEmail = editEmailInput.getText().toString().trim();

                if (!updatedName.isEmpty() && !updatedEmail.isEmpty()) {
                    User updatedUser = new User(userId, updatedName, updatedEmail);
                    userViewModel.updateUser(updatedUser);
                    Toast.makeText(EditUser.this, "User updated", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(EditUser.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}