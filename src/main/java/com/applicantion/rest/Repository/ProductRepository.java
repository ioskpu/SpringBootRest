package com.applicantion.rest.Repository;

import com.applicantion.rest.Entities.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {


    //metodo con query y querymethods
    //@Query("SELECT p FROM Product p WHERE p.price >= ?1 AND <= ?2")
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN ?1 AND ?2")
    List<Product> findProductByPriceInRange(BigDecimal min, BigDecimal max);

    //metodo jpa
    List<Product> findProductByPriceBetween(BigDecimal min, BigDecimal max);

    //metodo para busqueda por maracas
    List<Product> findByMaker(Long id);
}
