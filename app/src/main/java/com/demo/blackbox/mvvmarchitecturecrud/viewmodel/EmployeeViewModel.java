package com.demo.blackbox.mvvmarchitecturecrud.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.demo.blackbox.mvvmarchitecturecrud.model.Employee;
import com.demo.blackbox.mvvmarchitecturecrud.repository.EmployeeRepository;

import java.util.List;

public class EmployeeViewModel extends AndroidViewModel {

    private EmployeeRepository employeeRepository;
    private MutableLiveData<List<Employee>> allEmployees;

    public EmployeeViewModel(@NonNull Application application) {
        super(application);
        employeeRepository = new EmployeeRepository(application);
        allEmployees = new MutableLiveData<>();
        loadEmployees();
    }

    private void loadEmployees() {
        allEmployees.setValue(employeeRepository.getAllEmployees());
    }

    public void insert(Employee employee) {
        employeeRepository.insert(employee);
        loadEmployees();
    }

    public LiveData<List<Employee>> getAllEmployees() {
        return allEmployees;
    }

    public void update(Employee employee) {
        employeeRepository.update(employee);
        loadEmployees();
    }

    public void delete(int employeeId) {
        employeeRepository.delete(employeeId);
        loadEmployees();
    }
}