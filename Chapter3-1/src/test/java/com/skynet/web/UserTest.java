package com.skynet.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Skynet
 * @date 2017年04月28日 10:39
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void webTest() throws Exception {

        RequestBuilder request = null;

        //1、测试查询所有用户：getUserList，预期为空
        request = MockMvcRequestBuilders.get("/user/");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));

        //2、测试保存用户：save
        request = MockMvcRequestBuilders.post("/user/")
                .param("id", "1")
                .param("username","skynet")
                .param("age","11");

        mvc.perform(request).andExpect(content().string(equalTo("success")));

        //3、测试查询所有用户：getUserList，此时应该有数据
        request = MockMvcRequestBuilders.get("/user/");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"id\":1,\"username\":\"skynet\",\"age\":11}]")));

        //4、测试更新用户数据：update
        request = MockMvcRequestBuilders.put("/user/1")
                .param("username", "tomcat")
                .param("age", "22");

        mvc.perform(request).andExpect(content().string(equalTo("success")));

        //5、测试通过ID查询用户
        request = MockMvcRequestBuilders.get("/user/1");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":1,\"username\":\"tomcat\",\"age\":22}")));

        //6、删除用户
        request = MockMvcRequestBuilders.delete("/user/1");
        mvc.perform(request)
                .andExpect(content().string(equalTo("success")));

        //7、测试查询所有用户：getUserList，预期为空
        request = MockMvcRequestBuilders.get("/user/");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));

    }

}
