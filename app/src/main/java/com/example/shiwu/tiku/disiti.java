package com.example.shiwu.tiku;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.shiwu.R;

public class disiti  extends Fragment {

    private CheckBox a,b,c,d;
    public static int fenshu=0;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.disiti, container, false);
init (view);
        return view;
    }
    public void init(View view){

        a=view.findViewById (R.id.A);
        b=view.findViewById (R.id.B);
        c=view.findViewById (R.id.C);
        d=view.findViewById (R.id.D);

        a.setOnCheckedChangeListener (new CompoundButton.OnCheckedChangeListener () {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                System.out.println ("-------------------------");
                fenshu=20;
            }
        });
        b.setOnCheckedChangeListener (new CompoundButton.OnCheckedChangeListener () {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                System.out.println ("-------------------------");
            }
        });
        c.setOnCheckedChangeListener (new CompoundButton.OnCheckedChangeListener () {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                System.out.println ("-------------------------");
            }
        });
        d.setOnCheckedChangeListener (new CompoundButton.OnCheckedChangeListener () {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                System.out.println ("-------------------------");
            }
        });
    }
    public static int chengji(){
        return fenshu;
    }
}