package com.applicantion.rest.Services;

import com.applicantion.rest.Entities.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IProductServices {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    List<Product> findByMaker(Long id);

    List<Product> findByPriceInRange(BigDecimal min, BigDecimal max);

    void save(Product product);

    void deleteById(Long id);
}
