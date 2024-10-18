package org.example.lab2_rest;

//import entities.Category;
//import entities.Product;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.time.LocalDate;

public class WarehouseService {
    private final Warehouse warehouse = new Warehouse();
    private final Lock lock = new ReentrantLock();

    public WarehouseService() {
        existingProducts();
    }

    public void addProduct(Product product) {
        lock.lock();
        try {
            warehouse.addProduct(product);
        } finally {
            lock.unlock();
        }
    }

    public List<Product> getProductsByCategory(Category category) {
        lock.lock();
        try {
            return warehouse.getProductsByCategory(category);
        } finally {
            lock.unlock();
        }
    }

    public List<Product> getAllProducts() {
        lock.lock();
        try {
            return warehouse.getAllProducts();
        } finally {
            lock.unlock();
        }
    }

    public Product getProductById(String id) {
        lock.lock();
        try {
            return warehouse.getProductById(id);
        } finally {
            lock.unlock();
        }
    }

    private void existingProducts() {
        addProduct(new Product("1", "Iphone 16 pro", Category.ELECTRONICS, 5, LocalDate.now(), LocalDate.now()));
        addProduct(new Product("2", "Nokia 3310", Category.ELECTRONICS, 4, LocalDate.now(), LocalDate.now()));
        addProduct(new Product("3", "Sony headphones", Category.ELECTRONICS, 5, LocalDate.now(), LocalDate.now()));
        addProduct(new Product("4", "Tjockjacka", Category.CLOTHING, 4, LocalDate.now(), LocalDate.now()));
        addProduct(new Product("5", "Brallor", Category.CLOTHING, 4, LocalDate.now(), LocalDate.now()));
        addProduct(new Product("6", "Frukostmacka", Category.FOOD, 5, LocalDate.now(), LocalDate.now()));
        addProduct(new Product("7", "Kötbullar och brunsås", Category.FOOD, 3, LocalDate.now(), LocalDate.now()));
        addProduct(new Product("8", "1984", Category.BOOKS, 5, LocalDate.now(), LocalDate.now()));
        addProduct(new Product("9", "Sagan om ringen", Category.BOOKS, 5, LocalDate.now(), LocalDate.now()));
        addProduct(new Product("10", "T-shirt, svart", Category.CLOTHING, 4, LocalDate.now(), LocalDate.now()));
    }
}
