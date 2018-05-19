package com.birjuvachhani.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.birjuvachhani.retrofitdemo.api.ApiResponse;
import com.birjuvachhani.retrofitdemo.api.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvUserList;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvUserList = findViewById(R.id.rvUserList);
        progressBar = findViewById(R.id.progressBar);

        final UserAdapter adapter = new UserAdapter();
        rvUserList.setLayoutManager(new LinearLayoutManager(this));
        rvUserList.setAdapter(adapter);

        RetrofitInterface client = RetrofitClient.getClient().create(RetrofitInterface.class);
        client.fetchUsers(10).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                List<Result> mlist = response.body().getResults();
                progressBar.setVisibility(View.INVISIBLE);
                adapter.setData(mlist);
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e("Main", t.toString());
            }
        });
    }
}

