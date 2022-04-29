package eapli.base.productcatalog;

import eapli.base.product.domain.Product;
import eapli.base.productCategory.domain.Category;
import eapli.framework.application.UseCaseController;

@UseCaseController
public class CheckProductCatalogController {

    private CheckProductCatalogService svc= new CheckProductCatalogService();



    public Iterable<Product> allProducts(int num) {return svc.allProductsWithoutRestrictions(num);}

    public Iterable<Product> allProductsWithCategory(String categoryName,int num){
        return svc.allProductsWithCategory(categoryName,num);
    }

    public Iterable<Product> allProductsWithBrand(String brandName,int num){
        return svc.allProductsWithBrand(brandName,num);
    }

    public Iterable<Category> allCategories(){
        return svc.allCategories();
    }
}
