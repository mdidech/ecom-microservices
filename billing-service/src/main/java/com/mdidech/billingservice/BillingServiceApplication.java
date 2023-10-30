package com.mdidech.billingservice;

import com.mdidech.billingservice.entities.Bill;
import com.mdidech.billingservice.entities.ProductItem;
import com.mdidech.billingservice.model.Customer;
import com.mdidech.billingservice.model.Product;
import com.mdidech.billingservice.repository.BillRepository;
import com.mdidech.billingservice.repository.ProductItemRepository;
import com.mdidech.billingservice.service.CustomerRestClient;
import com.mdidech.billingservice.service.ProductRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

//    @Bean
    CommandLineRunner start(BillRepository billRepository,
                            ProductItemRepository productItemRepository,
                            CustomerRestClient customerRestClient,
                            ProductRestClient productRestClient){

        return  args -> {
            Collection<Product> products=productRestClient.allProducts().getContent();
//           if(!products.isEmpty()) throw new RuntimeException("Product List is empty");
            Long customerId=1L;
            Customer customer=customerRestClient.findCustomerById(customerId);
            if(customer==null) throw new RuntimeException("Customer Not found");
            Bill bill=new Bill();
            bill.setBillDate(new Date());
            bill.setCustomerId(customerId);
            Bill savedBill=billRepository.save(bill);

            products.forEach(product->{
                ProductItem productItem=new ProductItem();
                productItem.setBill(savedBill);
                productItem.setProductId(product.getId());
                productItem.setQuantity(1+new Random().nextInt(10));
                productItem.setPrice(product.getPrice());
                productItem.setDiscount(Math.random());
                productItemRepository.save(productItem);

            });

        };

    }

}
