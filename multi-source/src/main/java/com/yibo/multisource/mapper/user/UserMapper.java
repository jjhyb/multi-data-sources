package com.yibo.multisource.mapper.user;

import com.yibo.multisource.domain.entity.user.User;
import org.springframework.beans.factory.annotation.Qualifier;
import tk.mybatis.mapper.common.Mapper;

//@Qualifier("db1SqlSessionTemplate")
public interface UserMapper extends Mapper<User> {
}