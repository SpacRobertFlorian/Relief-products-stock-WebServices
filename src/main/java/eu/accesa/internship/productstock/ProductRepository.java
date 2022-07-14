package eu.accesa.internship.productstock;

import eu.accesa.internship.productstock.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Optional<Product> findByName(String name);

    Optional<Product> findProductByUuid(String uuid);
}
