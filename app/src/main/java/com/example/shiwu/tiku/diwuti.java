package com.example.shiwu.tiku;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shiwu.Activity.LoginActivity;
import com.example.shiwu.Activity.dati;
import com.example.shiwu.Activity.paihangbang;
import com.example.shiwu.Activity.weikaifa;
import com.example.shiwu.Activity.xinwne;
import com.example.shiwu.R;

public class diwuti  extends Fragment {

    private EditText shuru;
    private static int fenshu=0;
    private Button tijiao;
    String a;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_diwuti, container, false);


        shuru=view.findViewById (R.id.tiankong);
        tijiao=view.findViewById (R.id.tijiao);
        tijiao.setOnClickListener(new View.OnClickListener ()
        {
            @Override
            public void onClick(View v)
            {
                ///
                a="123456";
                if (a.equals(shuru.getText ().toString ())){
                    fenshu=20;
                }
                else {

                }
            }
        });
        return view;
    }
    public static int chengji(){
        return fenshu;
    }




}