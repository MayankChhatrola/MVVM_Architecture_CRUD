package com.demo.blackbox.mvvmarchitecturecrud.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.demo.blackbox.mvvmarchitecturecrud.model.User;
import com.demo.blackbox.mvvmarchitecturecrud.repository.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private final UserRepository userRepository;
    private final MutableLiveData<List<User>> users;

    public UserViewModel(Application application) {
        super(application);
        userRepository = new UserRepository(application);
        users = new MutableLiveData<>(userRepository.getAllUsers());
    }

    public LiveData<List<User>> getUsers() {
        return users;
    }

    public void addUser(User user) {
        userRepository.addUser(user);
        users.setValue(userRepository.getAllUsers());
    }

    public void updateUser(User user) {
        userRepository.updateUser(user);
        users.setValue(userRepository.getAllUsers());
    }

    public void deleteUser(int userId) {
        userRepository.deleteUser(userId);
        users.setValue(userRepository.getAllUsers());
    }
}
