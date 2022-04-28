package eapli.base.order.application;

import eapli.base.clientusermanagement.domain.Customer;
import eapli.base.clientusermanagement.repositories.CustomerRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.order.domain.Order;
import eapli.base.order.domain.OrderBuilder;
import eapli.base.order.repositories.OrderRepository;
import eapli.base.product.domain.Product;
import eapli.base.product.repositories.ProductRepository;
import eapli.base.productcatalog.CheckProductCatalogService;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreateProductOrderController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private Order order = null;
    private OrderBuilder OB;

    private final CheckProductCatalogService productCatalogService= new CheckProductCatalogService();




    public CreateProductOrderController(){
        //authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.SALES_CLERK);
    }

    public List<Customer> getCustomerList(){
        List<Customer> actualList = new ArrayList<Customer>();
        PersistenceContext.repositories().customers().findAllActive().iterator().forEachRemaining(actualList::add);
        return actualList;
    }

    public void createOrder(Long customerId){
        this.OB = new OrderBuilder(customerId);
    }

    public List<Product> getProductList(){
        List<Product> actualList = new ArrayList<Product>();
        productCatalogService.allProducts().iterator().forEachRemaining(actualList::add);
        return actualList;
    }

    public boolean addProductToOrder(Product product, int quantity){
        this.OB.addProduct(product,quantity);
    }

    public void saveOrder(){
        this.OB.build();
    }

}
