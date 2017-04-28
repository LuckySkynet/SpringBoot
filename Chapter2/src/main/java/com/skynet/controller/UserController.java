package com.skynet.controller;

import com.skynet.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Skynet
 * @date 2017年04月27日 17:36
 */
@RestController
public class UserController {

    @Autowired
    private UserEntity userEntity;

    //spring-boot可通过${...}直接引用配置文件
    @RequestMapping("/${super}")
    public String hello(){
        System.out.println(userEntity);
        return "Hello World";
    }

}
