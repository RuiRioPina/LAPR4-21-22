package eapli.base.order.application;

import eapli.base.clientusermanagement.domain.Customer;
import eapli.base.clientusermanagement.repositories.CustomerRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.order.domain.Order;
import eapli.base.order.repositories.OrderRepository;
import eapli.base.product.domain.Product;
import eapli.base.product.repositories.ProductRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.List;

public class CreateProductOrderController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private Order order;

    private final CustomerRepository cRepo = PersistenceContext.repositories().customers();
    private final OrderRepository oRepo = PersistenceContext.repositories().orders();
    private final ProductRepository pRepo = PersistenceContext.repositories().products();




    public CreateProductOrderController(){

    }

    public List<Customer> getCustomerList(){
        return null;
    }

    public void createOrder(Customer customer){

    }

    public List<Product> getProductList(){
        return null;
    }

    public boolean addProductToOrder(Product product, int quantity){
        return false;
    }

    public void saveOrder(){

    }

}
