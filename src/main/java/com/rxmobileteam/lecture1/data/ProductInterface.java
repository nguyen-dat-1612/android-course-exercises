package com.rxmobileteam.lecture1.data;

import com.rxmobileteam.lecture1.service.Product;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public interface ProductInterface {

    boolean add(@NotNull Product product);

    Set<Product> findAll();
}
