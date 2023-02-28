package org.goat.store.services;

import lombok.extern.slf4j.Slf4j;
import org.goat.store.DTO.ProductRequest;
import org.goat.store.DTO.ProductResponse;
import org.goat.store.models.Product;
import org.goat.store.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void saveProduct(ProductRequest productRequest){
        Product product = new Product();
        BeanUtils.copyProperties(productRequest, product);
        productRepository.save(product);
        log.info("Product created successfully : {}", product.getId());

    }


    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> productResponses = new ArrayList<>();
        products.stream().forEach(product -> {
            ProductResponse productResponse = new ProductResponse();
            BeanUtils.copyProperties(product, productResponse);
            productResponses.add(productResponse);
        });
        return productResponses;
    }
}
