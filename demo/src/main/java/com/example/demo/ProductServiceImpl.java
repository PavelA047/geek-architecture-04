package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDto getProductByID(long id) {
        Product productFromDb = productRepository.getProductByID(id);
        return new ProductDto(productFromDb.getId(), productFromDb.getTitle(), productFromDb.getCost());
    }

    public List<ProductDto> findAll() {
        List<Product> listOfProductsFromDb = productRepository.findAll();
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : listOfProductsFromDb) {
            productDtoList.add(new ProductDto(product.getId(), product.getTitle(), product.getCost()));
        }
        return productDtoList;
    }

    public ProductDto saveProduct(ProductDto productDto) {
        productRepository.save(new Product(productDto.getId(), productDto.getTitle(), productDto.getCost()));
        return productDto;
    }

    public void deleteById(long id) {
        productRepository.delete(id);
    }
}
