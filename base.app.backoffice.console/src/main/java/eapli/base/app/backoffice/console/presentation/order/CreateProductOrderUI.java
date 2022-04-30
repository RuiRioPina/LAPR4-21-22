package eapli.base.app.backoffice.console.presentation.order;

import eapli.base.app.backoffice.console.presentation.Utils.Utils;
import eapli.base.clientusermanagement.domain.Customer;
import eapli.base.order.application.CreateProductOrderController;
import eapli.base.order.domain.Order;
import eapli.base.product.domain.Product;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;

public class CreateProductOrderUI extends AbstractUI {

    private final CreateProductOrderController ctrl = new CreateProductOrderController();


    @Override
    public boolean doShow() {
        Customer customer = (Customer) Utils.selectsObject(this.ctrl.getCustomerList());

        assert customer != null;
        this.ctrl.createOrder(customer.identity());

        List<Product> lProd = this.ctrl.getProductList();

        int n = 1;

        System.out.print("Product List: \n" +
                            "--------------------------------------------------------------\n");
        for (Product prod: lProd) {
            System.out.println(n + " - " + prod.getName());
            n++;
        }
        System.out.print("\n" +
                "0 - to finish adding products");
        System.out.print("--------------------------------------------------------------\n");

        int i = -1;
        int quantity;
        while(i != 0) {
            i = Utils.readIntegerFromConsole("Select a Product to add to order: \n");
            if(i == 0) {
                break;
            }
            quantity = Utils.readIntegerFromConsole("Select how many: \n");
            if (this.ctrl.addProductToOrder(lProd.get(i+1), quantity)){
                System.out.println( quantity + "x " + lProd.get(i+1).getName().toString() + " added successfully! \n");
            }
        }

        System.out.println("Resulting order class: ");
        System.out.println(this.ctrl.showOrderBuilder());

        Order newOrder = this.ctrl.saveOrder();

        if(newOrder != null) {
            System.out.print("\nOperation successfully completed!");
        } else {
            System.out.println("\nOh no! Something went wrong when creating the Order!");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Place Order";
    }
}
