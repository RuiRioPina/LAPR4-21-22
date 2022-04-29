package eapli.base.order.repositories;

import eapli.base.order.domain.Order;
import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface OrderRepository extends DomainRepository<Long, Order> {
    default Optional<Order> findById(final Long number) {
        return ofIdentity(number);
    }

    public Iterable<Order> findAllActive();
}
