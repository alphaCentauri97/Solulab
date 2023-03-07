package com.example.solulabapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.solulabapp.Model.Products;
import com.example.solulabapp.databinding.ProductListItemBinding;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Context context;
    List<Products> list =new ArrayList<>();

    public RecyclerViewAdapter(Context context, List<Products> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.stock.setText(list.get(position).getStock()+" left");
        holder.binding.price.setText("$ "+list.get(position).getPrice());
        holder.binding.brand.setText("Brand: "+list.get(position).getBrand());
        holder.binding.title.setText(list.get(position).getTitle());
        holder.binding.category.setText(list.get(position).getCategory());
        Glide.with(context).load(list.get(position).getThumbnail()).into(holder.binding.itemImg);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ProductItem.class);
                intent.putStringArrayListExtra("list",(ArrayList<String>)list.get(position).getImages());
                intent.putExtra("desc",list.get(position).getDescription());
                intent.putExtra("title",list.get(position).getTitle());
                intent.putExtra("price",list.get(position).getPrice());
                intent.putExtra("rate",list.get(position).getRating());
                context.startActivity(intent);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ProductListItemBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ProductListItemBinding.bind(itemView);
        }
    }
}
