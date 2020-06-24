package com.huijian.rac.bean;

import java.io.Serializable;
import java.util.Date;

public class OrderOperate implements Serializable {
    private Integer id;

    private String orderno;

    private String operating;

    private Integer orderstate;

    private Date oreratingdate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public String getOperating() {
        return operating;
    }

    public void setOperating(String operating) {
        this.operating = operating == null ? null : operating.trim();
    }

    public Integer getOrderstate() {
        return orderstate;
    }

    public void setOrderstate(Integer orderstate) {
        this.orderstate = orderstate;
    }

    public Date getOreratingdate() {
        return oreratingdate;
    }

    public void setOreratingdate(Date oreratingdate) {
        this.oreratingdate = oreratingdate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderno=").append(orderno);
        sb.append(", operating=").append(operating);
        sb.append(", orderstate=").append(orderstate);
        sb.append(", oreratingdate=").append(oreratingdate);
        sb.append("]");
        return sb.toString();
    }
}