package eapli.base.app.backoffice.console.presentation.order;

import eapli.base.agv.domain.AGV;
import eapli.base.app.backoffice.console.presentation.Utils.Utils;
import eapli.base.productOrder.application.UpdateOrderStateReadyController;
import eapli.base.productOrder.domain.OrderState;
import eapli.base.productOrder.domain.ProductOrder;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;

public class UpdateOrderStateReadyUI extends AbstractUI {

    UpdateOrderStateReadyController ctrl = new UpdateOrderStateReadyController();

    @Override
    protected boolean doShow() {

        Iterable<ProductOrder> lProdOrder = ctrl.getListProductOrders();
        int i = 1;

        System.out.print("Order List: \n" +
                "--------------------------------------------------------------\n");
        for (ProductOrder prod : lProdOrder ) {
            if(prod.getOrderState().toString().equals(OrderState.READY_FOR_CARRIER.toString())){
                System.out.println(i + " - \n" + prod.shortToString());
                i++;
            }
        }
        System.out.print("\n" +
                "0 - to cancel operation");
        System.out.print("--------------------------------------------------------------\n");
        ProductOrder rProductOrder = (ProductOrder) Utils.selectsObject((List) lProdOrder);

        if (rProductOrder.equals(null)){
            System.out.printf("No Product Order selected! (Null Product Order");
            return false;
        }

        System.out.println();

        String confirmation = null;
        do {
            confirmation= Utils.readLineFromConsole("Do you wish to update ProductOrder#" + rProductOrder.identity() + "'s state to DISPATCHED?(Y/N)\"");
            if(confirmation.equalsIgnoreCase("y")) {
                ProductOrder nProductOrder = rProductOrder;

                if(this.ctrl.changeOrderState(nProductOrder)){
                    this.ctrl.save(nProductOrder,rProductOrder);
                    System.out.print("--------------------------------------------------------------\n" +
                            "Operation successful!" + "\n" +
                            "--------------------------------------------------------------\n" +
                            "Here is the list of DISPATCHED Product Orders:" + "\n" +
                            this.ctrl.printDispatchedProductOrders() + "\n" +
                            "--------------------------------------------------------------\n");
                    break;
                } else {
                    System.out.print("--------------------------------------------------------------\n" +
                            "Operation unsuccessful!" + "\n" +
                            "--------------------------------------------------------------\n");
                    break;
                }

            } else if (confirmation.equalsIgnoreCase("n")) {
                System.out.print("Operation successfully canceled!\n");
                break;
            } else {
                System.out.println("Enter Y to confirm, or N to cancel the order!");
            }

        }while(!confirmation.equalsIgnoreCase("y") || !confirmation.equalsIgnoreCase("n"));


        return false;
    }

    @Override
    public String headline() {
        return "Update Product Order To Dispatched";
    }
}
