package com.huijian.rac.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


public class Discard {
    private String discardNo;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date discardDate;
    private String departCode;
    private String personID;
    private String personName;
    private String reason;
    private String sumPrice;
    private String levelID;
    private String hospitalID;
    private List<DiscardDetail> list;

    public String getDiscardNo() {
        return discardNo;
    }

    public void setDiscardNo(String discardNo) {
        this.discardNo = discardNo;
    }

    public Date getDiscardDate() {
        return discardDate;
    }

    public void setDiscardDate(Date discardDate) {
        this.discardDate = discardDate;
    }

    public String getDepartCode() {
        return departCode;
    }

    public void setDepartCode(String departCode) {
        this.departCode = departCode;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(String sumPrice) {
        this.sumPrice = sumPrice;
    }

    public String getLevelID() {
        return levelID;
    }

    public void setLevelID(String levelID) {
        this.levelID = levelID;
    }

    public String getHospitalID() {
        return hospitalID;
    }

    public void setHospitalID(String hospitalID) {
        this.hospitalID = hospitalID;
    }

    public List<DiscardDetail> getList() {
        return list;
    }

    public void setList(List<DiscardDetail> list) {
        this.list = list;
    }
}
