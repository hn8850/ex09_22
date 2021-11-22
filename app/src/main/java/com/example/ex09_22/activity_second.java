package com.example.ex09_22;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class activity_second extends AppCompatActivity implements AdapterView.OnItemLongClickListener, View.OnCreateContextMenuListener {
    TextView info;
    double x1, d;
    Intent gi;
    boolean type;
    ListView lv;
    String[] series = new String[20];
    double[] nums = new double[20];
    double[] sums = new double[20];
    String oper;
    int pos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        info = findViewById(R.id.info);
        lv = findViewById(R.id.series);



        gi = getIntent();
        x1 = gi.getDoubleExtra("a1", -42442);
        d = gi.getDoubleExtra("d", -17.5);
        type = gi.getBooleanExtra("type", false);


        nums[0] = x1;
        series[0] = String.valueOf(x1);
        sums[0] = x1;
        for (int i = 1; i < 20; i++) {
            if (!type) {
                nums[i] = nums[i - 1] + d;
            } else {
                nums[i] = nums[i - 1] * d;
            }
            series[i] = String.valueOf(nums[i]);
            sums[i] = sums[i-1] + nums[i];
        }



        ArrayAdapter<String> adp = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, series);
        lv.setAdapter(adp);
        lv.setOnItemLongClickListener(this);
        lv.setOnCreateContextMenuListener(this);
    }

    public void back(View view) {
        gi.putExtra("first", x1);
        gi.putExtra("jump", d);
        gi.putExtra("type", type);
        setResult(RESULT_OK, gi);
        finish();
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        pos = position+1;
        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo){
        menu.add("Sum");
        menu.add("Position");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        oper=item.getTitle().toString();
        if (oper.equals("Sum")) info.setText("The sum is " + sums[pos-1]);
        else info.setText("The position is " + pos);
        return super.onContextItemSelected(item);
    }



}