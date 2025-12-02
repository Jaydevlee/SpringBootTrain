package com.example.demo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Member {
    private int id;
    private String name;
    private String email;
    private int age;

    public Member(int id, String name, String email, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }
    public String toString(){
        return "Member(id=" + id + ", name=" + name + ", email=" + email + ", age=" + age + ")";
    }
}
