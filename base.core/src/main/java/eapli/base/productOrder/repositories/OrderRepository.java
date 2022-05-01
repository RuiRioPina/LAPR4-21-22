package eapli.base.productOrder.repositories;

import eapli.base.productOrder.domain.ProductOrder;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface OrderRepository extends DomainRepository<Long, ProductOrder> {
    default Optional<ProductOrder> findById(final Long number) {
        return ofIdentity(number);
    }

    public Iterable<ProductOrder> findAllActive();
}
