package com.accidentaldeveloper.havetodothistoo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.accidentaldeveloper.havetodothistoo.databinding.ActivityDataInsertBinding;

public class DataInsertActivity extends AppCompatActivity {
ActivityDataInsertBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDataInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String type = getIntent().getStringExtra("type");
        if(type.equals("update")){
            setTitle("update");
            binding.Title.setText(getIntent().getStringExtra("Title"));
            binding.Disp.setText(getIntent().getStringExtra("Disp"));
            int id = getIntent().getIntExtra("id",0);
            binding.add.setText("Update note");
            binding.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("Title",binding.Title.getText().toString());
                    intent.putExtra("Disp",binding.Disp.getText().toString());
                    intent.putExtra("id",id);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            });
        }
        else {
            setTitle("Add Mode");
            binding.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("Title",binding.Title.getText().toString());
                    intent.putExtra("Disp",binding.Disp.getText().toString());
                    setResult(RESULT_OK,intent);
                    finish();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}