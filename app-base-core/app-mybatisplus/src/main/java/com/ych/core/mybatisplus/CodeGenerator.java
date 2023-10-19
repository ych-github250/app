package com.ych.core.mybatisplus;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.ych.core.mybatisplus.entity.BaseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CodeGenerator {
    private static final String URL = "jdbc:mysql://localhost:3306/app?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";
    private static final String AUTHOR = "YCH   `";


    public static void main(String[] args) {
        FastAutoGenerator.create(URL, USERNAME, PASSWORD)
                // 全局配置
                .globalConfig((scanner, builder) -> builder
                                .disableOpenDir() // 禁止打开输出目录
                                .outputDir(System.getProperty("user.dir") + "/generator") // 输出目录
                                .author(AUTHOR)
//                        .enableKotlin() // 开启 Kotlin 模式
                                .enableSwagger() // 开启 swagger 模式
                                .dateType(DateType.ONLY_DATE) // 时间策略
                                .commentDate("yyyy-MM-dd") // 注释日期
                )
                // 包配置
                .packageConfig((scanner, builder) -> builder
                                .parent(" ") // 父包名
//                        .moduleName(scanner.apply("请输入模块名？")) // 父包模块名
                                .entity("model.po") // Entity包名
                                .service("service") // Service包名
                                .serviceImpl("service.impl") // ServiceImpl包名
                                .mapper("mapper") // Mapper包名
                                .xml("mapper.xml") // Mapper XML包名
                                .controller("controller") // Controller包名
                                .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir") + "/generator/mapper/xml")) // 路径配置信息
                )
//                 模板配置
//                .templateConfig((scanner, builder) -> builder
//                        .disable() // 禁用所有模板
//                        .disable(TemplateType.XML) // 禁用模板
//                        .entity("templates/entity.java") // Entity模板路径
//                        .entityKt("templates/entity.kt") // EntityKt模板路径
//                        .service("templates/service.java") // Service模板路径
//                        .serviceImpl("templates/serviceImpl.java") // ServiceImpl模板路径
//                        .mapper("templates/mapper.java") // Mapper模板路径
//                        .xml("templates/mapper.xml") // Mapper XML模板路径
//                        .controller("templates/controller.java") // Controller模板路径
//                )
                // 策略配置
                .strategyConfig((scanner, builder) -> builder
//                                .enableCapitalMode() // 开启大写命名
//                                .enableSkipView() // 开启跳过视图
//                                .disableSqlFilter() // 禁用sql过滤
//                                .enableSchema() // 开启schema模式
                                .addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
                                .addTablePrefix(scanner.apply("请输入表前缀？")) // 表前缀
                                // 实体策略配置
                                .entityBuilder()
                                .superClass(BaseEntity.class) // 自定义继承的Entity类全称，带包名
//                                .disableSerialVersionUID() // 禁用 serialVersionUID
//                                .enableFileOverride() // 开启覆盖已生成文件
//                                .enableColumnConstant() // 开启字段常量
//                                .enableChainModel() // 开启链式模型
                                .enableLombok() // 开启 Lombok 模式
                                .enableRemoveIsPrefix() // 开启移除 is 前缀
                                .enableTableFieldAnnotation() // 开启实体字段注解
//                                .enableActiveRecord() // 开启 ActiveRecord 模式
                                .versionColumnName("version") // 乐观锁属性名称
                                .logicDeleteColumnName("deleted") // 逻辑删除属性名称
                                .naming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
                                .addSuperEntityColumns("create_by", "update_by", "create_time", "update_time", "deleted") // 父类公共字段
//                                .addIgnoreColumns("create_by", "update_by", "create_time", "update_time", "deleted") // 忽略父类公共字段
//                                .addTableFills(new Column("create_time", FieldFill.INSERT)) // 表填充字段
//                                .addTableFills(new Column("update_time", FieldFill.INSERT_UPDATE)) // 表填充字段
                                .idType(IdType.ASSIGN_ID) // 主键类型
//                                .formatFileName("%sEntity") // 自定义文件名称
                                // controller 策略配置
                                .controllerBuilder()
//                        .superClass() // 自定义继承的Controller类全称，带包名
                                .enableFileOverride() // 开启覆盖已生成文件
//                                .enableHyphenStyle() // 开启驼峰转连字符
                                .enableRestStyle() // 开启 REST 风格
                                .formatFileName("%sController") // 自定义文件名称
                                // service 策略配置
                                .serviceBuilder()
//                        .superServiceClass() // 自定义继承的Service类全称，带包名
//                        .superServiceImplClass() // 自定义继承的ServiceImpl类全称，带包名
                                .enableFileOverride() // 开启覆盖已生成文件
                                .formatServiceFileName("%sService") // 自定义文件名称
                                .formatServiceImplFileName("%sServiceImpl") // 自定义文件名称
                                // mapper 策略配置
                                .mapperBuilder()
//                        .superClass() // 自定义继承的Mapper类全称，带包名
                                .enableBaseColumnList() // 开启 BaseColumnList
                                .enableBaseResultMap() // 开启 BaseResultMap
                                .enableFileOverride() // 开启覆盖已生成文件
                                .formatMapperFileName("%sMapper") // 自定义文件名称
                                .formatXmlFileName("%sMapper") // 自定义文件名称
                )
                .injectionConfig(consumer -> {
//                    Map<String, String> customFile = new HashMap<>();
//                    customFile.put("DTO.java", "/templates/entityDTO.java.ftl");
//                    consumer.customFile(customFile);
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}
