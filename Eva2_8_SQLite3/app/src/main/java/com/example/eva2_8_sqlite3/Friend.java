package com.example.eva2_8_sqlite3;

public class Friend {
    private int id;
    private String name;
    private String phone;

    public Friend() {
        //id = 1;
        name = "Diego";
        phone = "666-1111";
    }

    public Friend(int id, String name, String phone){
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
