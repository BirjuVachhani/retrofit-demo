package com.birjuvachhani.retrofitdemo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.birjuvachhani.retrofitdemo.api.Result;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<Result> mlist = Collections.emptyList();

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.bind(mlist.get(position));
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public void setData(List<Result> mlist) {
        this.mlist = mlist;
        notifyDataSetChanged();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivAvatar;
        private TextView tvName;
        private TextView tvCity;

        public UserViewHolder(View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.ivAvatar);
            tvName = itemView.findViewById(R.id.tvFirstName);
            tvCity = itemView.findViewById(R.id.tvCity);
        }

        public void bind(Result result) {
            tvName.setText(result.getName().getFirst());
            tvCity.setText(result.getLocation().getCity());
            Picasso.get().load(result.getPicture().getMedium()).fit().into(ivAvatar);
        }
    }
}
