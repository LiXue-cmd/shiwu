package com.example.shiwu.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shiwu.Sqlite.MydatabaseHelper;
import com.example.shiwu.R;
import com.example.shiwu.Util.MyEditTextChangeListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginActivity extends AppCompatActivity {

    private static String name;
    private EditText manager_name;//用户名
    private EditText manager_pass;//密码
    private Button btn_login;//登录按钮
    private TextView forgetNum;//忘记密码
    private TextView registe;//注册
    private ImageView manager_login0,xianhsi;
    private MydatabaseHelper dbHelper;
    private int flag=1;
    private CheckBox jizhu;
    File file = null;
    public String mima;
    int q1=1;
   // public String name;
   private SharedPreferences sp;



    @SuppressLint("WorldReadableFiles")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);

        int themeColor = getSharedPreferences ("themeColor", MODE_PRIVATE).getInt ("themeColor", getResources ().getColor (R.color.white, null));
        getWindow ().setStatusBarColor (themeColor);

        initViews ();
        dbHelper = MydatabaseHelper.getInstance (this);


        registe.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (LoginActivity.this, Register.class);
                startActivity (intent);
            }
        });

        forgetNum.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (LoginActivity.this, wangjimima.class);
                startActivity (intent);
            }
        });

        sp = this.getSharedPreferences("userInfo", MODE_PRIVATE);
        if(sp.getBoolean("ISCHECK", false))
        {
            //设置默认是记录密码状态
            jizhu.setChecked(true);
            manager_name.setText(sp.getString("USER_NAME", ""));
            manager_pass.setText(sp.getString("PASSWORD", ""));
        }

        manager_pass.addTextChangedListener (new TextWatcher () {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String pass_info = manager_pass.getText ().toString ().trim ();
                if (!TextUtils.isEmpty (pass_info)) {
                    manager_login0.setImageResource (R.drawable.go);
                } else {

                    manager_login0.setImageResource (R.drawable.go_no);
                }
            }
        });

        xianhsi.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if (flag == 1) {
                    xianhsi.setImageResource (R.drawable.et_pwd_yes);
                    System.out.println ("无法显示");
                    manager_pass.setTransformationMethod (null);
                    flag = 0;
                    //  manager_pass.setSelection(manager_pass.getText().length());
                } else {
                    manager_pass.setTransformationMethod (new PasswordTransformationMethod ());
                    flag = 1;
                    xianhsi.setImageResource (R.drawable.et_pwd_no);
                }
            }
        });


//        jizhu.setOnCheckedChangeListener (new CompoundButton.OnCheckedChangeListener () {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//
//                FileOutputStream fos = null;
//                name = manager_name.getText ().toString ();
//                mima = manager_pass.getText ().toString ();
//                System.out.println (name);
//                System.out.println (mima);
//                try {
//                    q1=2;
//                    /*
//                     * getFilesDir()路径其实就是data/data/项目包/files 安卓中，每一个程序只能在自己的包下进行读写。
//                     * 例如，本例子中，其实路径就是 data/data/com.examle.mylogin/files/info.txt
//                     * 这里补充一点，如果文件要写在sd卡上，那么路径为storage/sdcard/info.txt,注意，写在sd卡是要添加读写权限的。
//                     * 当然咯，路径不用自己写，可以用api获取，Environment.getExternalStorageDirectory()
//                     * android.permission.READ_EXTERNAL_STORAGE，android.permission.WRITE_EXTERNAL_STORAGE
//                     */
//                    file = new File (getFilesDir (), "info.txt");
//                    try {
//                        fos = new FileOutputStream (file);
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace ();
//                    }
//                    // 将name和pwd转化为字节数组写入。##是为了方便待会分割
//                    try {
//                        fos.write ((name + "##" + mima).getBytes ());
//                    } catch (IOException e) {
//                        e.printStackTrace ();
//                    }
//
//
//                } finally {
//                    if (fos != null) {
//                        try {
//                            fos.close ();
//                        } catch (IOException e) {
//                            e.printStackTrace ();
//                        }
//                    }
//                }
//
//            }
//        });
        jizhu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener () {
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (jizhu.isChecked()) {

                    System.out.println("记住密码已选中");
                    sp.edit().putBoolean("ISCHECK", true).commit();

                }else {

                    System.out.println("记住密码没有选中");
                    sp.edit().putBoolean("ISCHECK", false).commit();

                }

            }
        });
