package com.geelaro.viewtrianex.viewact;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

import com.geelaro.viewtrianex.R;

/**
 * Created by Administrator on 2016/11/10.
 */

public class AutoTextViewActivity extends AppCompatActivity {
    private AutoCompleteTextView AutoTeView;
    private MultiAutoCompleteTextView MulAutoTeView;
    private ArrayAdapter<String> adapter=null;
    private ArrayAdapter<String> madapter=null;

    private final static String[] data=new String[]{"小猪猪","小猫猫","小狗狗","小蛇蛇","小鸡鸡"};

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autotextview);

        Intent tvIntent=getIntent();

        AutoTeView=(AutoCompleteTextView) findViewById(R.id.atcompt_tv);
        MulAutoTeView=(MultiAutoCompleteTextView)findViewById(R.id.mtatcompt_tv);

        adapter=new ArrayAdapter<>(AutoTextViewActivity.this,android.R.layout.simple_dropdown_item_1line,data);
        AutoTeView.setAdapter(adapter);

        madapter=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_dropdown_item_1line,data);
        MulAutoTeView.setAdapter(madapter);
        MulAutoTeView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }
}
