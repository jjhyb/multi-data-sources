package com.yibo.jta.controller;


import com.yibo.jta.domain.ao.TradeGoodsAO;
import com.yibo.jta.domain.ao.UserAO;
import com.yibo.jta.domain.entity.TradeGoods;
import com.yibo.jta.domain.entity.User;
import com.yibo.jta.domain.vo.UserVO;
import com.yibo.jta.response.Result;
import com.yibo.jta.response.ResultUtil;
import com.yibo.jta.service.TradeGoodsService;
import com.yibo.jta.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: huangyibo
 * @Date: 2020/6/10 23:38
 * @Description:
 */

@RestController
@RequestMapping("/datasource")
@Api(value = "动态数据源展示相关的api",description = "动态数据源展示相关的api",tags = {"动态数据源展示相关的api"})
public class JTAController {

    @Autowired
    private UserService userService;

    @Autowired
    private TradeGoodsService tradeGoodsService;

    @ApiOperation(value = "获取User的记录", notes = "获取User的所有记录")
    @GetMapping("/user/findAl1")
    public Result<List<UserVO>> findUser(){
        List<User> list = userService.findAll();
        List<UserVO> userVoList = list.stream().map(user -> {
            UserVO userVo = new UserVO();
            BeanUtils.copyProperties(user, userVo);
            return userVo;
        }).collect(Collectors.toList());
        return ResultUtil.success(userVoList);
    }

    @ApiOperation(value = "根据id获取用户", notes = "根据id获取用户")
    @GetMapping("/user/{id}")
    public Result<UserVO> findUserById(@ApiParam(name = "id", value = "用户id", required = true) @PathVariable Integer id){
        UserVO userVo = new UserVO();
        User user = userService.findById(id);
        BeanUtils.copyProperties(user,userVo);
        return ResultUtil.success(userVo);
    }

    @ApiOperation(value = "新增用户", notes = "新增用户")
    @PostMapping("/user")
    public Result<String> addUser(@ApiParam("userAo") @RequestBody UserAO userAo){
        String result = userService.addUser(userAo);
        return ResultUtil.success(result);
    }

    @ApiOperation(value = "获取TradeGoods的记录", notes = "获取TradeGoods的所有记录")
    @GetMapping("/goods/findAl3")
    public Result<List<TradeGoods>> findTradeGoods(){
        List<TradeGoods> list = tradeGoodsService.findAll();
        return ResultUtil.success(list);
    }

    @ApiOperation(value = "新增TradeGoods", notes = "新增TradeGoods")
    @PostMapping("/goods")
    public Result<String> addTradeGoods(@ApiParam("tradeGoodsAO") @RequestBody TradeGoodsAO tradeGoodsAO){
        String result = tradeGoodsService.addTradeGoods(tradeGoodsAO);
        return ResultUtil.success(result);
    }

}
