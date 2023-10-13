package com.zt.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Description
 * @Author ZT
 * @Version
 * @Date 2023/9/20 14:34
 */

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
//        this.strictInsertFill(metaObject, "createUser", Long.class, BaseContext.getCurrentId()); // 起始版本 3.3.0(推荐使用)
//        this.strictUpdateFill(metaObject, "updateUser", Long.class, BaseContext.getCurrentId()); // 起始版本 3.3.0(推荐使用)
        this.strictInsertFill(metaObject, "createUser", Long.class, 1L); // 起始版本 3.3.0(推荐使用)
        this.strictUpdateFill(metaObject, "updateUser", Long.class, 1L); // 起始版本 3.3.0(推荐使用)
//        this.fillStrategy(metaObject, "createUser", BaseContext.getCurrentId());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐)
//        this.strictUpdateFill(metaObject, "updateUser", Long.class, BaseContext.getCurrentId()); // 起始版本 3.3.0(推荐)
        this.strictUpdateFill(metaObject, "updateUser", Long.class, 1L); // 起始版本 3.3.0(推荐)
    }
}
