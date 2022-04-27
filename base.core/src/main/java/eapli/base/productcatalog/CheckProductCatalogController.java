package eapli.base.productcatalog;

import eapli.base.product.domain.Product;
import eapli.framework.application.UseCaseController;

@UseCaseController
public class CheckProductCatalogController {

    private CheckProductCatalogService svc= new CheckProductCatalogService();

    public Iterable<Product> allProducts() {return svc.allProducts();}
}
