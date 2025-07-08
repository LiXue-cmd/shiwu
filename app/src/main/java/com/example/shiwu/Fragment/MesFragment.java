package com.example.shiwu.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shiwu.Activity.Aishitu;
import com.example.shiwu.Activity.Tianqi;
import com.example.shiwu.R;

import java.io.File;

public class MesFragment extends Fragment {

    private File photoFile = null;
    private Button shibie,tianqi;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mes, container, false);
        
        
        shibie=view.findViewById (R.id.paizhao);
        tianqi=view.findViewById (R.id.tianqi);
        shibie.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                startActivity(new Intent (getActivity(), Aishitu.class));
            }
         
        });
        tianqi.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                startActivity(new Intent (getActivity(), Tianqi.class));
            }

        });

        return view;
    }

}
