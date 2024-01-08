package com.bazaronline.bazaronline.service;

import com.bazaronline.bazaronline.model.Client;
import com.bazaronline.bazaronline.model.Product;
import com.bazaronline.bazaronline.model.Sale;

import java.time.LocalDate;
import java.util.List;

public interface ISaleService {

    public Sale getSale(Long id_sale);

    public List<Sale> getSales();

    public void saveSale(Sale sale);

    public void deleteSale(Long id_sale);

    public void editSale(Long id_sale,LocalDate new_date, Double new_total, Client new_client, List<Product> new_list);

    public String totalAmountAndSales(LocalDate date);
}
