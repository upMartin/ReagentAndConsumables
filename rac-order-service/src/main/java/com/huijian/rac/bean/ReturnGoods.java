package com.huijian.rac.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnGoods implements Serializable {
    private static final long serialVersionUID = 1L;
    private String returnGoodsNo;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date returnGoodsDate;
    private String collarPersonID;
    private String collarPersonName;
    private String auditPersonID;
    private String auditPersonName;
    private String reason;
    private BigDecimal sumPrice;
    private Integer returnGoodsState;
    private String hospitalID;
    private String hospitalName;
    private List<ReturnGoodsDetail> list;
}
