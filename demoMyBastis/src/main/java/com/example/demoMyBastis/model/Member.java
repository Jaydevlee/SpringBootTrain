package com.example.demoMyBastis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private Long id;      //아이디
    private String name;  //이름
    private String email; //이메일
    private int age;      //나이
}
