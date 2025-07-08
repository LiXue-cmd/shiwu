package com.example.shiwu.Activity;


import com.example.shiwu.Sqlite.chengji;

import java.util.Comparator;

public class Pricesort implements Comparator<chengji> {
    @Override
    public int compare(chengji o1, chengji o2) {
        return o1.getShop ().compareTo(o2.getShop ());
    }
}