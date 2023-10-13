package com.zt;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Author ZT
 * @Description
 * @Date 2023/10/7
 * @Version 1.0
 **/
@SpringBootTest
public class UserGenetator {

    @Test
    void CodeGenerator() {
        List<String> tables = new ArrayList<>();
        tables.add("s_clazz");
//        tables.add("p_question");
//        tables.add("p_answer");
//        tables.add("p_correct");
//ail
        //数据源配置
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/studentmanager", "root", "123456")
                //全局配置
                .globalConfig(builder -> {
                    //作者
                    builder.author("zt")
                            //开启覆盖之前生成的文件
                            .fileOverride()
                            .outputDir(System.getProperty("user.dir") + "\\src\\main\\java")    //输出路径(写到java目录)
                            .author("zt")
                            //开启swagger
                            .enableSwagger()
                            .dateType(DateType.TIME_PACK)
                            .commentDate("yyyy-MM-dd")
                            .build();
                })
                //包配置
                .packageConfig(builder -> {
                    builder.parent("com.zt")
                            .moduleName("practice")
                            .entity("entity")
                            .service("service")
                            .serviceImpl("service.impl")
                            .controller("controller")
                            .mapper("mapper")
                            .xml("mapper")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir") + "\\src\\main\\resources\\mapper"));
                })
                //策略配置
                .strategyConfig(builder -> {
                    // 设置需要生成的表名
                    builder.addInclude(tables)
                            // 设置过滤表前缀
                            .addTablePrefix("s_")

                            // entity 策略配置
                            .entityBuilder()
                            // 设置父类为 BaseEntity.class
                            .superClass(BaseEntity.class)
                            // 禁用生成 serialVersionUID
                            .disableSerialVersionUID()
                            // 覆盖
//                            .enableFileOverride(true)
                            // 开启链式模型
//                            .enableChainModel()
                            // 开启 lombok 模型
                            .enableLombok()
                            // 开启 Boolean 类型字段移除 is 前缀
//                            .enableRemoveIsPrefix()
                            // 开启生成实体时生成字段注解
                            .enableTableFieldAnnotation()
                            // 开启 ActiveRecord 模型
//                            .enableActiveRecord()
                            // 设置乐观锁字段名(数据库字段)
//                            .versionColumnName("version")
                            // 设置逻辑删除字段名(数据库字段)
//                            .logicDeleteColumnName("deleted")
                            // 数据库表映射到实体的命名策略为不改变
                            .naming(NamingStrategy.underline_to_camel)
                            // 数据库表字段映射到实体的命名策略为下划线转驼峰
                            .columnNaming(NamingStrategy.underline_to_camel)
                            // 添加父类公共字段
//                            .addSuperEntityColumns("id", "created_by", "created_time", "updated_by", "updated_time")
                            // 添加忽略字段
//                            .addIgnoreColumns("age")
                            // 添加表字段填充
//                            .addTableFills(new Column("create_time", FieldFill.INSERT))
                            // 添加表字段填充
//                            .addTableFills(new Property("updateTime", FieldFill.INSERT_UPDATE))
                            // 全局主键类型为自增
                            .idType(IdType.AUTO)
                            // 格式化文件名称
                            .formatFileName("%sEntity")

                    // controller 策略配置
                            .controllerBuilder()
                            .enableHyphenStyle()
                            .formatFileName("%sController")

                            // service 策略配置
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImp")

                            // mapper 策略配置
                            .mapperBuilder()
                            .formatMapperFileName("%sDao")
                            .formatXmlFileName("%sXml");
                })
                // 模板配置
//                .templateConfig(new Consumer<TemplateConfig.Builder>() {
//                    @Override
//                    public void accept(TemplateConfig.Builder builder) {
//                        // 实体类使用我们自定义模板
//                        builder.entity("templates/myentity.java");
//                    }
//                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}



