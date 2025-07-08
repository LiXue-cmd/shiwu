package com.example.shiwu.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shiwu.Activity.fenlei1;
import com.example.shiwu.Activity.fenlei2;
import com.example.shiwu.Activity.fenlei3;
import com.example.shiwu.Activity.fenlei4;
import com.example.shiwu.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KindFragment extends Fragment implements View.OnClickListener {

    private ImageView a1,a2,a3,a4;

    @Nullable


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kind, container, false);



       init(view);

        return view;
    }
    public void init(View view){

        a4=view.findViewById (R.id.youhailaji);
        a1=view.findViewById (R.id.chuyu);
        a2=view.findViewById (R.id.kehuishou);
        a3=view.findViewById (R.id.qitalaji);

        a1.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent1 =new Intent (getActivity (), fenlei1.class);
                startActivity (intent1);
            }
        });
        a2.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent2 =new Intent (getActivity (), fenlei2.class);
                startActivity (intent2);
            }
        });
        a3.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent3 =new Intent (getActivity (), fenlei3.class);
                startActivity (intent3);
            }
        });
        a4.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent4 =new Intent (getActivity (), fenlei4.class);
                startActivity (intent4);
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}
