package com.huijian.rac.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveStockList {
    private Integer leaveStockNo;
    private String orderNo;
    private String leaveStock;
    private String personID;
    private String memo;
    private String receivingUnitName;
    private String supervisor;
    private BigDecimal sum;
    private List<LeaveStockListDetail> list;
}
