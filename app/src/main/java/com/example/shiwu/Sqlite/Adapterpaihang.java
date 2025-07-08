package com.example.shiwu.Sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.shiwu.R;

import java.util.List;

public class Adapterpaihang extends ArrayAdapter {

    private int count;

    public Adapterpaihang(@NonNull Context context, int resource, List<chengji> list) {
        super(context, resource,list);
        count=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        chengji sp= (chengji) getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(count,null);
        TextView tv1,tv2,tv3,tv4,tv5;
        tv1=view.findViewById(R.id.tv_id);
        tv2=view.findViewById(R.id.tv_name);
        tv3=view.findViewById(R.id.tv_shop);
       // tv4=view.findViewById(R.id.tv_privc);
       // tv5=view.findViewById(R.id.tv_num);
        tv1.setText(sp.getId());
        tv2.setText(sp.getName());
        tv3.setText(sp.getShop());
      //  tv4.setText(sp.getPrice());
      //  tv5.setText(sp.getNum());

        return view;
    }
}
