package com.yibo.jta.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
@Table(name = "trade_goods")
public class TradeGoods {
    @Id
    @Column(name = "goods_id")
    private Long goodsId;

    /**
     * 商品名称
     */
    @Column(name = "goods_name")
    private String goodsName;

    /**
     * 商品库存
     */
    @Column(name = "goods_number")
    private Integer goodsNumber;

    /**
     * 商品价格
     */
    @Column(name = "goods_price")
    private BigDecimal goodsPrice;

    /**
     * 商品描述
     */
    @Column(name = "goods_desc")
    private String goodsDesc;

    /**
     * 添加时间
     */
    @Column(name = "add_time")
    private Date addTime;
}