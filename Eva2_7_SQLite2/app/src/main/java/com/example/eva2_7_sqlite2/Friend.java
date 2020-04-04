package com.example.eva2_7_sqlite2;

public class Friend {
    private String name;
    private String phone;

    public Friend() {
        name = "Diego";
        phone = "666-1111";
    }

    public Friend(String name, String phone){
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
