package com.rxmobileteam.lecture1.service;

import com.rxmobileteam.lecture1.data.ProductDao;
import com.rxmobileteam.lecture1.data.ProductInterface;
import com.rxmobileteam.utils.ExerciseNotCompletedException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * {@link ProductService} provides an API that allows to manage {@link Product}s.
 * <p>
 * TODO: 1. Using {@link ProductDao} implement method {@link ProductService#addProduct(Product)}}
 * TODO: 2. Using {@link ProductDao} implement method {@link ProductService#searchProducts(String)}
 */
public class ProductService {

    private final ProductInterface productInterface;

    public ProductService(ProductInterface productInterface) {
        this.productInterface = productInterface;
    }


    public boolean addProduct(@NotNull Product product) {
        if (productInterface.add(product) == true)  return true;
        else return false;

    }

    @NotNull
    public List<Product> searchProducts(@NotNull String query) {
        Set<Product> products = productInterface.findAll();
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(query.toLowerCase())) {
                result.add(product);
            }
        }
        return result;
    }
}