//        manager_name.addTextChangedListener (new MyEditTextChangeListener (){
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                String sss = s.toString();
//                Log.d("值",sss);
//               // load ();
//            }
//
//        });

        manager_login0.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                manager_login0.setImageResource (R.drawable.go_yes);
                String name_info=manager_name.getText().toString().trim();
                String pass_info=manager_pass.getText().toString().trim();
                SQLiteDatabase db=dbHelper.getReadableDatabase();

                //通过输入的用户名查询数据库中的密码
                Cursor cursor=db.rawQuery("select password from manager where name=?",new String[]{name_info});
                String pi=null;
                if (cursor.moveToNext()){
                    //获取密码
                    pi=cursor.getString(cursor.getColumnIndex("password"));
                    System.out.println (pi);
                }
                //密码正确跳转到管理员界面
                if (pass_info.equals(pi)){
                    System.out.println ("88888888888888888888");
                    // System.out.println (name);
                    if(jizhu.isChecked())
                    {
                        //记住用户名、密码、
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("USER_NAME", name_info);
                        editor.putString("PASSWORD",pass_info);
                        editor.commit();
                    }
                    Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish ();
                    cursor.close();
                }else {
                    Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
    public  void initViews(){
        manager_name = findViewById(R.id.manager_login_name_input);
        manager_pass = findViewById(R.id.manager_login_pass_input);
        forgetNum = findViewById(R.id.manager_login_activity_forgetNum);
        registe = findViewById(R.id.manager_login_activity_register);
        manager_login0=findViewById (R.id.manager_login0);
        xianhsi=findViewById (R.id.iv_et_pwd_see);
        jizhu=findViewById (R.id.jizhu);
        Intent intent=getIntent();
        String username=intent.getStringExtra("username");
        manager_name.setText(username);


    }

    public void login(View v){
//        manager_login0.setImageResource (R.drawable.go_yes);
//        String name_info=manager_name.getText().toString().trim();
//        String pass_info=manager_pass.getText().toString().trim();
//        SQLiteDatabase db=dbHelper.getReadableDatabase();
//
//        //通过输入的用户名查询数据库中的密码
//        Cursor cursor=db.rawQuery("select password from manager where name=?",new String[]{name_info});
//        String pi=null;
//        if (cursor.moveToNext()){
//            //获取密码
//            pi=cursor.getString(cursor.getColumnIndex("password"));
//            System.out.println (pi);
//        }
//        //密码正确跳转到管理员界面
//        if (pass_info.equals(pi)){
//
//            System.out.println ("88888888888888888888");
//           // System.out.println (name);
//            Intent intent=new Intent(this, MainActivity.class);
//            startActivity(intent);
//            finish ();
//            cursor.close();
//        }else {
//            Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
//
//        }
    }


    public void jizhu(){

        FileOutputStream fos = null;
        if(jizhu.isChecked ()){
            name =manager_name.getText ().toString ();
            mima = manager_pass.getText ().toString ();
            System.out.println (name);
            System.out.println (mima);
            try {
                /*
                 * getFilesDir()路径其实就是data/data/项目包/files 安卓中，每一个程序只能在自己的包下进行读写。
                 * 例如，本例子中，其实路径就是 data/data/com.examle.mylogin/files/info.txt
                 * 这里补充一点，如果文件要写在sd卡上，那么路径为storage/sdcard/info.txt,注意，写在sd卡是要添加读写权限的。
                 * 当然咯，路径不用自己写，可以用api获取，Environment.getExternalStorageDirectory()
                 * android.permission.READ_EXTERNAL_STORAGE，android.permission.WRITE_EXTERNAL_STORAGE
                 */
                file = new File (getFilesDir(), "info.txt");
                try {
                    fos = new FileOutputStream (file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace ();
                }
                // 将name和pwd转化为字节数组写入。##是为了方便待会分割
                try {
                    fos.write((name + "##" + mima).getBytes());
                } catch (IOException e) {
                    e.printStackTrace ();
                }


            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace ();
                    }
                }
            }

        }
        else{
            System.out.println ("shiiiiiiiiiiiiiiiiiiii");
        }
    }
//    public void load(){
////        if (q1==2) {
////            q1=1;
//            FileInputStream fiStream = null;
//            BufferedReader br = null;
//            file = new File(getFilesDir(), "info.txt");
//            if (file.exists()) {
//                try {
//                    fiStream = new FileInputStream(file);
//                    /* 将字节流转化为字符流，转化是因为我们知道info.txt
//                     * 只有一行数据，为了使用readLine()方法，所以我们这里
//                     * 转化为字符流，其实用字节流也是可以做的。但比较麻烦
//                     */
//                    br = new BufferedReader(new InputStreamReader (fiStream));
//                    //读取info.txt
//                    String str = null;
//                    try {
//                        str = br.readLine();
//                    } catch (IOException e) {
//                        e.printStackTrace ();
//                    }
//                    //分割info.txt里面的内容。这就是为什么写入的时候要加入##的原因
//                    String arr[] = str.split("##");
//                    //      manager_name.setText(arr[0]);
//                    manager_pass.setText(arr[1]);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } finally {
//                    if (br != null) {
//                        try {
//                            br.close();
//                        } catch (IOException e) {
//                            e.printStackTrace ();
//                        }
//                    }
//                }
//            } else {
//
//            }
////        }
//
//
//    }
    public static String getname(){

        return name;
    }

}
