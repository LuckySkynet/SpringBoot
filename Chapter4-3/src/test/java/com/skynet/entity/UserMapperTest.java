package com.skynet.entity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author Skynet
 * @date 2017年05月03日 13:38
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void findByName() throws Exception {
        User user = userMapper.findByName("hahaha");
        assertNotNull(user);
        assertEquals(2L, user.getId().intValue());
        user = userMapper.findByName("hahaha");
    }

    @Test
    public void findAll() throws Exception {
        List<User> users = userMapper.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void insert() throws Exception {
        userMapper.insert(4L, "qwe");
    }

    @Test
    public void update() throws Exception {
        User user = userMapper.findByName("hello");
        user.setName("skynet");
        userMapper.update(user);
       assertNotNull(userMapper.findByName("skynet"));
    }

    @Test
    public void delete() throws Exception {
        userMapper.delete(1L);
    }

    @Test
    public void inserByUser() throws Exception {
        User user = new User();
        user.setId(3L);
        user.setName("sss");
        userMapper.inserByUser(user);
    }

    @Test
    public void insertByMap() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 2L);
        map.put("name", "hahaha");
        userMapper.insertByMap(map);
    }

}