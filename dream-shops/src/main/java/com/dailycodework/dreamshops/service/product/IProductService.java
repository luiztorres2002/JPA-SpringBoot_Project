package com.dailycodework.dreamshops.service.product;

import com.dailycodework.dreamshops.model.Product;
import com.dailycodework.dreamshops.requests.AddProductRequest;

import java.util.List;

public interface IProductService {

    //Methods that the service should have
    Product addProduct(AddProductRequest product);
    Product getProductById(Long id);
    void deleteProductById(Long id);
    void updateProduct(Product product, Long productId);

    //Lists
    List<Product> getAllProducts();
    List<Product> getProductsByCategoryId(String categoryName);
    List<Product> getProductsByBrand(String brandName);
    List<Product> getProductsByCategoryAndBrand(String categoryName, String brandName);
    List<Product> getProductsByName(String productName);
    List<Product> getProductByBrandAndName(String productName, String productBrand);

    //Counts
    Long countProductsByCategory(String category);
    Long countProductsByBrand(String brand);
    Long countProductsByBrandAndName(String brand, String name);
    Long countProductsByCategoryAndName(String category, String name);
}
