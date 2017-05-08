package com.skynet.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Skynet
 * @date 2017年05月02日 13:58
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("************************开始执行SQL操作************************");

        List<Object[]> splitUpNames = new ArrayList<>();

        splitUpNames.add(new Object[]{"John Woo"});
        splitUpNames.add(new Object[]{"Tom Cat"});
        splitUpNames.add(new Object[]{"Rose Jerry"});

        splitUpNames.forEach(name -> LOGGER.info(String.format("插入记录 %s", name[0])));

        jdbcTemplate.batchUpdate("INSERT INTO USER(NAME) VALUES(?)", splitUpNames);

        LOGGER.info("查询User表记录");

        jdbcTemplate.query(
                "SELECT id, name FROM user", new Object[]{},
                (rs, rowNum) -> new User(rs.getInt("id"), rs.getString("name"))).forEach(user -> LOGGER.info(user.toString()));

        LOGGER.info("删除全部记录");
        jdbcTemplate.update("DELETE FROM USER");

        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM USER", Integer.class);
        LOGGER.info("当前记录数为: " + count );

        LOGGER.info("************************结束SQL操作************************");
    }
}
