package com.skynet.entity;

import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;

/**
 * @author Skynet
 * @date 2017年05月02日 18:49
 */
@Mapper
public interface UserMapper {

    @Select("select id,name from hello where name=#{name}")
    User findByName(@Param("name") String name);

    @Select("select id,name from hello")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name")
    })
    List<User> findAll();

    @Insert("insert into hello(id,name) values (#{id},#{name})")
    int insert(@Param("id") Long id, @Param("name") String name);

    @Update("update hello set name=#{name} where id=#{id}")
    void update(User user);

    @Delete("delete from hello where id=#{id}")
    void delete(Long id);

    @Insert("insert into hello(id,name) values(#{id},#{name})")
    int inserByUser(User user);

    @Insert("INSERT INTO hello(id, name) VALUES(#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR})")
    int insertByMap(Map<String, Object> map);
}
