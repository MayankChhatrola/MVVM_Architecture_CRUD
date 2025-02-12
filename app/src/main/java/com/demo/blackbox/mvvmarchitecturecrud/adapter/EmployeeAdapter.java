package com.demo.blackbox.mvvmarchitecturecrud.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.blackbox.mvvmarchitecturecrud.view.EditEmployeeActivity;
import com.demo.blackbox.mvvmarchitecturecrud.R;
import com.demo.blackbox.mvvmarchitecturecrud.model.Employee;
import com.demo.blackbox.mvvmarchitecturecrud.viewmodel.EmployeeViewModel;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private Context context;
    private List<Employee> employeeList;
    private EmployeeViewModel employeeViewModel;

    public EmployeeAdapter(Context context) {
        this.context = context;
    }

    public void setEmployees(List<Employee> employees) {
        this.employeeList = employees;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_employee, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        Employee employee = employeeList.get(position);
        holder.employeeNameText.setText(employee.getName());
        holder.employeePositionText.setText(employee.getPosition());

        holder.editButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditEmployeeActivity.class);
            intent.putExtra("employeeId", employee.getId());
            intent.putExtra("employeeName", employee.getName());
            intent.putExtra("employeePosition", employee.getPosition());
            context.startActivity(intent);
        });

        holder.deleteButton.setOnClickListener(v -> {
            employeeViewModel = new EmployeeViewModel(((android.app.Activity) context).getApplication());
            employeeViewModel.delete(employee.getId());
        });
    }

    @Override
    public int getItemCount() {
        return employeeList == null ? 0 : employeeList.size();
    }

    static class EmployeeViewHolder extends RecyclerView.ViewHolder {
        TextView employeeNameText, employeePositionText;
        Button editButton, deleteButton;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            employeeNameText = itemView.findViewById(R.id.employeeNameText);
            employeePositionText = itemView.findViewById(R.id.employeePositionText);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}