package com.skynet.web;

import com.skynet.annotation.SysLogAnno;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Skynet
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    @SysLogAnno(value = "三千世界")
    public String hello(@RequestParam String name,@RequestParam(defaultValue = "10") String age){
        return "hello " + name + " age: " + age;
    }

}
