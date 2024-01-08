package com.bazaronline.bazaronline.service;

import com.bazaronline.bazaronline.model.Product;
import com.bazaronline.bazaronline.model.Sale;
import com.bazaronline.bazaronline.repository.IProductRepository;
import com.bazaronline.bazaronline.repository.ISaleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository prodRepo;

    @Override
    public Product getProduct(Long id_sale) {
        return prodRepo.findById(id_sale).orElse(null);
    }


    @Override
    public List<Product> getProducts() {
        return prodRepo.findAll();
    }


    @Override
    public void saveProduct(Product prod) {
        prodRepo.save(prod);
    }


    @Override
    public void deleteProduct(Long id_product) {
        prodRepo.deleteById(id_product);
    }

    @Override
    public void editProduct(Long id_product, String new_name, String new_brand, Double new_price, int new_stock) {

        Product existingProduct = this.getProduct(id_product);

        if (existingProduct != null) {

            existingProduct.setProduct_name(new_name);
            existingProduct.setProduct_brand(new_brand);
            existingProduct.setProduct_price(new_price);
            existingProduct.setProduct_stock(new_stock);

            prodRepo.save(existingProduct);
        }
    }

    @Override
    public List<Product> productsOutOfStock() {

        List<Product> existingProducts = this.getProducts();
        List<Product> OutOfStock = new ArrayList<>();
        for (Product p : existingProducts) {
            if (p.getProduct_stock() < 5) {
                OutOfStock.add(p);
            }
        }
        return OutOfStock;
    }

    public boolean hasStock(List<Product> productList) {
        for (Product p : productList) {
            Product productInDataBase = prodRepo.findById(p.getId_product()).orElse(null);

            if (productInDataBase == null || productInDataBase.getProduct_stock() < 1) {
                throw new RuntimeException("Insufficient stock for product with ID: " + p.getId_product());
            }
        }
        return true;
    }


    public void updateStock(List<Product> productList) {
        for (Product p : productList) {
            Product productInDataBase = prodRepo.findById(p.getId_product()).orElse(null);
            if (productInDataBase != null) {
                productInDataBase.decreaseStock();
                prodRepo.save(productInDataBase);
            }
        }
    }
}

