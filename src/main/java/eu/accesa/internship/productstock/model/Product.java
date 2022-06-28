package eu.accesa.internship.productstock.model;

import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;


@Entity(name = "Product")
@Table(name = "product")
@NaturalIdCache
//@org.hibernate.annotations.Cache(
//        usage = CacheConcurrencyStrategy.READ_WRITE
//)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, columnDefinition = "VARCHAR(255)")
    private String uuid;
    @Column(unique = true)
    private String name;
    @Column
    private Long stock;
    @Column
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;


    public Product() {

    }

    public Product(String uuid, String name, Long stock, ProductCategory productCategory) {
        this.uuid = uuid;
        this.name = name;
        this.stock = stock;
        this.productCategory = productCategory;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }
}
