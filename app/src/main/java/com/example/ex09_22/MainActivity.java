package com.example.ex09_22;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView op1, op2;
    Button next;
    Switch sw;
    EditText first, jump;
    double a1, d;
    Intent si;
    boolean type; // if type == false, then the series type is Arithmetic, and if true, then Geometric
    boolean cont2 = false;
    boolean cont1 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        op1 = findViewById(R.id.op1);
        op2 = findViewById(R.id.op2);
        next = findViewById(R.id.next);
        first = findViewById(R.id.a1);
        jump = findViewById(R.id.d);
        sw = findViewById(R.id.sw);


    }

    public void go(View view) {

        try {
            a1 = Double.parseDouble(first.getText().toString());
            cont1 = true;
        } catch (Exception e) {
            cont1 = false;
            Toast.makeText(getApplicationContext(), "Enter valid number!", Toast.LENGTH_SHORT).show();
        }

        try {
            d = Double.parseDouble(jump.getText().toString());
            cont2 = true;
        } catch (Exception e) {
            cont2 = false;
            Toast.makeText(getApplicationContext(), "Enter valid number!", Toast.LENGTH_SHORT).show();
        }

        if (!sw.isChecked()) {
            type = false;
        } else type = true;


        if (cont1 && cont2) {
            si = new Intent(this, activity_second.class);
            si.putExtra("a1", a1);
            si.putExtra("d", d);
            si.putExtra("type", type);
            startActivityForResult(si, 100);
        } else {
            Toast.makeText(getApplicationContext(), "Cannot go to next page without all variables", Toast.LENGTH_SHORT).show();

        }
    }


    @Override
    protected void onActivityResult(int source, int good, @Nullable Intent data_back) {
        super.onActivityResult(source, good, data_back);
        if (data_back != null) {
            a1 = data_back.getDoubleExtra("first", 29329);
            d = data_back.getDoubleExtra("jump", 4282);
            type = data_back.getBooleanExtra("type", false);
            first.setText(String.valueOf(a1), TextView.BufferType.EDITABLE);
            jump.setText(String.valueOf(d));
            sw.setChecked(type);
        }
    }
}