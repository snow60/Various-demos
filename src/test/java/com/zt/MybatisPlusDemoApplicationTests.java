package com.zt;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zt.mapper.UserMapper;
import com.zt.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MybatisPlusDemoApplicationTests {

    // 继承了BaseMapper，所有的方法来自父类
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        //
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    //测试插入
    @Test
    public void testInsert(){
        User user = new User();
        user.setName("张三");
        user.setAge(20);
        user.setEmail("10199991111@qq.com");

        int result = userMapper.insert(user);
        System.out.println(result);
        System.out.println(user);
    }

    //测试更新
    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(3L);
        user.setName("李四");
        user.setAge(20);

        //
        int i = userMapper.updateById(user);
        System.out.println(i);
    }

    //测试乐观锁成功
    @Test
    public void testOptimisticLocker(){
        // 1、查询用户信息
        User user = userMapper.selectById(1L);
        // 2、修改用户信息
        user.setName("吴迪");
        user.setEmail("2551000322@qq.com");
        // 3、执行更新操作
        userMapper.updateById(user);

    }

    //测试乐观锁失败
    @Test
    public void testOptimisticLocker2(){

        // 线程1
        User user = userMapper.selectById(1L);
        user.setName("吴迪111");
        user.setEmail("2551000321@qq.com");

        // 线程2
        User user2 = userMapper.selectById(1L);
        user2.setName("吴迪222");
        user2.setEmail("2551000323@qq.com");
        userMapper.updateById(user2);

        userMapper.updateById(user);

    }

    @Test
    public void testOptimisticLock3() throws InterruptedException {
        // 创建一个共享的 User 对象
        User user = new User();
        //先查再更新
        user.setId(1L);

        // 线程1更新用户信息
        Thread thread1 = new Thread(() -> {
            User user1 = userMapper.selectById(user.getId());
            user1.setName("Thread 1");
            userMapper.updateById(user1);
        });

        // 线程2更新用户信息
        Thread thread2 = new Thread(() -> {
            User user2 = userMapper.selectById(user.getId());
            user2.setName("Thread 2");
            userMapper.updateById(user2);
        });

        // 启动线程1和线程2
        thread1.start();
        thread2.start();

        // 等待线程1和线程2执行完成
        thread1.join();
        thread2.join();

        // 读取最终的用户信息
        User finalUser = userMapper.selectById(user.getId());
        System.out.println("Final User: " + finalUser);
    }

    //测试查询
    @Test
    public void testSelectById(){
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    //测试批量查询
    @Test
    public void testSelectByBatchId(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);
    }

    //条件查询map
    @Test
    public void testSelectByBatchIds(){
        HashMap<String, Object> map = new HashMap<>();
        //自定义查询
        map.put("name", "李四");

        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    //测试分页查询
    @Test
    public void testPage(){
        //参数一：当前页， 参数二：页面大小
        Page<User> page = new Page<>(1, 5);
        userMapper.selectPage(page, null);

        page.getRecords().forEach(System.out::println);
    }

    //测试删除
    @Test
    public void testDeleteById(){
        userMapper.deleteById(2L);
    }

    //测试通过id批量删除
    @Test
    public void testDeleteByBatchId(){
        userMapper.deleteBatchIds(Arrays.asList(1L, 2L));
    }

    //通过map删除
    @Test
    public void testDeleteByBatchIds(){
        HashMap<String, Object> map = new HashMap<>();
        //自定义删除
        map.put("name", "李四");
        userMapper.deleteByMap(map);
    }
}
