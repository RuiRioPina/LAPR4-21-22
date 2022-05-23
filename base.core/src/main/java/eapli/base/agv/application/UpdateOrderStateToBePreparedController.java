package eapli.base.agv.application;

import eapli.base.agv.domain.AGV;
import eapli.base.agv.repositories.AGVRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productOrder.domain.OrderState;
import eapli.base.productOrder.domain.ProductOrder;
import eapli.base.productOrder.repositories.OrderRepository;

public class UpdateOrderStateToBePreparedController {

    private OrderRepository oRepo = PersistenceContext.repositories().orders();
    private AGVRepository aRepo = PersistenceContext.repositories().agvs();


    public UpdateOrderStateToBePreparedController (){

    }

    public Iterable<ProductOrder> getListProductOrders() {
        return this.oRepo.findByState(OrderState.TO_BE_PREPARED);
    }

    public Iterable<AGV> getListAGV() {
        return this.aRepo.findAll();
    }

    public boolean assignProductOrder(ProductOrder rProductOrder, AGV rAGV) {
        return rAGV.addProductOrderWithPriority(rProductOrder);
    }

    public AGV save(AGV agv){
        this.aRepo.remove(agv);
        return this.aRepo.save(agv);
    }


}
