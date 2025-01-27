package com.dailycodework.dreamshops.service.product;

import com.dailycodework.dreamshops.Exception.ProductNotFoundException;
import com.dailycodework.dreamshops.model.Category;
import com.dailycodework.dreamshops.model.Product;
import com.dailycodework.dreamshops.repository.CategoryRepository;
import com.dailycodework.dreamshops.repository.ProductRepository;
import com.dailycodework.dreamshops.requests.AddProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product addProduct(AddProductRequest request) {

        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(() -> {
                    Category newCategory = new Category(request.getCategory().getName());
                    return categoryRepository.save(newCategory);
                } );

        request.setCategory(category);
        return productRepository.save(createProduct(request, category));
    }

    public Product createProduct(AddProductRequest request, Category category){
        return new Product(
                request.getName(),
                request.getBrand(),
                request.getPrice(),
                request.getInventory(),
                request.getDescription(),
                category
        );
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found!"));
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id).
                ifPresentOrElse(productRepository::delete,
                        ()-> new ProductNotFoundException("Product not found!"));


    }

    @Override
    public void updateProduct(Product product, Long productId) {

    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategoryId(String categoryName) {
        return productRepository.findByCategory(categoryName);

    }

    @Override
    public List<Product> getProductsByBrand(String brandName) {
        return productRepository.findByBrand(brandName);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String categoryName, String brandName) {
        return productRepository.findByCategoryAndBrand(categoryName, brandName);
    }

    @Override
    public List<Product> getProductsByName(String productName) {
        return productRepository.findbyName(productName);
    }

    @Override
    public List<Product> getProductByBrandAndName(String productName, String productBrand) {
        return productRepository.findByBrandAndProductName(productName, productBrand);
    }

    @Override
    public Long countProductsByCategory(String category) {
        return productRepository.countProductsByCategory(category);
    }

    @Override
    public Long countProductsByBrand(String brand) {
        return productRepository.countProductsByBrand(brand);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countProductsByBrandAndName(brand, name);
    }

    @Override
    public Long countProductsByCategoryAndName(String category, String name) {
        return productRepository.countProductsByCategoryAndName(category,name);
    }
}
