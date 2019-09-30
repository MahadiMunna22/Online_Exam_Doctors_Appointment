package com.example.a201714032;

class StoreResult {
    String name;
    String time;
    String date;
    String test;

    public StoreResult() {
    }

    public StoreResult(String name, String time, String date, String test) {
        this.name = name;
        this.time = time;
        this.date = date;
        this.test = test;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}

