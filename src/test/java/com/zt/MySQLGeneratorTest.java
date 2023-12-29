package com.zt;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * MySQL 代码生成
 *
 * @author lanjerry
 * @since 3.5.3
 */
@SpringBootTest
public class MySQLGeneratorTest {

    /**
     * 数据源配置
     */
    private static final DataSourceConfig DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder(
            "jdbc:mysql://localhost:3306/studentmanager?serverTimezone=Asia/Shanghai",
            "root",
            "123456")
            // TODO 数据库
            .schema("studentmanager")
            // .typeConvert(new MySqlTypeConvert())
            // .keyWordsHandler(new MySqlKeyWordsHandler())
            // .databaseQueryClass(SQLQuery.class)
            // .addConnectionProperty("remarks", "true")
            .build();

    @Test
    public void testSimple() {
        AutoGenerator generator = new AutoGenerator(DATA_SOURCE_CONFIG);


    }
}
