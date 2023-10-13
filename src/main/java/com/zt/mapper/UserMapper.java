package com.zt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zt.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * @Description
 * @Author ZT
 * @Version
 * @Date 2023/9/20 10:10
 */

//在对应的Mapper上面继承基本的类BaseMapper
@Repository
public interface UserMapper extends BaseMapper<User> {
    //
}


