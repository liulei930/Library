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
@Setter @Getter @ToString
public class StudentBook {
    private Integer id;
    private Integer stuId;
    private Integer bookId;
    private Date borrowTime;
    private Date returnTime;
    private Date deadTime;
    private String borrowTimeStr;
    private String returnTimeStr;
    private String deadTimeStr;
    private Book book;//一条记录对应一个书籍

    public String getBorrowTimeStr() {
        if(borrowTime!=null){
         borrowTimeStr=DateUtils.dateToString(borrowTime);}
        return borrowTimeStr;
    }

    public String getReturnTimeStr() {
        if(returnTime!=null){
         returnTimeStr=DateUtils.dateToString(returnTime);}
        return returnTimeStr;
    }

    public String getDeadTimeStr() {
        if(deadTime!=null){
         deadTimeStr=DateUtils.dateToString(deadTime);}
        return deadTimeStr;
    }
}
