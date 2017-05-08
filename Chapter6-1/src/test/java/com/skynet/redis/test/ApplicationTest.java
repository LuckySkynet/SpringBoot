package com.skynet.redis.test;

import com.skynet.redis.Application;
import com.skynet.redis.entity.Receiver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Skynet
 * @date 2017年05月04日 18:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTest {

    /**
     * 注意：RedisAutoConfiguration自动配置类将根据类名或字符串注入对应的RedisTemplate
     * 例如：RedisTemplate<String,String> 将注入 StringRedisTemplate
     * 而 RedisTemplate 将注入 RedisTemplate
     */
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private Receiver receiver;

    @Test
    public void testSetAndGet() {
        redisTemplate.opsForValue().set("ping","pong");
        System.out.println(redisTemplate.opsForValue().get("ping"));
    }

    @Test
    public void testPublish() throws InterruptedException {
        redisTemplate.convertAndSend("hello","好忧桑啊");
        receiver.getCountDownLatch().await();
    }

}
