package com.applicantion.rest.Persistence.Impl;

import com.applicantion.rest.Entities.Product;
import com.applicantion.rest.Persistence.IProductDAO;
import com.applicantion.rest.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
public class ProductDAOImpl implements IProductDAO {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }


    @Override
    public List<Product> findByPriceInRange(BigDecimal min, BigDecimal max) {
        return productRepository.findProductByPriceInRange(min, max);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
