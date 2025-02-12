package com.demo.blackbox.mvvmarchitecturecrud.repository;

import android.content.Context;

import com.demo.blackbox.mvvmarchitecturecrud.database.EmployeeDao;
import com.demo.blackbox.mvvmarchitecturecrud.model.Employee;

import java.util.List;

public class EmployeeRepository {

    private EmployeeDao employeeDao;

    public EmployeeRepository(Context context) {
        employeeDao = new EmployeeDao(context);
    }

    public void insert(Employee employee) {
        employeeDao.insertEmployee(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    public void update(Employee employee) {
        employeeDao.updateEmployee(employee);
    }

    public void delete(int employeeId) {
        employeeDao.deleteEmployee(employeeId);
    }
}
