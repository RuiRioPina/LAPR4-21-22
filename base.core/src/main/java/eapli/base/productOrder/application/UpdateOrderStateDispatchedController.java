package eapli.base.productOrder.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productOrder.domain.OrderState;
import eapli.base.productOrder.domain.ProductOrder;
import eapli.base.productOrder.repositories.OrderRepository;

public class UpdateOrderStateDispatchedController {

    private OrderRepository oRepo = PersistenceContext.repositories().orders();

    public UpdateOrderStateDispatchedController(){

    }

    public Iterable<ProductOrder> getListProductOrders() {
        return this.oRepo.findByState(OrderState.DISPATCHED);
    }

    public boolean changeOrderState(ProductOrder prod){
        prod.setOrderState(OrderState.BEING_PREPARED);
        return prod.getOrderState().equals(OrderState.BEING_PREPARED);
    }

    public ProductOrder save(ProductOrder nProd, ProductOrder oProd){
        this.oRepo.remove(oProd);
        return this.oRepo.save(nProd);
    }

    public String printBeingPreparedProductOrders(){
        Iterable<ProductOrder> lProd = this.oRepo.findByState(OrderState.BEING_PREPARED);
        int i = 1;
        String result = "";
        for (ProductOrder prod: lProd) {
            result += i + " - ProductOrder#" + prod.identity() + ", " + prod.getOrderState().toString() + "\n";
            i++;
        }
        return result;
    }
}
