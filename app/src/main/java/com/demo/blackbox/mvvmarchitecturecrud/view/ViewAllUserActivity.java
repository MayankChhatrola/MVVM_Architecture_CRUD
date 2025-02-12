package com.demo.blackbox.mvvmarchitecturecrud.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.blackbox.mvvmarchitecturecrud.R;
import com.demo.blackbox.mvvmarchitecturecrud.adapter.UserAdapter;
import com.demo.blackbox.mvvmarchitecturecrud.model.User;
import com.demo.blackbox.mvvmarchitecturecrud.viewmodel.UserViewModel;

import java.util.List;

public class ViewAllUserActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_user);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userAdapter = new UserAdapter(this);
        recyclerView.setAdapter(userAdapter);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        userViewModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                userAdapter.setUsers(users);
            }
        });
    }
}