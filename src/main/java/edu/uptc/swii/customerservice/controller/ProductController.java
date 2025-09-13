package edu.uptc.swii.customerservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.uptc.swii.customerservice.model.Product;

@RestController
@RequestMapping("/products")
public class ProductController {

    private List<Product> products = new ArrayList<>();

    @GetMapping
    public List<Product> getAllProducts() {
        return products;
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        products.add(product);
        return product;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        for (Product p : products) {
            if (p.getId().equals(id)) {
                p.setName(product.getName());
                p.setPrice(product.getPrice());
                p.setDescription(product.getDescription());
                p.setQuantity(product.getQuantity());
                return p;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        products.removeIf(p -> p.getId().equals(id));
    }
}