package com.example.shiwu.Fragment;

import com.example.shiwu.Activity.ceshiti;
import com.example.shiwu.Activity.gerenxinxi;
import com.example.shiwu.Activity.shipin;
import com.example.shiwu.Activity.xinwne;
import com.example.shiwu.R;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.shiwu.Activity.dati;
import com.example.shiwu.Util.CircleImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment implements View.OnClickListener {


    private ListView listView;
    @Nullable

    int tupian1[]={R.drawable.xin1,R.drawable.xin2,R.drawable.xin3,R.drawable.xin4,
            R.drawable.xin5,R.drawable.xin6,R.drawable.xin7,R.drawable.xin8};
    final String name[]={"大山深处垃圾分类,“扁担行动队”派上大用场",
            "市民应当按照四类垃圾的分类要求……",
            "济南教育部门将对学生开展生活垃圾减量与分类教育",
            "市民可通过“泉城济南垃圾分类”官方微信公众号了解分类知识和相……",
            "【昆明垃圾分类主题文艺作品征集】9岁小朋友用创意绘画讲述垃圾分类故事",
            "【网络文明】垃圾分类你会了吗？",
            "我分类 我承诺｜点滴积累，让垃圾分类融入日常生活",
            "垃圾分类新时尚"};
    String text[]={
            "北京市中山村地处房山区大安山乡深山，村里老人投放垃圾，需要下坡下崖，很容易发生危险。于是村里组织了“扁担行动队”。队员们，不仅是垃圾清运员，还是垃圾分类指导员和督导员。",
            "记者提问：请问《条例》主要有哪些特点或特色？将对市民日常生活产生怎样的影响？另外，请重点介绍一下居民在对生活垃圾进行分类和投放时，有哪些注意事项？『市人大常委会法制工作室副主任何修冬』：《条例》一是在制度层面上建立协同治理机制。明确各级人民政府及其部门各个层级的管理职责，重点建立生活垃圾分类管理责任人和督导员制度，完善村（居）民委员会、业主委员会、物业服务企业、业主等共同参与的管理机制。二是从流程环节上实行全程分类管理。《条例》从便于群众分类的角度设计前端管理环节，确保分类投放简便易行；从完善收运体系的层面加强中端收运管理，坚决杜绝混合收运；从优化处置工艺的环节提高后端处理能力和资源化利用水平，推动形成环环相扣、一体运行的全链条分类管理体系。三是在措施保障上提升分类管理水平。《条例》从提升社会治理现代化水平的高度，加强信息化平台建设，鼓励设置智能化收集容器，支持科技创新及研发应用，不断提高分类管理效能和工作实效。《条例》出台标志着我市生活垃圾减量与分类进入“有法可依”、“强制实施”阶段。市民长期以来形成的投放习惯将面临很大改变。希望广大市民朋友尽快学习分类知识，提高资源节约和分类投放意识，养成低碳文明的生活习惯。关于如何分类和投放问题，居民应当按照四类垃圾的分类要求，在家中分类袋装或桶装垃圾，在规定的时间、地点分类投放。可回收物可以直接售卖给再生资源回收人员或者投放到蓝色收集桶；有害垃圾量很少，可以在每月第一周的有害垃圾宣传收集周，集中投放至红色收集桶；厨余垃圾和其他垃圾产生量较多，应当按照时间、地点要求及时投放至绿色、灰色收集桶。",
            "记者提问：刚才提到了“生活垃圾减量与分类要从娃娃抓起”，教育部门将从哪些方面做好相关工作？『市教育局总督学宋豫』：我们主要采取以下措施，对学生开展生活垃圾减量与分类方面的教育。一是全面开展以生活垃圾分类与减量为主要内容的宣传教育。根据不同年龄段学生的认知水平和成长规律，编写了有关生活垃圾分类的教育读本，通过图文并茂的形式让学生了解有关生活垃圾的知识，学会如何进行生活垃圾分类。二是加强生活垃圾分类实践教育。采取做游戏、讲故事、知识竞赛、海报绘制等多种活动形式，利用升旗仪式、班会、校会等时间，通过板报、宣传橱窗、校园网站等宣传阵地，开展丰富多彩的生活垃圾减量、分类主题实践活动，帮助学生养成节约好习惯，成为变废为宝的小能手。三是合理设置学校垃圾投放容器。本着便利投放、便于实现垃圾分类实效的原则，综合考虑学校垃圾产量及产生频率，合理配置四类垃圾投放容器类别并进行组合，做到桶体有明显、规范的垃圾分类标识，保持垃圾投放容器整洁无破损。四是积极开展垃圾分类家校社互动活动。通过垃圾分类进社区、进家庭等多种形式，充分发挥小手拉大手的作用，将践行垃圾分类的理念，从班级扩展到校园，从校园影响到家庭，从家庭延伸到社会，提高了家长、社会参与垃圾分类的意识，实现了家校社联动践行垃圾分类的良好效果。",
            "记者提问：《条例》将于明年5月1日起实施，主管部门将如何做好宣传让获取分类知识和相关信息，确保垃圾分类相关标准和流程能够人人皆知？『市城市管理局党组成员、副局长黄爱民』：垃圾分类涉及千家万户，与群众日常生活密切相关。我们城管局将从七个方面入手，让广大市民知晓、学习和执行条例。一是着力抓好媒体宣传。利用传统媒体和网络新媒体相结合的方式，通过内容介绍、知识竞赛等形式，营造社会氛围。二是着力抓好入户宣传。组织各区县城管局（环卫管护中心、综合执法局）、各街镇环卫所、执法中队，通过进社区、进单位等，《条例》实施前实现入户宣传全覆盖，确保居民知晓率达95%以上。三是着力抓好宣讲宣传。成立市、区、街各级宣讲团，深耕法条宣传，提高生活垃圾分类法规的知晓率、普及率。四是着力抓好公益宣传。利用公共卫生间、垃圾中转站等环卫设施室外电子显示屏进行条例的宣传。五是着力抓好业务培训。分批对管理和执法人员进行专题培训，进一步提高服务管理水平。六是着力抓好网络宣传。目前“泉城济南垃圾分类”官方微信公众号已正式上线，微信小程序也即将上线，市民朋友可以通过公众号了解分类知识和相关动态，通过小程序对垃圾类别和投放要求进行查询。另外，还可以通过“我爱泉城”APP了解相关知识、进行反馈互动。七是着力抓好学校教育。垃圾分类要从娃娃抓起，我们将携手教育部门，全面开展《条例》宣贯进学校，通过“小手拉大手”等活动，让每一个孩子去影响一个家庭，推动全社会共同参与。",
            "昆明市“生活垃圾分类 你我同行”主题文艺作品征集活动将持续至2021年3月1日,本次征集作品形式多样,优秀作品最高可获3000元奖金。 本次征集活动将面向广大网友征集创作集聚思想性、艺术性和趣味性为一体、广大群众喜闻乐见的网络文艺作品,有效引导和激励社会各界关注、支持、参与生活垃圾分类。活动主题为“生活垃圾分类 你我同行”,创作可以是音乐、文学、美术(海报)、书法、摄影、短视频、戏剧、手工艺品(废物利用)等多种形式。作品要求在内容上要主题突出,题材积极向上,贴近群众、贴近生活、贴近实际的原创作品(未在报刊、网络等媒体正式发表,未曾投入演出,严禁抄袭),同时具有较强的艺术性和观赏性,以具有云南特色的艺术形式展现为佳。广大网友可通过彩龙社区活动页进行投稿 届时,活动将通过“线上投票+线下评审”的方式,评选出一等奖1名、二等奖2名、三等奖3名、优秀奖10名。活动将为获奖作品统一颁发荣誉证书,并为一等奖发放稿酬3000元,二等奖2000元/人,三等奖1000元/人,优秀奖500元/人。",
            "什么是垃圾分类呢？垃圾分为哪几类呢？让小编带你了解一下~",
            "做好垃圾分类，共创美丽家园,北京市妇联,面向全市广大妇女和家庭发出倡议",
            "扬子晚报讯 （通讯员 戴黔 记者 裴睿）近日，南京市雨花台区梅山街道在新梅文化广场举行了一场“垃圾分类新时尚，生态文明金梅山”的主题活动。"};
    final List<Map<String,Object>> listmap=new ArrayList<Map<String,Object>> ();



    private Button b2,b3;
   // private Toolbar toolbar;
    private Handler handler;
    private CircleImageView tupian;

    private ViewPager topVp;

    private int[]images = new int[]{R.drawable.a4, R.drawable.a2, R.drawable.a3}; //模拟存放要展示的图片

    private List<ImageView> imageViews ;



    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ///menu设置
        androidx.appcompat.widget.Toolbar toolbar = (androidx.appcompat.widget.Toolbar) view.findViewById(R.id.toolBar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        //toolbar去掉左侧文字
       ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        setHasOptionsMenu(true);
        findView (view);

        toolbar.setOnMenuItemClickListener (new androidx.appcompat.widget.Toolbar.OnMenuItemClickListener () {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId ()){
                    case R.id.q1:
                        break;
                    case R.id.q2:
                        break;
                    case R.id.q3:
                        break;
                }
                return false;
            }
        });


        init ();
        intvp ();
        handler  = new Handler();
        handler.postDelayed(new TimerRunnable(),5000);



        listView=view.findViewById (R.id.listview);
        for (int i=0;i<8;i++){
            Map<String,Object> map=new HashMap<String,Object> ();
            map.put ("logo",tupian1[i]);
            map.put ("name",name[i]);
            map.put ("text",text[i]);
            listmap.add (map);
            registerForContextMenu (listView);

        }
        SimpleAdapter simpleAdapter=new SimpleAdapter (getActivity(),listmap,R.layout.lajifenlei, new String[]{"logo","name","text"}, new int[]{R.id.logo,R.id.name,R.id.text});
        listView.setAdapter (simpleAdapter);
        listView.setOnItemClickListener (new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map listView=listmap.get (position);
                String tp=Integer.toString (tupian1[position]);
                String name1= name[position];
                String text1= text[position];
                Intent intent = new Intent (getActivity (), xinwne.class);
                Bundle bundle = new Bundle ();
                bundle.putString ("logo", tp);
                bundle.putString ("biaoti",name1);
                bundle.putString ("zhengwen",text1);
                intent.putExtras (bundle);
                getActivity().startActivity(intent);

            }
        });




        return view;

    }
    class TimerRunnable implements Runnable{
        @Override
        public void run() {
            int curItem = topVp.getCurrentItem();
            topVp.setCurrentItem(curItem+1);
            if (handler!=null){
                handler.postDelayed(this,5000);
            }
        }
    }
    public void onDestroy() {
            super.onDestroy();
            handler = null; //此处在Activity退出时及时 回收
        }
    private void init(){
        imageViews = new ArrayList<> ();
        for(int i = 0;i<3;i++){
            ImageView imageView;
            imageView = new ImageView(getActivity ());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(images[i]);
            imageViews.add(imageView);
            imageView.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent (getActivity(), shipin.class));
                }
            });
        }
    }
    private void intvp(){
        topVp.setAdapter (new PagerAdapter () {
            @Override
            public int getCount() {
//        return imageViews.size(); 修改如下
                return 10000;
            }
            @Override

            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView(imageViews.get(position%imageViews.size())); 删除此句 此句不删除 会出现 滑动中 布局消失的情况 因为被移除了 此处这样修改会影响一些性能。。。。。
            }
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
//        container.addView(imageViews.get(position));
//        return imageViews.get(position); 修改如下
                try {
                    container.addView(imageViews.get(position%imageViews.size()));
                }catch (Exception e){
                }
                return imageViews.get(position%imageViews.size());
            }
        });
    }



    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
       MenuInflater aa = new MenuInflater (getActivity ());
        aa.inflate(R.menu.caidan,menu);

    }



    private void findView(View view) {
        topVp =  view.findViewById (R.id.gundong);

        b2 = view.findViewById (R.id.button2);
        b2.setOnClickListener (this);
        b3 = view.findViewById (R.id.button3);
        b3.setOnClickListener (this);

        tupian= view.findViewById (R.id.tupian);
        tupian.setOnClickListener (this);

        tupian.setImageURI (MyFragment.getdizi ());
        System.out.println ("555555555555555555555555");
        System.out.println (MyFragment.getdizi ());



    }
    @Override
    public void onClick(View view) {
         switch (view.getId ()){
             case R.id.button2:
                 startActivity(new Intent (getActivity(), dati.class));
              System.out.println ("报错");
                 break;
             case R.id.button3:
                 startActivity(new Intent (getActivity(), ceshiti.class));
                 System.out.println ("报错");
                 break;
            case R.id.tupian:
                startActivity(new Intent (getActivity(), gerenxinxi.class));
                break;



         }
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
       // MenuInflater ii = getMenuInflater();
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(Menu.NONE, 1, Menu.NONE, "喜欢");
        menu.add(Menu.NONE, 2, Menu.NONE, "不喜欢");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item
                .getMenuInfo();
        switch (item.getItemId()) {

            case 1:
                Toast.makeText (getActivity (),"以增加至喜欢",Toast.LENGTH_SHORT).show ();
                break;
            case 2:
                Toast.makeText (getActivity (),"收到",Toast.LENGTH_SHORT).show ();
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }


}
