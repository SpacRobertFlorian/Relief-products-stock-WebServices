package eu.accesa.internship.productstock.rest;

import eu.accesa.internship.productstock.ProductRepository;
import eu.accesa.internship.productstock.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestService {

    private final ProductRepository productRepository;

    @Autowired
    public RestService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> getProductByName(String name) {
        return productRepository.findByName(name);
    }

}
