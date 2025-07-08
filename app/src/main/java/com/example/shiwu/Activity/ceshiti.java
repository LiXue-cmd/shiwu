package com.example.shiwu.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shiwu.Fragment.HomeFragment;
import com.example.shiwu.R;
import com.example.shiwu.tiku.danxuanti;
import com.example.shiwu.tiku.danxuanti1;
import com.example.shiwu.tiku.disanti;
import com.example.shiwu.tiku.disiti;
import com.example.shiwu.tiku.diwuti;

public class ceshiti extends AppCompatActivity {

    private TextView textView;
    private Button button01;
    private Button shangyiti;
    private Button xiayiti,tijiao;
    private Handler hdl;
    private int i = 0;
    private boolean open = false;
    private int m=0;
    private int h=0;
    private Log log;
    private static  int flag=1;
    public int q1=1;
   public int fenshu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_ceshiti);

        replaceFragment();

        log.e("asd","creat调用");
        textView=findViewById(R.id.text05);
        button01=findViewById(R.id.start);
        shangyiti=findViewById (R.id.shangyiti);
        xiayiti=findViewById (R.id.xiayiti);
        tijiao=findViewById (R.id.tijiao);

        if (savedInstanceState != null)
        {
            i = savedInstanceState.getInt("I");
            m = savedInstanceState.getInt("M");
            h = savedInstanceState.getInt("H");
            button01.post(new Runnable() {
                @Override
                public void run() {
                    button01.performClick();
                }
            });
        }

        hdl = new Handler();



            //    通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
            AlertDialog.Builder builder = new AlertDialog.Builder(ceshiti.this);
            //    设置Title的图标
            // builder.setIcon(R.drawable.ic_launcher);
            //    设置Title的内容
            builder.setTitle("考试提醒");
            //    设置Content来显示一个信息
            builder.setMessage("你将有五分钟的时间完成一下五个题");
            //    设置一个PositiveButton
            builder.setPositiveButton("开始", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    Toast.makeText(ceshiti.this, "positive: " + which, Toast.LENGTH_SHORT).show();
                    q1=2;
                    open = !open;
                    hdl.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            if(open)
                            {
                                if(ceshiti.this.i >60)
                                {
                                    ceshiti.this.i =0;
                                    ceshiti.this.m++;
                                }
                                if(ceshiti.this.m >60)
                                {
                                    ceshiti.this.m =0;
                                    ceshiti.this.h++;
                                }
                                textView.setText(showTime(ceshiti.this.h)+":"+showTime(ceshiti.this.m)+":"+showTime(ceshiti.this.i));
                                ceshiti.this.i++;
                                hdl.postDelayed(this,100);
                            }
                        }
                    });
                }
            });
            //    设置一个NegativeButton
            builder.setNegativeButton("返回", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    Intent intent = new Intent (ceshiti.this,MainActivity.class);
                    startActivity (intent);
                }
            });
            //    显示出该对话框
            builder.show();

            if (q1 == 2){
                open = !open;
                hdl.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        if(open)
                        {
                            if(ceshiti.this.i >60)
                            {
                                ceshiti.this.i =0;
                                ceshiti.this.m++;
                            }
                            if(ceshiti.this.m >60)
                            {
                                ceshiti.this.m =0;
                                ceshiti.this.h++;
                            }
                            textView.setText(showTime(ceshiti.this.h)+":"+showTime(ceshiti.this.m)+":"+showTime(ceshiti.this.i));
                            ceshiti.this.i++;
                            hdl.postDelayed(this,100);
                        }
                    }
                });

            }
