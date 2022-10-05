package com.example.firestoretest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn_add, btn_add_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_add=(Button)findViewById(R.id.btn_add);
        btn_add_fragment=findViewById(R.id.btn_add_fragment);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,CreatePetActivity.class));
            }
        });

        btn_add_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreatePetFragment fm = new CreatePetFragment();
                fm.show(getSupportFragmentManager(),"Navegar a fragment");
            }
        });

    }
}