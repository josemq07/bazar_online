package com.bazaronline.bazaronline.service;

import com.bazaronline.bazaronline.model.Client;
import com.bazaronline.bazaronline.model.Product;
import com.bazaronline.bazaronline.model.Sale;
import com.bazaronline.bazaronline.repository.IClientRepository;
import com.bazaronline.bazaronline.repository.IProductRepository;
import com.bazaronline.bazaronline.repository.ISaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class SaleService implements ISaleService {
    @Autowired
    private ISaleRepository saleRepo;

    @Autowired
    private IProductService prodServ;


    @Override
    public Sale getSale(Long id_sale) {
        return saleRepo.findById(id_sale).orElse(null);
    }


    @Override
    public List<Sale> getSales() {
        return saleRepo.findAll();
    }


    @Override
    public void saveSale(Sale sale) {


        if (prodServ.hasStock(sale.getProduct_list())) {

            prodServ.updateStock(sale.getProduct_list());

            sale.setTotal_amount(sale.getTotal_amount());

            saleRepo.save(sale);
        } else {
            throw new RuntimeException("Insufficient stock for one or more products");
        }
    }

    @Override
    public void deleteSale(Long id_sale) {
        saleRepo.deleteById(id_sale);
    }


    @Override
    public void editSale(Long id_sale,LocalDate new_date, Double new_total, Client new_client, List<Product> new_list) {

        Sale existingSale = this.getSale(id_sale);



        if (existingSale != null) {
            existingSale.setSale_date(new_date);
            existingSale.setTotal_amount(new_total);
            existingSale.setProduct_list(new_list);
            existingSale.setClient(new_client);

            saleRepo.save(existingSale);
        }
    }
    @Override
    public String totalAmountAndSales(LocalDate date) {

        List<Sale> totalSales = this.getSales();
        double totalAmount = 0;
        int sales = 0;
        for(Sale s : totalSales){
            if(s.getSale_date().equals(date)){
                sales++;
                totalAmount += s.getTotal_amount();
            }
        }
        return "On: " + date + "has been realized: " + sales + "sales with a total mount of: " + totalAmount;
    }
}