package com.skynet;

import com.skynet.dao.CustomerRepository;
import com.skynet.entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author Skynet
 * @date 2017年05月02日 15:24
 */
@SpringBootApplication
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * CommandLineRunner：在ApplicationContext加载完成后执行run方法
     * @param repository
     * @return
     */
    @Bean
    public CommandLineRunner commandLineRunner(final CustomerRepository repository) {
        return (String... args) -> {
            LOGGER.info("删除所有记录");
            repository.deleteAll();
            LOGGER.info("--------------------------------------------");
            LOGGER.info("当前记录数：" + repository.count());

            repository.save(new Customer("Jack", "Bauer"));
            repository.save(new Customer("Chloe", "O'Brian"));
            repository.save(new Customer("Kim", "Bauer"));
            repository.save(new Customer("David", "Palmer"));
            repository.save(new Customer("Michelle", "Dessler"));

            LOGGER.info("调用findAll()获取所有的Customer:");
            LOGGER.info("-------------------------------");
            for (Customer customer : repository.findAll()) {
                LOGGER.info(customer.toString());
            }
            LOGGER.info("");

            LOGGER.info("查询ID为1的Customer:");
            LOGGER.info("--------------------------------");
            Customer customer = repository.findOne(1L);
            LOGGER.info(customer.toString());
            LOGGER.info("");

            LOGGER.info("调用findByLastName('Bauer'):");
            LOGGER.info("--------------------------------------------");
            for (Customer bauer : repository.findByLastName("Bauer")) {
                LOGGER.info(bauer.toString());
            }
            LOGGER.info("");
        };
    }
}
