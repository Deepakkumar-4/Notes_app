package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.databinding.ActivityInsertDataBinding;

public class InsertDataActivity extends AppCompatActivity {
    ActivityInsertDataBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityInsertDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        String type = getIntent().getStringExtra("type");

        if(type.equals("update")){

            setTitle("Update");
            binding.title.setText(getIntent().getStringExtra("title"));
            binding.Note.setText(getIntent().getStringExtra("Text"));
            int id = getIntent().getIntExtra("id",0);
            binding.add.setText("Update");
            binding.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("title",binding.title.getText().toString());
                    intent.putExtra("disp",binding.Note.getText().toString());
                    intent.putExtra("id",id);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            });


        }
        else {
            setTitle("Add Note");
            binding.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("title",binding.title.getText().toString());
                    intent.putExtra("disp",binding.Note.getText().toString());
                    setResult(RESULT_OK,intent);
                    finish();
                }
            });
        }



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
    }
}