//        button01.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {
//                open = !open;
//                hdl.post(new Runnable()
//                {
//                    @Override
//                    public void run()
//                    {
//                        if(open)
//                        {
//                            if(ceshiti.this.i >60)
//                            {
//                                ceshiti.this.i =0;
//                                ceshiti.this.m++;
//                            }
//                            if(ceshiti.this.m >60)
//                            {
//                                ceshiti.this.m =0;
//                                ceshiti.this.h++;
//                            }
//                            textView.setText(showTime(ceshiti.this.h)+":"+showTime(ceshiti.this.m)+":"+showTime(ceshiti.this.i));
//                            ceshiti.this.i++;
//                            hdl.postDelayed(this,100);
//                        }
//                    }
//                });
//            }
//        });

        tijiao.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                ceshiti.this.m =0;
                ceshiti.this.i =0;
                ceshiti.this.h =0;
                textView.setText(showTime(ceshiti.this.h)+":"+showTime(ceshiti.this.m)+":"+showTime(ceshiti.this.i));
                open = false;

                int b1=danxuanti.chengji ();
                int b2=danxuanti1.chengji ();
                int b3=disanti.chengji ();
                int b4=disiti.chengji ();
                int b5=diwuti.chengji ();
                fenshu=b5+b1+b2+b3+b4;
                //    通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                AlertDialog.Builder builder = new AlertDialog.Builder(ceshiti.this);
                //    设置Title的图标
                // builder.setIcon(R.drawable.ic_launcher);
                //    设置Title的内容
                builder.setTitle("成绩显示");
                //    设置Content来显示一个信息
                builder.setMessage("恭喜你得了"+fenshu+"分");
                //    设置一个PositiveButton
                builder.setPositiveButton("查看排行榜", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(ceshiti.this, "positive: " + which, Toast.LENGTH_SHORT).show();
                        //  startActivity(new Intent (getActivity(), paihangbang.class));
                        Intent intent = new Intent (ceshiti.this, paihangbang.class);
                        Bundle bundle = new Bundle ();
                        String qq= LoginActivity.getname ();
                        bundle.putString ("fenshu", String.valueOf (fenshu));

                        bundle.putString ("xiayici",qq);
                        //  bundle.putString ("zhengwen",text1);
                        intent.putExtras (bundle);
                        ceshiti.this.startActivity(intent);

                    }
                });
                //    设置一个NegativeButton
                builder.setNegativeButton("返回", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(ceshiti.this, "negative: " + which, Toast.LENGTH_SHORT).show();
                    }
                });
                //    显示出该对话框
                builder.show();
            }
        });

        shangyiti.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if (flag==1) {
                    shangyiti.setEnabled (false);
                    System.out.println ("第一题，不能点击");
                }
                else {
                    xiayiti.setEnabled (true);
                    flag--;
                    replaceFragment ();
                    System.out.println (flag);
                }
            }
        });

        xiayiti.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if (flag==5) {
                    xiayiti.setEnabled (false);
                }
                else {
                    shangyiti.setEnabled (true);
                    flag++;
                    replaceFragment ();
                    System.out.println (flag);
                }
            }
        });

    }
    ////////切换页面
    public void replaceFragment() {
        Fragment fragment = null;
        switch (flag){
            case 1:
                fragment =new danxuanti ();
                System.out.println (flag);
                break;
            case 2:
                fragment =new danxuanti1 ();
                System.out.println (flag);
                break;
            case 3:
                fragment = new disanti ();
                System.out.println (flag);
                break;
            case 4:
                fragment = new disiti ();
                System.out.println (flag);
                break;
            case 5:
                fragment = new diwuti ();
                System.out.println (flag);
                break;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_empty, fragment);
        transaction.commit();
    }


    ////////////////////计时器
    public String showTime(int i){
        if(i<10)
        {
            return "0"+i;
        }
        else
        {
            return String.valueOf(i);
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("I", i);
        outState.putInt("M", m);
        outState.putInt("H", h);
    }

    @Override
    protected void onStart() {
        super.onStart();
        log.e("asd","start调用");
    }
    @Override
    protected void onResume() {
        super.onResume();
        log.e("asd","resume调用");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        log.e("asd","restart调用");
    }
    @Override
    protected void onPause() {
        super.onPause();
        log.e("asd","pause调用");
    }
    @Override
    protected void onStop() {
        super.onStop();
        log.e("asd","stop调用");

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        log.e("asd","destroy调用");
    }
}