package com.yibo.controller;

import com.yibo.center.domain.entity.Share;
import com.yibo.center.domain.entity.TradeGoods;
import com.yibo.center.domain.entity.User;
import com.yibo.center.domain.vo.TradeGoodsAO;
import com.yibo.center.domain.vo.UserAo;
import com.yibo.center.domain.vo.UserVo;
import com.yibo.response.Result;
import com.yibo.response.ResultUtil;
import com.yibo.service.ShareService;
import com.yibo.service.TradeGoodsService;
import com.yibo.service.UserService;
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
public class DynamicDataSourceController {

    @Autowired
    private ShareService shareService;

    @Autowired
    private UserService userService;

    @Autowired
    private TradeGoodsService tradeGoodsService;

    @ApiOperation(value = "获取User的记录", notes = "获取User的所有记录")
    @GetMapping("/user/findAl1")
    public Result<List<UserVo>> findUser(){
        List<User> list = userService.findAll();
        List<UserVo> userVoList = list.stream().map(user -> {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(user, userVo);
            return userVo;
        }).collect(Collectors.toList());
        return ResultUtil.success(userVoList);
    }

    @ApiOperation(value = "根据id获取用户", notes = "根据id获取用户")
    @GetMapping("/user/{id}")
    public Result<UserVo> findUserById(@ApiParam(name = "id", value = "用户id", required = true) @PathVariable Integer id){
        UserVo userVo = new UserVo();
        User user = userService.findById(id);
        BeanUtils.copyProperties(user,userVo);
        return ResultUtil.success(userVo);
    }

    @ApiOperation(value = "新增用户", notes = "新增用户")
    @PostMapping("/user")
    public Result<String> addUser(@ApiParam("userAo") @RequestBody UserAo userAo){
        String result = userService.addUser(userAo);
        return ResultUtil.success(result);
    }

    @ApiOperation(value = "获取Share的记录", notes = "获取Share的所有记录")
    @GetMapping("/share/findAl2")
    public Result<List<Share>> findShare(){
        List<Share> list = shareService.findAll();
        return ResultUtil.success(list);
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
