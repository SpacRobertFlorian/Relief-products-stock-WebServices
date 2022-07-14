package eu.accesa.internship.productstock.soap;

import eu.accesa.internship.productstock.ProductRepository;
import eu.accesa.internship.productstock.model.Product;
import localhost._8082.soap_products.GetProductRequest;
import localhost._8082.soap_products.GetProductResponse;
import localhost._8082.soap_products.ListOfProducts;
import localhost._8082.soap_products.ListOfUuid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.Optional;

@Endpoint
public class ProductEndpoint {
    private final Logger logger = LoggerFactory.getLogger(ProductEndpoint.class);
    private static final String NAMESPACE_URI = "http://localhost:8082/soap-products";

    private final ProductRepository productRepository;

    @Autowired
    public ProductEndpoint(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetProductRequest")
    @ResponsePayload
    public GetProductResponse getProduct(@RequestPayload GetProductRequest request) {
        logger.info("Creating response");

        GetProductResponse response = new GetProductResponse();
        ListOfProducts productListResponse = new ListOfProducts();
        response.setList(productListResponse);
        ListOfUuid products = request.getList();

        for (String uuid : products.getUuid()) {
            Optional<Product> product = productRepository.findProductByUuid(uuid);
            product.ifPresent(value -> response.getList().getProduct().add(convertToSOAPObj(product.get())));
        }
        logger.info("Sending response: " + request.getList().toString());

        return response;
    }

    private localhost._8082.soap_products.Product convertToSOAPObj(Product product) {
        logger.info("Converting data to SOAP object");
        localhost._8082.soap_products.Product productSoap = new localhost._8082.soap_products.Product();
        productSoap.setStock(product.getStock());
        productSoap.setName(product.getName());
        productSoap.setUuid(product.getUuid());
        productSoap.setProductCategory(product.getProductCategory().toString());

        logger.info(product + "->" + productSoap);
        return productSoap;
    }
}
