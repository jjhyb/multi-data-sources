package com.yibo.multisource.domain.entity.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TradeGoodsAO {

    private Long goodsId;

    private String goodsName;

    private Integer goodsNumber;

    private BigDecimal goodsPrice;

    private String goodsDesc;

    private Date addTime;
}