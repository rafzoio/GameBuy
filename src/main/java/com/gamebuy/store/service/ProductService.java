package com.gamebuy.store.service;

import com.gamebuy.store.dao.ProductDAO;
import com.gamebuy.store.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private static ProductService instance;
    private final ProductDAO productDAO;

    private ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public static ProductService getInstance() {
        ProductDAO productDAO = new ProductDAO();

        if (instance == null) {
            instance = new ProductService(productDAO);
        }
        return instance;
    }

    /**
     * Returns all existing product categories.
     * @return all product categories
     */
    public List<String> getAllProductTypes() {
        return productDAO.getAllProducts()
                .stream()
                .map(Product::getCategory)
                .distinct()
                .toList();
    }

    /**
     * Returns a list of all products of a certain provided category.
     * @param allProducts
     * @param category
     * @return list of products of specified category.
     */
    public ArrayList<Product> filterProductsByCategory(ArrayList<Product> allProducts, String category) {
        if (category.equals("All")) {
            return allProducts;
        }
        List<Product> filteredProducts = allProducts
                .stream()
                .filter(product -> product.getCategory().equals(category))
                .toList();

        return new ArrayList<>(filteredProducts);
    }
}