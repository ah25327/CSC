package com.example.andre.ieeeconverter;
//Author: ah25327
//Class: CSC 205 (003N) with Professor Tanes
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Space;
import android.widget.TextView;

import com.example.andre.ieeconverter.R;

import csc.ahan.baseconverter.Converter;
public class MainActivity extends AppCompatActivity {
    Converter converter = new Converter();
    Button button1;
    Button button2;
    Button button3;
    EditText editT;
    TextView textV;
    Space space;

    //Creates the components and add attributes/methods for each component
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button)findViewById(R.id.button);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        editT = (EditText)findViewById(R.id.editText);
        textV = (TextView)findViewById(R.id.textView);
        space = (Space)findViewById(R.id.space);

        space.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(
                        INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        });
        textV.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                InputMethodManager imm = (InputMethodManager) getSystemService(
                        INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        });

        button1.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view) {
                        try{
                        String result = Converter.decimalTo7554(editT.getText().toString(), true);
                            textV.setTextSize(20f);
                        textV.setText(result);
                        }catch (NumberFormatException nfe){
                            textV.setText("Malformed Number");
                        }
                    }
                }
        );
        button2.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view) {
                        try{
                        String result = Converter.decimalTo7554(editT.getText().toString(), false);
                            textV.setTextSize(10f);
                            textV.setText(result);
                        }catch (NumberFormatException nfe){
                            textV.setText("Malformed Number");
                        }
                    }
                }
        );
        button3.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view) {
                        try{
                        String result = Converter.parseValueToDecimal(editT.getText().toString());
                            textV.setTextSize(20f);
                        textV.setText(result);
                        }catch (NumberFormatException nfe) {
                            textV.setText("Malformed Number");
                        }
                    }
                }
        );
    }



}
