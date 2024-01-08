package com.bazaronline.bazaronline.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Sale {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)


    private Long sale_id;

    private LocalDate sale_date;

    private Double total_amount;
    @ManyToMany
    @JoinTable(
            name = "sale_product_list",
            joinColumns = @JoinColumn(name = "sale_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> product_list;
    @OneToOne
    private Client client;

    public Sale() {
    }

    public Sale(Long sale_id, LocalDate sale_date, Double total_amount, List<Product> product_list, Client client) {
        this.sale_id = sale_id;
        this.sale_date = sale_date;
        this.total_amount = total_amount;
        this.product_list = product_list;
        this.client = client;
    }
    public int getAmountOfProducts(){ return this.product_list.size(); }
    public Long getSale_id() {
        return sale_id;
    }

    public void setSale_id(Long sale_id) {
        this.sale_id = sale_id;
    }

    public LocalDate getSale_date() {
        return sale_date;
    }

    public void setSale_date(LocalDate sale_date) {
        this.sale_date = sale_date;
    }

    public Double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Double total_amount) {
        this.total_amount = total_amount;
    }

    public List<Product> getProduct_list() {
        return product_list;
    }

    public void setProduct_list(List<Product> product_list) {
        this.product_list = product_list;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
