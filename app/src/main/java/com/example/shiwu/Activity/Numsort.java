package com.example.shiwu.Activity;

import com.example.shiwu.Sqlite.chengji;

import java.util.Comparator;

public class Numsort implements Comparator<chengji> {
    @Override
    public int compare(chengji o1, chengji o2) {

        if (Float.parseFloat(o1.getShop ()) > Float
                .parseFloat(o2.getShop ())) {
            return -1;
        }
        if (Float.parseFloat(o1.getShop ()) < Float
                .parseFloat(o2.getShop ())) {
            return 1;
        }
        return 0;

       // return o1.getShop ().compareTo(o2.getShop ());
    }
}