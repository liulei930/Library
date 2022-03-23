package com.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author 刘磊
 * @version 1.0
 */
@Setter @Getter @ToString
public class Student implements Serializable {
    public Integer sid;
    private String no;
    private String name;
    private String sex;
    private String grade;
    private String major;
    private String phone;
    private String password;//自动设置登录密码为手机号后六位
   List<StudentBook> studentBookList; //一个学生有多个记录
    public void setPassword(String password) {
        if(phone!=null){
            password=phone.substring(phone.length()-6,phone.length());
        }
        this.password = password;
    }
    public String getPassword() {
        if(phone!=null){
            password=phone.substring(phone.length()-6,phone.length());
        }
        return password;
    }
}
