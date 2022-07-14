package eu.accesa.internship.productstock.rest;

import eu.accesa.internship.productstock.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(Controller.class);
    private final RestService restService;

    @Autowired
    public Controller(RestService restService) {
        this.restService = restService;
    }

    //Postman example: localhost:8082/products?name=Water,Chocolate
    //TODO refacut logurile
    @GetMapping
    public ResponseEntity<List<Product>> getProduct(@RequestParam List<String> uuids) {
        logger.info("Creating response");
        List<Product> products = new ArrayList<>();
        for (String uuid : uuids) {
            Optional<Product> product = restService.getProductByUuid(uuid);
            product.ifPresent(products::add);
        }
        logger.info("Sending response: " + restService.toString());
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
