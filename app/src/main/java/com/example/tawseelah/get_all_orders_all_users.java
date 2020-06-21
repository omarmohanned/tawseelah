package com.example.tawseelah;

public class get_all_orders_all_users {
    private String coust_name;
    private String cus_phone_num;
    private String meal_name;
    private String meal_price;
    private String start_point;
    private String end_point;
    private  String res_name;
    private String meal_states;
    private  String dilivary_cost;
    private String key;
    private String uid;
    private String loc_longitude;
    private String loc_latitude;

    public get_all_orders_all_users() {
    }

    public get_all_orders_all_users(String coust_name, String cus_phone_num, String meal_name, String meal_price, String start_point, String end_point, String res_name, String meal_states, String dilivary_cost, String key, String uid, String loc_longitude, String loc_latitude) {
        this.coust_name = coust_name;
        this.cus_phone_num = cus_phone_num;
        this.meal_name = meal_name;
        this.meal_price = meal_price;
        this.start_point = start_point;
        this.end_point = end_point;
        this.res_name = res_name;
        this.meal_states = meal_states;
        this.dilivary_cost = dilivary_cost;
        this.key = key;
        this.uid = uid;
        this.loc_longitude = loc_longitude;
        this.loc_latitude = loc_latitude;
    }

    public String getCoust_name() {
        return coust_name;
    }

    public void setCoust_name(String coust_name) {
        this.coust_name = coust_name;
    }

    public String getCus_phone_num() {
        return cus_phone_num;
    }

    public void setCus_phone_num(String cus_phone_num) {
        this.cus_phone_num = cus_phone_num;
    }

    public String getMeal_name() {
        return meal_name;
    }

    public void setMeal_name(String meal_name) {
        this.meal_name = meal_name;
    }

    public String getMeal_price() {
        return meal_price;
    }

    public void setMeal_price(String meal_price) {
        this.meal_price = meal_price;
    }

    public String getStart_point() {
        return start_point;
    }

    public void setStart_point(String start_point) {
        this.start_point = start_point;
    }

    public String getEnd_point() {
        return end_point;
    }

    public void setEnd_point(String end_point) {
        this.end_point = end_point;
    }

    public String getRes_name() {
        return res_name;
    }

    public void setRes_name(String res_name) {
        this.res_name = res_name;
    }

    public String getMeal_states() {
        return meal_states;
    }

    public void setMeal_states(String meal_states) {
        this.meal_states = meal_states;
    }

    public String getDilivary_cost() {
        return dilivary_cost;
    }

    public void setDilivary_cost(String dilivary_cost) {
        this.dilivary_cost = dilivary_cost;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLoc_longitude() {
        return loc_longitude;
    }

    public void setLoc_longitude(String loc_longitude) {
        this.loc_longitude = loc_longitude;
    }

    public String getLoc_latitude() {
        return loc_latitude;
    }

    public void setLoc_latitude(String loc_latitude) {
        this.loc_latitude = loc_latitude;
    }
}
