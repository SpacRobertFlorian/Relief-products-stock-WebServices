package eu.accesa.internship.productstock.soap;

import eu.accesa.internship.productstock.ProductRepository;
import eu.accesa.internship.productstock.model.Product;
import localhost._8082.soap_products.GetProductRequest;
import localhost._8082.soap_products.GetProductResponse;
import localhost._8082.soap_products.List;
import localhost._8082.soap_products.ListName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.Optional;

@Endpoint
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://localhost:8082/soap-products";

    private final ProductRepository productRepository;

    @Autowired
    public ProductEndpoint(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetProductRequest")
    @ResponsePayload
    public GetProductResponse getProduct(@RequestPayload GetProductRequest request) {
        GetProductResponse response = new GetProductResponse();
        List productListResponse = new List();
        response.setList(productListResponse);
        ListName products = request.getList();

        for (String name : products.getName()) {
            Optional<Product> product = productRepository.findByName(name);
            product.ifPresent(value -> response.getList().getProduct().add(convertToSOAPObj(product.get())));
        }
        return response;
    }

    private localhost._8082.soap_products.Product convertToSOAPObj(Product product) {

        localhost._8082.soap_products.Product productSoap = new localhost._8082.soap_products.Product();
        productSoap.setStock(product.getStock());
        productSoap.setName(product.getName());
        productSoap.setUuid(product.getUuid());
        productSoap.setProductCategory(product.getProductCategory().toString());

        return productSoap;
    }
}
