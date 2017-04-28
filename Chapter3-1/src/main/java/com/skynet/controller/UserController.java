package com.skynet.controller;

import com.skynet.entity.UserEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author Skynet
 * @date 2017年04月28日 9:52
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private Map<Long, UserEntity> map = Collections.synchronizedMap(new HashMap<Long, UserEntity>());

    private static final String SUCCESS = "success";

    /**
     * 处理POST请求，保存用户
     *
     * @param userEntity
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String save(UserEntity userEntity) {
        map.put(userEntity.getId(), userEntity);
        return SUCCESS;
    }

    /**
     * 处理PUT请求，更新用户
     * @param id
     * @param userEntity
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String update(@PathVariable("id") Long id, UserEntity userEntity) {
        UserEntity user = map.get(id);
        user.setUsername(userEntity.getUsername());
        user.setAge(userEntity.getAge());
        map.put(id, user);
        return SUCCESS;
    }

    /**
     * 处理DELETE请求，删除用户
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id) {
        map.remove(id);
        return SUCCESS;
    }

    /**
     * 处理GET请求，获取所有用户
     *
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<UserEntity> getUserList() {
        return new ArrayList<UserEntity>(map.values());
    }

    /**
     * 处理GET请求，通过ID查询用户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserEntity getUser(@PathVariable("id") Long id) {
        return map.get(id);
    }
}
