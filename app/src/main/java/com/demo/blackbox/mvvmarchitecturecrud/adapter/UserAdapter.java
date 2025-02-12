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

import com.demo.blackbox.mvvmarchitecturecrud.view.EditUser;
import com.demo.blackbox.mvvmarchitecturecrud.R;
import com.demo.blackbox.mvvmarchitecturecrud.model.User;
import com.demo.blackbox.mvvmarchitecturecrud.viewmodel.UserViewModel;

import java.util.List;

public class UserAdapter  extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{
    private Context context;
    private List<User> userList;
    private UserViewModel userViewModel;

    public UserAdapter(Context context) {
        this.context = context;
    }

    public void setUsers(List<User> users) {
        this.userList = users;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user_with_actions, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.userNameText.setText(user.getName());
        holder.userEmailText.setText(user.getEmail());

        holder.editButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditUser.class);
            intent.putExtra("userId", user.getId());
            intent.putExtra("userName", user.getName());
            intent.putExtra("userEmail", user.getEmail());
            context.startActivity(intent);
        });

        holder.deleteButton.setOnClickListener(v -> {
            userViewModel = new UserViewModel(((android.app.Activity) context).getApplication());
            userViewModel.deleteUser(user.getId());
        });
    }

    @Override
    public int getItemCount() {
        return userList == null ? 0 : userList.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView userNameText, userEmailText;
        Button editButton, deleteButton;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            userNameText = itemView.findViewById(R.id.userNameText);
            userEmailText = itemView.findViewById(R.id.userEmailText);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
