package eapli.base.persistence.impl.jpa;

import eapli.base.order.domain.Order;
import eapli.base.order.repositories.OrderRepository;

public class JpaOrderRepository extends BasepaRepositoryBase <Order,Long, Long>
        implements OrderRepository {

    public JpaOrderRepository() {
        super("id");
    }

}

