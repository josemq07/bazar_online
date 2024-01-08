package com.bazaronline.bazaronline.dto;

public class SaleDTO {

    private Long id_sale;
    private Double total_amount;
    private int products_amount;
    private String client_name;
    private String client_surname;

    public SaleDTO() {

    }

    public SaleDTO(Double total_amount) {
        this.total_amount = total_amount;
    }

    public Long getId_sale() {
        return id_sale;
    }

    public void setId_sale(Long id_sale) {
        this.id_sale = id_sale;
    }

    public Double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Double total_amount) {
        this.total_amount = total_amount;
    }

    public int getProducts_amount() {
        return products_amount;
    }

    public void setProducts_amount(int products_amount) {
        this.products_amount = products_amount;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getClient_surname() {
        return client_surname;
    }

    public void setClient_surname(String client_surname) {
        this.client_surname = client_surname;
    }
}

