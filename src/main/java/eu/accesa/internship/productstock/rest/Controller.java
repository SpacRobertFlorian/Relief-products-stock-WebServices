package eu.accesa.internship.productstock.rest;

import eu.accesa.internship.productstock.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class Controller {

    private final RestService restService;

    @Autowired
    public Controller(RestService restService) {
        this.restService = restService;
    }

    //Postman example: localhost:8082/products?name=Water,Chocolate
    @GetMapping
    public ResponseEntity<List<Product>> getProduct(@RequestParam List<String> name) {
        List<Product> products = new ArrayList<>();
        for (String s : name) {
            Optional<Product> product = restService.getProductByName(s);
            product.ifPresent(products::add);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
