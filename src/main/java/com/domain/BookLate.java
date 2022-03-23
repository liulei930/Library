package com.domain;

import com.util.DateUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author 刘磊
 * @version 1.0
 */
@Setter
@Getter
@ToString
public class BookLate {
    private String name;
    private String bookName;
    private String BorrowTime;
    private String deadTime;

    public void setBorrowTime(Date borrowTime) {

        BorrowTime = DateUtils.dateToString(borrowTime);
    }

    public void setDeadTime(Date deadTime) {
        this.deadTime = DateUtils.dateToString(deadTime);
    }
}
