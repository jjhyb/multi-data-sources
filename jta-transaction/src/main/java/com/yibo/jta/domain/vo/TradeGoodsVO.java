package com.yibo.jta.domain.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TradeGoodsVO {

    private Long goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品库存
     */
    private Integer goodsNumber;

    /**
     * 商品价格
     */
    private BigDecimal goodsPrice;

    /**
     * 商品描述
     */
    private String goodsDesc;

    /**
     * 添加时间
     */
    private Date addTime;
}