package com.example.shiwu.Util;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

public class Viewpager extends PagerAdapter {

    private List<ImageView> imageViewList;
    private ViewPager viewPager;

    public Viewpager(Object imageViewList, ViewPager picture_viewPager) {
    }

    /**
     * 构造方法，传入图片列表和ViewPager实例
     * @param images
     * @param viewPager
     */

    public void ViewPagerAdapter(List<ImageView> images, ViewPager viewPager){
        this.imageViewList  = images;
        this.viewPager = viewPager;
    }


    @Override
    public int getCount() {
//        return imageViewList.size() * 2;
        return Integer.MAX_VALUE;//保证能够无限循环
    }


    // 4.指定复用的判断逻辑（一般为固定写法）
    @Override
    public boolean isViewFromObject(View view, Object object) {
        boolean a = view == object;
        return a;
    }

    // 2.返回要显示的条目，并创建条目
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //container:容器，其实也就是ViewPager
        //position:当前要显示的条目的位置

        int newPosition = position % imageViewList.size();
        ImageView imageView = imageViewList.get(newPosition);
        Log.e("fenger", "创建条目:"+position );
        //a.将View对象添加到container容器中
        container.addView(imageView);

        //b.把View对象返回给框架，适配器
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View) object);
        Log.e("fenger","删除条目:"+position);
    }

}
