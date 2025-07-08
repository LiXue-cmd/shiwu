package com.example.shiwu.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.shiwu.R;

import java.util.ArrayList;
import java.util.Calendar;

public class gerenxinxi extends AppCompatActivity {

    private Spinner spYear;
    private Spinner spMonth;
    private Spinner spDay;
    private ArrayList<String> dataYear = new ArrayList<String>();
    private ArrayList<String> dataMonth = new ArrayList<String>();
    private ArrayList<String> dataDay = new ArrayList<String>();
    private ArrayAdapter<String> adapterSpYear;
    private ArrayAdapter<String> adapterSpMonth;
    private ArrayAdapter<String> adapterSpDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_gerenxinxi);
    spYear = (Spinner) findViewById(R.id.sp_year);
    spMonth = (Spinner) findViewById(R.id.sp_month);
    spDay = (Spinner) findViewById(R.id.sp_day);

    // 年份设定为当年的前后20年
    Calendar cal = Calendar.getInstance();
		for (int i = 0; i < 40; i++) {
        dataYear.add("" + (cal.get(Calendar.YEAR) - 20 + i));
    }
    adapterSpYear = new ArrayAdapter<String>(this, R.layout.spinner_item, dataYear);
		adapterSpYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spYear.setAdapter(adapterSpYear);
		spYear.setSelection(20);// 默认选中今年

    // 12个月
		for (int i = 1; i <= 12; i++) {
        dataMonth.add("" + (i < 10 ? "0" + i : i));
    }
    adapterSpMonth = new ArrayAdapter<String>(this, R.layout.spinner_item, dataMonth);
		adapterSpMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spMonth.setAdapter(adapterSpMonth);

    adapterSpDay = new ArrayAdapter<String>(this, R.layout.spinner_item, dataDay);
		adapterSpDay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spDay.setAdapter(adapterSpDay);

		spMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener () {

        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            dataDay.clear();
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, Integer.valueOf(spYear.getSelectedItem().toString()));
            cal.set(Calendar.MONTH, arg2);
            int dayofm = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            for (int i = 1; i <= dayofm; i++) {
                dataDay.add("" + (i < 10 ? "0" + i : i));
            }
            adapterSpDay.notifyDataSetChanged();
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {

        }
    });

}
}