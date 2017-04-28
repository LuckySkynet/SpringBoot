package com.skynet.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Skynet
 * @date 2017年04月28日 11:42
 */
@Controller
public class HelloController {

    @RequestMapping("/th")
    public String index1(ModelMap map) {
        map.addAttribute("username", "skynet");
        return "index";
    }

    @RequestMapping("/ftl")
    public String index2(ModelMap map) {
        map.addAttribute("username", "tomcat");
        return "index2";
    }

    @RequestMapping("/vm")
    public String index3(ModelMap map) {
        map.addAttribute("username", "jerry");
        return "index2";
    }
}
