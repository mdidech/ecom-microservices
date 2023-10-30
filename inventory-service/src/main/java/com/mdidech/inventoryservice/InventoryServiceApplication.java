package com.mdidech.inventoryservice;

import com.mdidech.inventoryservice.entities.Product;
import com.mdidech.inventoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

//     @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository, RepositoryRestConfiguration restConfiguration){

        return args -> {
            restConfiguration.exposeIdsFor(Product.class);
            productRepository.save(new Product(null,"Printer",1200,25));
            productRepository.save(new Product(null,"Mac",5250,5));
            productRepository.save(new Product(null,"computer",4000,10));

        };
    }
}
