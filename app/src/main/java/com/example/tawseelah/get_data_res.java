package com.example.tawseelah;

public class get_data_res {
    private String firstname2;
    private String secondname2;
    private String restaurantname2;
    private String restaurantplace2;
    private String loc_longitude;
    private  String loc_latitude;
    private String phonenumber;

    public get_data_res(String firstname2, String secondname2, String restaurantname2, String restaurantplace2, String loc_longitude, String loc_latitude, String phonenumber) {
        this.firstname2 = firstname2;
        this.secondname2 = secondname2;
        this.restaurantname2 = restaurantname2;
        this.restaurantplace2 = restaurantplace2;
        this.loc_longitude = loc_longitude;
        this.loc_latitude = loc_latitude;
        this.phonenumber = phonenumber;
    }

    public get_data_res() {
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getFirstname2() {
        return firstname2;
    }

    public void setFirstname2(String firstname2) {
        this.firstname2 = firstname2;
    }

    public String getSecondname2() {
        return secondname2;
    }

    public void setSecondname2(String secondname2) {
        this.secondname2 = secondname2;
    }

    public String getRestaurantname2() {
        return restaurantname2;
    }

    public void setRestaurantname2(String restaurantname2) {
        this.restaurantname2 = restaurantname2;
    }

    public String getRestaurantplace2() {
        return restaurantplace2;
    }

    public void setRestaurantplace2(String restaurantplace2) {
        this.restaurantplace2 = restaurantplace2;
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
