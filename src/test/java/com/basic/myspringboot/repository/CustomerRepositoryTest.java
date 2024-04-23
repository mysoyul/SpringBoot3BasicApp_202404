package com.basic.myspringboot.repository;

import com.basic.myspringboot.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customerRepository;

    @Test
    @Rollback(value = false)
    public void customer() throws Exception{
        Customer customer = new Customer();
        customer.setCustomerId("A001");
        customer.setCustomerName("스프링");
        Customer addCustomer = customerRepository.save(customer);
        System.out.println(addCustomer.getCustomerId() + " " + addCustomer.getCustomerName());
        //junit5
        assertNotNull(addCustomer);
        assertEquals(addCustomer.getCustomerName(),"스프링");
        //assertj
        assertThat(addCustomer).isNotNull();
        assertThat(addCustomer.getCustomerName()).isEqualTo("스프링");

        Optional<Customer> optionalCustomer =
                customerRepository.findByCustomerId(addCustomer.getCustomerId());

    }
}