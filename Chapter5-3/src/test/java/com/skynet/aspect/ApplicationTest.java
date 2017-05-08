package com.skynet.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


/**
 * @author Skynet
 * @date 2017年05月04日 16:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTest {

    @Autowired
    private MockMvc mvc;

    private Logger logger = LogManager.getLogger(ApplicationTest.class);

    @Test
    public void test(){
        logger.info("我是info信息");
        logger.debug("我是debug信息");
        logger.error("我是error信息");
    }

    @Test
    public void testMvc() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/hello")
                .param("name", "skynet"))
                .andExpect(content().string(equalTo("hello skynet age: 10")));
    }

}
