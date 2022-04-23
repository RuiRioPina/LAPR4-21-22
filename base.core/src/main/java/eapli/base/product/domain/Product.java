package eapli.base.product.domain;

import eapli.base.productCategory.domain.Category;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.validations.Preconditions;
import javax.persistence.*;

@Entity
@Table
public class Product implements AggregateRoot<Long> {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Category category;

    @Column(name = "name", nullable = false)
    private Designation name;

    @Embedded
    @Column(name = "description")
    private ProductDescription description;

    @Embedded
    @Column(name = "brand", nullable = false)
    private Brand brand;

    @Embedded
    @Column(name = "price", nullable = false)
    private Price price;

    @Embedded
    @Column(name = "reference")
    private Reference reference;
    @Embedded
    @Column(name = "internalCode", nullable = false)
    private InternalCode internalCode;
    @Embedded
    @Column(name = "productionCode")
    private ProductionCode productionCode;
    @Embedded
    @Column(name = "barcode", nullable = false)
    private Barcode barcode;

    public Product() {
    }

    public Product(Category category,Designation name, ProductDescription description, Brand brand, Price price,
                   Reference reference, InternalCode internalCode, ProductionCode productionCode,
                   Barcode barcode) {
        Preconditions.noneNull(category,name,brand,price,internalCode,barcode);
        this.category = category;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.price = price;
        this.reference = reference;
        this.internalCode = internalCode;
        this.productionCode = productionCode;
        this.barcode = barcode;
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public Long identity() {
        return null;
    }
}
