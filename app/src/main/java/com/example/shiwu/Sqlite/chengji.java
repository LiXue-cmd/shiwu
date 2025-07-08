package com.example.shiwu.Sqlite;

public class chengji {
    private String id;
    private String name;
    private String shop;
    private String price;
    private String num;

    public chengji(String id, String name, String shop) {
        this.id = id;
        this.name = name;
        this.shop = shop;
        this.price = price;
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
