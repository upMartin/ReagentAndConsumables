package com.huijian.rac.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutboundRecord {
    private Integer leaveStockNo;
    private String orderNo;
    private String leaveStock;
    private String personID;
    private String memo;
    private String receivingUnitName;
    private String supervisor;
    private BigDecimal sum;
    private String businessType;
    private Integer orderState;
    private List<LeaveStockListDetail> list;
}
