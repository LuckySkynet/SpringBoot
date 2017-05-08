package com.skynet.entity;

import java.io.Serializable;

/**
 * @author Skynet
 * @date 2017年05月02日 18:43
 */
public class User implements Serializable {

    private static final long SerialVersionUID = 1L;

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
