package com.mdidech.billingservice.web;

import com.mdidech.billingservice.entities.Bill;
import com.mdidech.billingservice.entities.ProductItem;
import com.mdidech.billingservice.repository.BillRepository;
import com.mdidech.billingservice.repository.ProductItemRepository;
import com.mdidech.billingservice.service.CustomerRestClient;
import com.mdidech.billingservice.service.ProductRestClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillRestController {

    private BillRepository billRepository;
    private ProductItemRepository productItemRepository;
    private CustomerRestClient customerRestClient;
    private ProductRestClient productRestClient;

    public BillRestController(BillRepository billRepository, ProductItemRepository productItemRepository, CustomerRestClient customerRestClient, ProductRestClient productRestClient) {
        this.billRepository = billRepository;
        this.productItemRepository = productItemRepository;
        this.customerRestClient = customerRestClient;
        this.productRestClient = productRestClient;
    }

    @GetMapping("/fullBill/{id}")
    public Bill bill(@PathVariable  Long id){
        Bill bill=billRepository.findById(id).get();
        bill.setCustomer(customerRestClient.findCustomerById(bill.getCustomerId()));
        bill.getProductsItems().forEach(item->{
            item.setProduct(productRestClient.findProductById(item.getProductId()));
        });
        return bill;
    }
}
