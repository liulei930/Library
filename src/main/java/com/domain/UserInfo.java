package com.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author 刘磊
 * @version 1.0
 */
@Setter @Getter
@ToString
public class UserInfo {
    private Integer userId;
    private String userName;
    private String password;
    private String userType;
}
