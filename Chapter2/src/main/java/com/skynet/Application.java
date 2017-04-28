package com.skynet;

import com.skynet.entity.UserEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * 测试读取外部文件
 * 1、配置实体类，使用@ConfigurationProperties注解，可设置前缀
 * 2、在启动类中添加@EnableConfigurationProperties注解，参数为配置的实体类{UserEntity.class}
 *
 * @author Skynet
 * @date 2017年04月27日 17:30
 */
@SpringBootApplication
@EnableConfigurationProperties({UserEntity.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
