package com.mdidech.customerservice;

import com.mdidech.customerservice.entities.Customer;
import com.mdidech.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {
    @Autowired
    private CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

//    @Bean
    CommandLineRunner commandLineRunner(){

        return args -> {
            customerRepository.save(new Customer(null,"mdidech","abdel@gmail.com"));
            customerRepository.save(new Customer(null,"elhabnouni","elhabnouni@gmail.com"));
            customerRepository.save(new Customer(null,"amal","amal@hotmail.com"));
            customerRepository.save(new Customer(null,"joe","joe@aol.com"));
        };
    }

}
