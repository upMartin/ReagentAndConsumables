package com.huijian.rac.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutboundSecondRecord {
    private Integer leaveStockNo;
    private String leaveStockDate;
    private String personID;
    private String memo;
    private String departCode;
    private String departName;
    private String supervisor;
    private BigDecimal sum;
    private String businessType;
    private String hospitalID;
    private String TXM;
    private String leaveStockState;
    private List<SecondLeaveStockListDetail> list;
}
