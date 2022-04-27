package eapli.base.productcatalog;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.product.domain.Product;
import eapli.base.product.repositories.ProductRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.List;

public class CheckProductCatalogService {
    private final AuthorizationService authz= AuthzRegistry.authorizationService();

    private final ProductRepository productRepository= PersistenceContext.repositories().products();

    public Iterable<Product> allProducts(){
        return productRepository.findAll();
    }
//    public List<Product> allProductsWithCategory(String category){
//        List<Product> finalList;
//        Iterable<Product> productList= allProducts();
//        for (Product product:productList){
//            if (product.getCategoryString().equals(category){
//                finalList.ad
//            }
//        }
//    }
}
