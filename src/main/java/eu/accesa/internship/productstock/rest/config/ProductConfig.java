package eu.accesa.internship.productstock.rest.config;

import eu.accesa.internship.productstock.ProductRepository;
import eu.accesa.internship.productstock.model.Product;
import eu.accesa.internship.productstock.model.ProductCategory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProductConfig {
    @Bean
    CommandLineRunner commandLineRunner1(ProductRepository productRepository) {
        return argv -> {
            Product water = new Product("4cafb0c1-1a55-46d4-baea-530ca06cda30", "Water", 10000L, ProductCategory.DRINKS);
            Product vitaminJuice = new Product("23f9cad8-c02a-4b90-a330-c727dfa7b94c", "Vitamin Juice", 10000L, ProductCategory.DRINKS);
            Product cannedVegetables = new Product("2fa9e768-be37-450c-b1d1-6c7bd0b1bced", "Canned Vegetables", 10000L, ProductCategory.FOOD);
            Product chocolate = new Product("a88fdac5-ab67-4bbb-b6f6-f874032244c8", "Chocolate", 10000L, ProductCategory.FOOD);
            Product meat = new Product("d5797a26-2a2a-4c47-8513-3f5ae51c0bae", "Meat", 10000L, ProductCategory.FOOD);

            productRepository.saveAll(List.of(water, vitaminJuice, cannedVegetables, chocolate, meat));
        };
    }
}