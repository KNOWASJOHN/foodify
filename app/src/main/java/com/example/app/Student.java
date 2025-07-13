package com.example.app;

public class Student {
    public String name;
    public int age;
    public String branch;

    public String id;           // CCE ID
    public String semester;
    public String hostel;
    public String phone;
    public String email;

    // ✅ Required empty constructor for Firebase
    public Student() {
    }

    // ✅ Constructor matching fields exactly
    public Student(String name, int age, String branch, String id, String semester, String hostel, String phone, String email) {
        this.name = name;
        this.age = age;
        this.branch = branch;
        this.id = id;
        this.semester = semester;
        this.hostel = hostel;
        this.phone = phone;
        this.email = email;
    }
}
