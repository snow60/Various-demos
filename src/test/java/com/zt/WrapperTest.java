package com.zt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zt.mapper.UserMapper;
import com.zt.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author ZT
 * @Version
 * @Date 2023/9/25 10:52
 */

@SpringBootTest
public class WrapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        //查询name不为空的用户，并且邮箱不为空的用户，年龄大于等于12
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                //字段 IS NOT NULL
                .isNotNull("name")
                .isNotNull("email")
                //大于等于
                .ge("age", 12);
        userMapper.selectList(wrapper).forEach(System.out::println);

    }

    @Test
    void test2(){
        //查询名字牛马
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "牛马");
        //查询一个数据，出现多个结果 ，使用List或Map
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    @Test
    void test3(){
        //查询年龄在20~30岁之间
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //区间
        wrapper.between("age", 20, 30);
        //查询结果数
        Long count = userMapper.selectCount(wrapper);
        System.out.println(count);
    }

    // 模糊查询
    @Test
    void test4(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 左和右 t%
        wrapper
                .notLike("name", "五")
                .likeRight("email", "2");

        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }
}
