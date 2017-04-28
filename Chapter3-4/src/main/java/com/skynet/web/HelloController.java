package com.skynet.web;

import com.skynet.exception.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Skynet
 * @date 2017年04月28日 16:54
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello() throws Exception {
        throw new Exception("发生错误了");
    }

    @RequestMapping("/json")
    public String json() throws MyException {
        throw new MyException("又发生错误了");
    }

    @RequestMapping("/index")
    public String index(ModelMap modelMap){
        modelMap.addAttribute("say","Hello world");
        return "index";
    }

}
