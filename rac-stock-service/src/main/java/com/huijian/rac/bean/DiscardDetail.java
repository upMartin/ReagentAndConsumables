package com.huijian.rac.bean;



public class DiscardDetail {
    private int discardDetailID;
    private String discardNo;
    private int goodsID;
    private String goodsName;
    private int quantity;
    private String unitPrice;
    private int unitID;
    private String unit;
    private String productionBatch;

    public int getDiscardDetailID() {
        return discardDetailID;
    }

    public void setDiscardDetailID(int discardDetailID) {
        this.discardDetailID = discardDetailID;
    }

    public String getDiscardNo() {
        return discardNo;
    }

    public void setDiscardNo(String discardNo) {
        this.discardNo = discardNo;
    }

    public int getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(int goodsID) {
        this.goodsID = goodsID;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getUnitID() {
        return unitID;
    }

    public void setUnitID(int unitID) {
        this.unitID = unitID;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getProductionBatch() {
        return productionBatch;
    }

    public void setProductionBatch(String productionBatch) {
        this.productionBatch = productionBatch;
    }
}
