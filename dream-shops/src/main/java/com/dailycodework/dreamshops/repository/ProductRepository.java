package com.dailycodework.dreamshops.repository;

import com.dailycodework.dreamshops.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory(String categoryName);

    List<Product> findByBrand(String brandName);

    List<Product> findByCategoryAndBrand(String categoryName, String brandName);

    List<Product> findbyName(String productName);

    List<Product> findByBrandAndProductName(String productName, String productBrand);

    Long countProductsByCategory(String category);

    Long countProductsByBrand(String brand);

    Long countProductsByBrandAndName(String brand, String name);

    Long countProductsByCategoryAndName(String category, String name);
}


