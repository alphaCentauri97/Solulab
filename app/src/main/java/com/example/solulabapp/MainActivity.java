package com.example.solulabapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.Toast;

import com.example.solulabapp.Model.ApiModel;
import com.example.solulabapp.Model.Products;
import com.example.solulabapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Context context;
    ActivityMainBinding binding;
    RecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(!isConnected())
        {
            Toast.makeText(this, "No Internet Access", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Welcome to our App", Toast.LENGTH_SHORT).show();
        }

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ApiUtilities.getApiInterface().getProducts().enqueue(new Callback<ApiModel>() {
            @Override
            public void onResponse(Call<ApiModel> call, Response<ApiModel> response) {
                if (response.isSuccessful()) {
                    ApiModel apiModel = response.body();
                    List<Products> productsList = apiModel.getProducts();
                    adapter = new RecyclerViewAdapter(MainActivity.this,productsList);
                    binding.recyclerView.setAdapter(adapter);
                    binding.recyclerView.setHasFixedSize(true);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private boolean isConnected()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}