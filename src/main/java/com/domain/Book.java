package com.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 刘磊
 * @version 1.0
 */
@Data
public class Book implements Serializable {
    private Integer bid;
    private String name;
    private String type;
    private String writer;
    private String pub;
}
