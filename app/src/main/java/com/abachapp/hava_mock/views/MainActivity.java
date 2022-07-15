package com.abachapp.hava_mock.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.abachapp.hava_mock.R;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText keyword;
    String word;
    SwitchMaterial switchMaterial;
    boolean checked;
    String rdgrp1,rdgrp2;
    RadioGroup radioGroup1,radioGroup2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.search);
        keyword=findViewById(R.id.keyword);
        switchMaterial=findViewById(R.id.cancelled);
        radioGroup1=findViewById(R.id.radioGroup);
        radioGroup2=findViewById(R.id.radioGroup2);
        rdgrp1="1";
        rdgrp2="1";
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getdata())
                {
                    Intent intent=new Intent(getApplicationContext(),Result.class);
                    Log.d("grp1",rdgrp1);
                    intent.putExtra("word",word);
                    intent.putExtra("checked",checked);
                    intent.putExtra("grp1",rdgrp1);
                    intent.putExtra("grp2",rdgrp2);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean getdata()
    {
        word=keyword.getText().toString();
        if(word.isEmpty()){
            word="1";
        }

        if(switchMaterial.isChecked())
        {
            checked=true;
        }
        else {
            checked=false;
        }

        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radio_button_1:
                        rdgrp1="1";
                        break;

                    case R.id.radio_button_2:
                        rdgrp1="2";
                        break;

                    case R.id.radio_button_3:
                        rdgrp1="3";
                        break;

                    case R.id.radio_button_4:
                        rdgrp1="4";
                        break;

                    case R.id.radio_button_5:
                        rdgrp1="5";
                        break;
                }
            }
        });

        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radio1_button_1:
                        rdgrp2="1";
                        break;

                    case R.id.radio1_button_2:
                        rdgrp2="2";
                        break;

                    case R.id.radio1_button_3:
                        rdgrp2="3";
                        break;

                    case R.id.radio1_button_4:
                        rdgrp2="4";
                        break;

                    case R.id.radio1_button_5:
                        rdgrp2="5";
                        break;
                }
            }
        });
        return true;
    }
}