package eapli.base.order.application;

import eapli.base.clientusermanagement.domain.Customer;
import eapli.base.clientusermanagement.repositories.CustomerRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.order.domain.Order;
import eapli.base.order.domain.OrderBuilder;
import eapli.base.order.repositories.OrderRepository;
import eapli.base.product.domain.Product;
import eapli.base.productcatalog.CheckProductCatalogService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.ArrayList;
import java.util.List;

public class CreateProductOrderController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private OrderBuilder OB;

    private CustomerRepository cRepo = PersistenceContext.repositories().customers();
    private OrderRepository oRepo = PersistenceContext.repositories().orders();

    private final CheckProductCatalogService productCatalogService= new CheckProductCatalogService();




    public CreateProductOrderController(){
        //authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.SALES_CLERK);
    }

    public List<Customer> getCustomerList(){
        List<Customer> actualList = new ArrayList<Customer>();
        this.cRepo.findAllActive().iterator().forEachRemaining(actualList::add);
        return actualList;
    }

    public void createOrder(Long customerId){
        this.OB = new OrderBuilder(customerId);
    }

    public List<Product> getProductList(){
        List<Product> actualList = new ArrayList<>();
        productCatalogService.allProducts().iterator().forEachRemaining(actualList::add);
        return actualList;
    }

    public boolean addProductToOrder(Product product, int quantity){
        return this.OB.addProduct(product,quantity).equals(OrderBuilder.class);
    }

    public String showOrderBuilder(){
        return this.OB.toString();
    }

    public Order saveOrder(){
        return this.oRepo.save(this.OB.build());
    }

}
