package com.bazaronline.bazaronline.service;

import com.bazaronline.bazaronline.model.Product;
import com.bazaronline.bazaronline.model.Sale;

import java.util.List;

public interface IProductService {

    public Product getProduct(Long id_sale);

    public List<Product> getProducts();

    public void saveProduct(Product prod);

    public void deleteProduct(Long id_product);

    public void editProduct(Long id_product, String new_name, String new_brand, Double new_price, int new_stock);

    public boolean hasStock(List<Product> productList);
    public void updateStock(List<Product> productList);

    public List<Product> productsOutOfStock();
}
