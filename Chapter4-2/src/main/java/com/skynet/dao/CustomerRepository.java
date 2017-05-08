package com.skynet.dao;

import com.skynet.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 除了CrudRepository，类似还有JpaRepository接口
 *
 * @author Skynet
 * @date 2017年05月02日 15:13
 */
public interface CustomerRepository extends CrudRepository<Customer, Long>{

    List<Customer> findByLastName(String lastName);

}
