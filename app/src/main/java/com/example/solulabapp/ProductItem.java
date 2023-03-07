package com.example.solulabapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.solulabapp.databinding.ActivityProductItemBinding;

import java.util.ArrayList;
import java.util.List;

public class ProductItem extends AppCompatActivity {

    ActivityProductItemBinding binding;
    int quantity = 1,realPrice,price = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        List<String>productItemPic = intent.getStringArrayListExtra("list");
        List<SlideModel> item = new ArrayList<>();
        for (String link: productItemPic) {
            item.add(new SlideModel(link,ScaleTypes.FIT));
        }
        binding.imageSlider.setImageList(item,ScaleTypes.FIT);

        String desc = intent.getStringExtra("desc");
        binding.tvDesc.setText(desc);

        realPrice =  intent.getIntExtra("price",0);
        binding.tvprice.setText("$"+realPrice);
        price = realPrice;

        String title = intent.getStringExtra("title");
        binding.title.setText(title);

        double rate = intent.getDoubleExtra("rate",0);
        binding.tvrating.setText(String.format("%.1f",rate));
        binding.quantity.setText(1+"");

        binding.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.quantity.setText(++quantity+"");
                price += realPrice;
                binding.tvprice.setText("$"+price+"");
            }
        });
        binding.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantity>1)
                {
                    binding.quantity.setText(--quantity+"");
                    price -= realPrice;
                    binding.tvprice.setText("$"+price+"");
                }
            }
        });

    }
}