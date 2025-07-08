package com.example.shiwu.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shiwu.Activity.LoginActivity;
import com.example.shiwu.Activity.dati;
import com.example.shiwu.Activity.weikaifa;
import com.example.shiwu.R;
import com.example.shiwu.Util.CircleImageView;

import java.net.URI;

import static android.app.Activity.RESULT_OK;

public class MyFragment extends Fragment  {

    public ImageView l1,l2,l3;
    public static Uri uri;
    private CircleImageView tp;
    String name;
   // LoginActivity name1= new LoginActivity ();
    private TextView name_xianshi;
  //  public int flag=1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);


       init (view);
       tiaozhuan ();
       tp.setImageURI (uri);
        return view;
    }

    public void init(View view){

        l1=view.findViewById (R.id.l1);
        l2=view.findViewById (R.id.l2);
        l3=view.findViewById (R.id.l3);
        tp=view.findViewById (R.id.tp);
        tp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tz();
            }
        });
        name_xianshi = view.findViewById (R.id.name);
        name = LoginActivity.getname ();
        System.out.println ("999999999999999999999999999999");
        System.out.println (name);
        name_xianshi.setText (name);

    }
    private void tz() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent,100 );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode==RESULT_OK){
                   uri=data.getData ();
                   Uri uri1 = data.getData ();
                    tp.setImageURI (uri1);

            }
        }

    }
    public static Uri getdizi(){
        return uri;
    }

    public void tiaozhuan(){
        l1.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                startActivity(new Intent (getActivity(), weikaifa.class));
            }
        });
        l2.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                startActivity(new Intent (getActivity(), weikaifa.class));
            }
        });
        l3.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                startActivity(new Intent (getActivity(), weikaifa.class));
            }
        });

    }
}
