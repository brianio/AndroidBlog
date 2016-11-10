package com.brianio.myappportfolio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnMovies,btnHawk,btnBigger,btnMaterial,btnGo,btnCapstone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void init(){
        btnMovies=(Button)findViewById(R.id.btn_movies);
        btnHawk=(Button)findViewById(R.id.btn_hawk);
        btnBigger=(Button)findViewById(R.id.btn_bigger);
        btnMaterial=(Button)findViewById(R.id.btn_material);
        btnGo=(Button)findViewById(R.id.btn_go);
        btnCapstone=(Button)findViewById(R.id.btn_capstone);

        btnMovies.setOnClickListener(this);
        btnHawk.setOnClickListener(this);
        btnBigger.setOnClickListener(this);
        btnMaterial.setOnClickListener(this);
        btnGo.setOnClickListener(this);
        btnCapstone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_movies:
                Toast.makeText(this,"This button will launch my Popular Movies app",
                        Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_hawk:
                Toast.makeText(this,"This button will launch my Stock Hawk app",
                        Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_bigger:
                Toast.makeText(this,"This button will launch my Build It Bigger app",
                        Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_material:
                Toast.makeText(this,"This button will launch my Make Your App Material app",
                        Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_go:
                Toast.makeText(this,"This button will launch my popular Go Ubiquitous app",
                        Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_capstone:
                Toast.makeText(this,"This button will launch my CapStone app",
                        Toast.LENGTH_LONG).show();
                break;
        }
    }
}
