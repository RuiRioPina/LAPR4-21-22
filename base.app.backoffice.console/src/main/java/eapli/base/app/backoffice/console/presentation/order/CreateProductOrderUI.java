package eapli.base.app.backoffice.console.presentation.order;

import eapli.base.app.backoffice.console.presentation.Utils.Utils;
import eapli.base.clientusermanagement.domain.Customer;
import eapli.base.order.application.CreateProductOrderController;
import eapli.base.product.domain.Product;

import java.util.List;

public class CreateProductOrderUI implements Runnable{

    private final CreateProductOrderController ctrl = new CreateProductOrderController();
    private List<Product> lProd;
    private Customer customer;


    @Override
    public void run() {
        customer = (Customer) Utils.selectsObject(ctrl.getCustomerList());

        assert customer != null;
        ctrl.createOrder(customer.identity());

        lProd =  ctrl.getProductList();

        int n = 1;

        System.out.print("Product List: \n" +
                            "--------------------------------------------------------------\n");
        for (Product prod:lProd ) {
            System.out.println(n + " - " + prod.toString());
            n++;
        }
        System.out.print("--------------------------------------------------------------\n");

        int i = -1;
        int quantity = -1;
        while(i != 0) {
            i = Utils.readIntegerFromConsole("Select a Product to add to order: \n");

            if(i == 0) {
                break;

            }

            quantity = Utils.readIntegerFromConsole("Select how many: \n");
//            if (ctrl.addProductToOrder(lProd.get(i+1), quantity)){
//                System.out.println( quantity + "x " + lProd.get(i+1).getName().toString() + " added successfully!");
//            }

        }

        ctrl.saveOrder();


    }
}
