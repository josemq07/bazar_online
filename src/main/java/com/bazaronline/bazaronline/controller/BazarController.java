package com.bazaronline.bazaronline.controller;

import com.bazaronline.bazaronline.dto.SaleDTO;
import com.bazaronline.bazaronline.model.Client;
import com.bazaronline.bazaronline.model.Product;
import com.bazaronline.bazaronline.model.Sale;
import com.bazaronline.bazaronline.repository.IClientRepository;
import com.bazaronline.bazaronline.service.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class BazarController {

    @Autowired
    private IClientService clientServ;
    @Autowired
    private IProductService prodServ;
    @Autowired
    private ISaleService saleServ;



    //CRUD Client
    @Operation(summary = "Crear un cliente")
    @PostMapping ("/client/create")
    public ResponseEntity<String> createClient(@Valid @RequestBody Client cli){

        clientServ.saveClient(cli);

        return new ResponseEntity<>("Client created successfully.", HttpStatus.CREATED);
    }


    @Operation(summary = "Traer todos los clientes")
    @GetMapping("/client/get-all-clients")
    public ResponseEntity<List<Client>> getAllClients(){

        return new ResponseEntity<>(clientServ.getClients(), HttpStatus.OK);
    }

    @Operation(summary = "Encontrar persona por ID")
    @GetMapping("/client/get-client/{id-client}")
    public ResponseEntity<Client> getClient(@Valid @PathVariable("id-client")Long id_client){

        return new ResponseEntity<>(clientServ.getClient(id_client),HttpStatus.OK);
    }

    @Operation(summary = "Borrar un cliente por ID")
    @DeleteMapping("/client/delete-client/{id-client}")
    public ResponseEntity<String> deleteClient(@Valid @PathVariable("id-client")Long id_client){

        clientServ.deleteClient(id_client);
        return new ResponseEntity<>("Client deleted successfully.",HttpStatus.OK);
    }

    @Operation(summary = "Editar un cliente por ID")
    @PutMapping("/client/edit-client/{id-client}")
    public ResponseEntity<String> editClient(@PathVariable("id-client")Long id_client,
                                             @RequestParam(required = false, name = "name")String new_name,
                                             @RequestParam(required = false, name = "surname")String new_surname,
                                             @RequestParam(required = false, name = "dni")String new_dni) {

        clientServ.editClient(id_client,new_name,new_surname,new_dni);
        return new ResponseEntity<>("Client edited successfully.", HttpStatus.OK);
    }

    //CRUD Product

    @Operation(summary = "Crear un producto")
    @PostMapping ("/product/create")
    public ResponseEntity<String> createProduct(@Valid @RequestBody Product product){

        prodServ.saveProduct(product);

        return new ResponseEntity<>("Product created successfully.", HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener todos los productos")
    @GetMapping("/product/get-all-products")
    public ResponseEntity<List<Product>> getAllProducts(){

        return new ResponseEntity<>(prodServ.getProducts(), HttpStatus.OK);
    }

    @Operation(summary = "Obtener producto por ID")
    @GetMapping("/product/get-product/{id-product}")
    public ResponseEntity<Product> getProduct(@Valid @PathVariable("id-product")Long id_product){

        return new ResponseEntity<>(prodServ.getProduct(id_product),HttpStatus.OK);
    }

    @Operation(summary = "Borrar producto por ID")
    @DeleteMapping("/product/delete-product/{id-product}")
    public ResponseEntity<String> deleteProduct(@Valid @PathVariable("id-product")Long id_product){

        prodServ.deleteProduct(id_product);
        return new ResponseEntity<>("Product deleted successfully.",HttpStatus.OK);
    }

    @Operation(summary = "Editar un producto por ID")
    @PutMapping("/product/edit-product/{id-product}")
    public ResponseEntity<String> editProduct(@PathVariable("id-product")Long id_product,
                                              @RequestParam(required = false, name = "name")String new_name,
                                              @RequestParam(required = false, name = "brand")String new_brand,
                                              @RequestParam(required = false, name = "price")Double new_price,
                                              @RequestParam(required = false, name = "stock")int new_stock) {

        prodServ.editProduct(id_product,new_name,new_brand,new_price,new_stock);
        return new ResponseEntity<>("Product edited successfully.", HttpStatus.OK);

    }

    //CRUD Sale
    @Operation(summary = "Crear una venta")
    @PostMapping("/sale/create")
    public ResponseEntity<String> createSale(@RequestBody Sale sale) {
        try {
            saleServ.saveSale(sale);
            return new ResponseEntity<>(("Venta creada exitosamente"), HttpStatus.CREATED);

        } catch (Exception e) {

            return new ResponseEntity<>("Error al procesar la venta" + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Obtener todas las ventas")
    @GetMapping("/sale/get-all-sales")
    public ResponseEntity<List<Sale>> getAllSales(){

        return new ResponseEntity<>(saleServ.getSales(), HttpStatus.OK);
    }
    @Operation(summary = "Obtener venta por ID")
    @GetMapping("/sale/get-sale/{id-sale}")
    public ResponseEntity<Sale> getSale(@Valid @PathVariable("id-sale")Long id_sale){

        return new ResponseEntity<>(saleServ.getSale(id_sale),HttpStatus.OK);
    }

    @Operation(summary = "Borrar venta por ID")
    @DeleteMapping("/sale/delete-sale/{id-sale}")
    public ResponseEntity<String> deleteSale(@Valid @PathVariable("id-sale")Long id_sale){

        saleServ.deleteSale(id_sale);
        return new ResponseEntity<>("Sale deleted successfully.",HttpStatus.OK);
    }

    @Operation(summary = "Editar una venta por ID")
    @PutMapping("/sale/edit-sale/{id-sale}")
    public ResponseEntity<String> editProduct(@PathVariable("id-sale")Long id_sale,
                                              @RequestParam(required = false, name = "date")LocalDate new_date,
                                              @RequestParam(required = false, name = "total_amount")Double new_total,
                                              @RequestParam(required = false, name = "client")Client new_client,
                                              @RequestParam(required = false, name = "product_list")List<Product> new_list) {

        saleServ.editSale(id_sale,new_date,new_total,new_client,new_list);
        return new ResponseEntity<>("Product edited successfully.", HttpStatus.OK);

    }




    @Operation(summary = "Productos con bajo stock (5 o menos)")
     @GetMapping("/products/out-of-stock")
     public ResponseEntity<List<Product>> productsOutOfStock() {


         return new ResponseEntity<>(prodServ.productsOutOfStock(), HttpStatus.OK);
     }
    @Operation(summary = "Lista de productos de una venta")
    @GetMapping("/sales/products/{id-sale}")
    public ResponseEntity<List<Product>> saleProductsList(@Valid @PathVariable("id-sale")Long id_sale){

        return new ResponseEntity<>(saleServ.getSale(id_sale).getProduct_list(), HttpStatus.OK);
    }

    @Operation(summary = "Cantidad de ventas y monto total de un determinado dia")
    @GetMapping("/sales/total-sales-and-amount/{sale-date}")
    public ResponseEntity<String> totalSalesAndAmount(@Valid @PathVariable("sale-date") LocalDate sale_date){

        return new ResponseEntity<>(saleServ.totalAmountAndSales(sale_date), HttpStatus.OK);
    }

    @Operation(summary = "ID, monto total, products, client name and surname de la venta con el monto mayor")
    @GetMapping("/sales/biggest-sale")
    public ResponseEntity<SaleDTO> biggestSaleInfo(){

        SaleDTO biggestSale = new SaleDTO(0.0);

        for(Sale s : saleServ.getSales()){
            if(s.getTotal_amount()>biggestSale.getTotal_amount()){

                biggestSale.setId_sale(s.getSale_id());
                biggestSale.setClient_name(s.getClient().getClient_name());
                biggestSale.setClient_surname(s.getClient().getClient_surname());
                biggestSale.setTotal_amount(s.getTotal_amount());
                biggestSale.setProducts_amount(s.getAmountOfProducts());
            }
        }
        return new ResponseEntity<>(biggestSale, HttpStatus.OK);
    }


}




