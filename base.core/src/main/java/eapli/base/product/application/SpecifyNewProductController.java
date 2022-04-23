package eapli.base.product.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.product.domain.*;
import eapli.base.product.repositories.ProductRepository;
import eapli.base.productCategory.application.ListCategories;
import eapli.base.productCategory.domain.Category;
import eapli.framework.general.domain.model.Designation;

public class SpecifyNewProductController {

    private final ProductRepository productRepository = PersistenceContext.repositories().products();
    private final ListCategories svcCategories = new ListCategories();

    public Product specifyNewProduct (Category category,Designation name, ProductDescription description, Brand brand,
                                      Price price, Reference reference, InternalCode internalCode,
                                      ProductionCode productionCode, Barcode barcode) {
        return productRepository.save(new Product(category,name,description,brand,price,reference,internalCode,productionCode,barcode));
    }

    public Iterable<Category> categories () {
        return svcCategories.allCategories();
    }

}
