package eu.accesa.internship.productstock.rest;

import eu.accesa.internship.productstock.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/products")
public class Controller {

    private final RestService restService;

    @Autowired
    public Controller(RestService restService) {
        this.restService = restService;
    }

    @GetMapping("/{name}")
    public String getProduct(@PathVariable String name) {
        Optional<Product> product = restService.getProductByName(name);
        return product.map(Product::toString).orElse(null);
    }
}
