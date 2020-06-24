package com.huijian.rac.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
public class Material {
    private String id;
    private String name;
    private String brand;
    private String specification;
    private String unit;
    private String secondaryAccounting;
    private String amount;
    private String unitPrice;
    private String sum;
    private String productionBatch;
    private String termOfValidity;
    private String registrationNumber;
    private String company;
    private String origin;
    private String pack;

    @Override
    public String toString() {
        return "Materil{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", specification='" + specification + '\'' +
                ", unit='" + unit + '\'' +
                ", secondaryAccounting='" + secondaryAccounting + '\'' +
                ", amount='" + amount + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", sum='" + sum + '\'' +
                ", productionBatch='" + productionBatch + '\'' +
                ", termOfValidity='" + termOfValidity + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", company='" + company + '\'' +
                ", origin='" + origin + '\'' +
                ", pack='" + pack + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSecondaryAccounting() {
        return secondaryAccounting;
    }

    public void setSecondaryAccounting(String secondaryAccounting) {
        this.secondaryAccounting = secondaryAccounting;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getProductionBatch() {
        return productionBatch;
    }

    public void setProductionBatch(String productionBatch) {
        this.productionBatch = productionBatch;
    }

    public String getTermOfValidity() {
        return termOfValidity;
    }

    public void setTermOfValidity(String termOfValidity) {
        this.termOfValidity = termOfValidity;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }
}
